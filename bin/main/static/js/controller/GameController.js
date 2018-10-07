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
	$scope.game= new Game();
	$scope.sequence = null;

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
            	  $scope.sequence=response.data;
            	  console.log(response.data);
              } else {
            	  console.log("Error occurred in GameController (method -> run) null response"); 
              }
            }, function error(response) {
            	console.log("Error occurred in GameController (method -> run) error response");
            });
          } else {
        	  console.log("Error occurred in GameController (method -> run) The Einstein's number is null");
          }
        } catch (e) {
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
    
  }]);
})();