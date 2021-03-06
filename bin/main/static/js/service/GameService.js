/**
 * @name=GameService.js
 * @author= Jose Ortega
 * @version = 1.0
 * @description = This file is used to communicate with Server in order to get/send datas
 * @date= 05-10-2018
 */

(function() {
	angular.module("fizzBuzzApp").service("gameService",[ '$http', function($http) {

		/**
		 * @name= getLimitNumbers()
		 * @author= Jose Ortega
		 * @version = 1.0
		 * @description = This function is used to get the limits numbers (min and max)
		 * @date= 05-10-2018
		 * @return = A hashmap
		 */
		this.getLimitNumbers = function() {
			return $http({
				method : 'GET',
				url : '/numLimits'
			});
		}
	
		/**
		 * @name= run()
		 * @author= Jose Ortega
		 * @version = 1.0
		 * @description = This function is used to start the game
		 * @date= 05-10-2018
		 * @param = numEinstein: number generated by client
		 * @return = The game's sequence (Vector)
		 */
		this.run = function(numEinstein) {
			return $http({
				method : 'POST',
				url : '/run',
				data : numEinstein
			});
		}
	
	} ]);
})();