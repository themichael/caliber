/*******************************************************************************
 * Team: Quantum Team Lead: Mridula Zaman Authors: Christian Acosta, Leibniz Berihuete, 
 * Dingchao Liao, Mridula Zaman
 *
 * Mridula worked on viewing all locations, adding locations, editing locations,
 * and deleting functionality for locations for the VP role. 
 * ******************************************************************************
 */

angular
		.module("vp")
		.controller(
				"vpLocationController",
				function($scope, $log, caliberDelegate) {
					$log.debug("Booted location manage controller.");
					$log.debug('test locationmanager cntroller -j');
					$scope.allLocations=[];
					$scope.selectedLocation = {};
					/**
					 * ************************** Batch
					 * ****************************
					 */

					/** On page start --> load all trainers * */

					(function(){
						caliberDelegate.vp.getAllLocations().then(
								function(locations) {
									$log.debug(locations);
									console.log(locations);
									$scope.allLocations = locations;
								});
					})();
					
					var loadAllLocations = function(){
						caliberDelegate.vp.getAllLocations().then(
								function(locations) {
									$log.debug(locations);
									console.log(locations);
									$scope.allLocations = locations;
								});
					}
					

					
					
					/**
					 * Code to create and delete Location
					 * 
					 */
					$scope.populateLocation = function(index){
						console.log(index);
						$scope.selectedLocation = $scope.allLocations[index];
					}
					
					$scope.removeLocation = function(){
						$scope.selectedLocation.active=0;
						caliberDelegate.vp.deactivateLocation($scope.selectedLocation).then(
								function(response) {
									loadAllLocations();
									$log.debug("Location removed: "
											+ response);
								}
						)
						angular.element("#deleteLocationModal").modal("hide");
					}
					
				});