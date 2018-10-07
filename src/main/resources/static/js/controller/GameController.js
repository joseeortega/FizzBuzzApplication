/**
* @name=GameController.js
* @author= Jose Ortega
* @version = 1.0
* @description = Main FizzBuzz game controller
* @date= 05-10-2018
*/

(function(){
  angular.module("fizzBuzzApp").controller("gameController",["$scope","$window","gameService",function($scope,$window,gameService){
	  
	//Variables
	$scope.game;
	$scope.sequence;
	$scope.errorGame;

    /**
    * @name= getLimitsNumbers()
    * @author= Jose Ortega
    * @version = 1.0
    * @description = This function is used to get the limit numbers (to work in app) using the service
    * @date= 05-10-2018
    */
    $scope.getLimitsNumbers = function(){
		try {
			gameService.getLimitNumbers().then(function success(response) {
				//Set the game fields
		          $scope.game.setMinNum(response.data["min"]);
		          $scope.game.setMaxNum(response.data["max"]);
	        }, function error(response) {
	        	console.log("Error occurred in GameController (method -> getLimitsNumbers) error response");
	        });
	      } catch (e) {
	    	  console.log("Error occurred in GameController (method -> getLimitsNumbers) exception");
	      }
    	
    }
    
    /**
     * @name= run()
     * @author= Jose Ortega
     * @version = 1.0
     * @description = This function is used to run the game in server in order to get the sequence generated (sending 
     * the einstein's number) using the service
     * @date= 05-10-2018
     */
    $scope.run = function(){
        try {
          if ($scope.game.getNumEinstein() != null) {
        	  gameService.run($scope.game.getNumEinstein()).then(function success(response) {
              if (response.data!=null) {
            	  if(response.data.length!=0){
            		  $scope.sequence=response.data;
            	  }else{
            		  $scope.errorGame=true;
                	  console.log("Error occurred in GameController (method -> run) no elements received");  
            	  }
            	  
              } else {
            	  $scope.errorGame=true;
            	  console.log("Error occurred in GameController (method -> run) null response"); 
              }
            }, function error(response) {
            	$scope.errorGame=true;
            	console.log("Error occurred in GameController (method -> run) error response");
            });
          } else {
        	  $scope.errorGame=true;
        	  console.log("Error occurred in GameController (method -> run) The Einstein's number is null");
          }
        } catch (e) {
        	$scope.errorGame=true;
        	console.log("Error occurred in GameController (method -> run) exception");
        }
      }
    
    /**
     * @name= setNullSequence()
     * @author= Jose Ortega
     * @version = 1.0
     * @description = This function is used to set the sequence to null
     * @date= 05-10-2018
     */
    $scope.setNullSequence = function(){
    	$scope.sequence=null;
    }
    
    /**
     * @name= initialize()
     * @author= Jose Ortega
     * @version = 1.0
     * @description = This function is used to initialize all variables
     * @date= 05-10-2018
     */
    $scope.initialize = function(){
    	
    	try{
	    	$scope.game= new Game();
	    	$scope.sequence = null;
	    	$scope.errorGame=false;
	    	$scope.getLimitsNumbers();
    	} catch (e) {
        	$scope.errorGame=true;
        	console.log("Error occurred in GameController (method -> initialize) exception");
        }
    }
    
  }]);
})();