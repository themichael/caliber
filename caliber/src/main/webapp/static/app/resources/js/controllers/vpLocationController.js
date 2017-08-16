
angular
		.module("vp")
		.controller(
				"vpLocationController",
				function($scope, $log, caliberDelegate) {
					// Needed to keep track of what index in categories is used
					// during Edit
					var editIndex;
					
					$log.debug("Booted location manage controller.");
					$log.debug('test locationmanager cntroller -j');
					$scope.allLocations = [];
					$scope.selectedLocation = {};
					

					/** On page start --> load all locations * */

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
                 

					
                 // creating scope for location form
					$scope.locationForm = {
							company : null,
							street : null,
							city : null,
							state : null,
							zipcode : null,
							active: 1
						};
					
					// create new Address object
					function createAddressObject(location) {
						location = $scope.locationForm;
						$log.debug(location);
					}
					// Create Location
					$scope.createLocation = function(locationForm) {
						var newLocation = locationForm;
						createAddressObject(newLocation);
						caliberDelegate.vp.createLocation(newLocation)
								.then(
										function(response) {
											loadAllLocations();
											$log.debug("Location Created: "
													+ response);
										});
					};
					
					//** Resets location form* *//*
					$scope.resetLocationForm = function() {
						$scope.locationForm.company = "";
						$scope.locationForm.street = "";
						$scope.locationForm.city = "";
						$scope.locationForm.state = "";
						$scope.locationForm.zipcode = "";
						$scope.locationForm.active = 1;
						$scope.Save = "Save";
					};
					
					// Create Location
					$scope.createLocation = function(locationForm) {
						console.log(locationForm);
						var newLocation = $scope.locationForm;
						createAddressObject(newLocation);
						caliberDelegate.vp.createLocation(newLocation)
								.then(
										function(response) {
											loadAllLocations();
											$log.debug("Location Created: "
													+ response);
										});
					};
					
					
					/** Fill update form with locations data */
					$scope.populateLocation = function(index) {
						$log.debug($scope.allLocations[index]);
						$scope.locationForm.company = $scope.allLocations[index].company;
						$scope.locationForm.street = $scope.allLocations[index].street;
						$scope.locationForm.city = $scope.allLocations[index].city;
						$scope.locationForm.state = $scope.allLocations[index].state;
						$scope.locationForm.zipcode = $scope.allLocations[index].zipcode;
						$scope.locationForm.active = $scope.allLocations[index].active;
						$scope.Save = "Update";
					};
					
				/*	$scope.updateLocation = function(locationForm) {
						$log.debug(locationForm);
						var currentLocation = locationForm;
						createAddressObject(currentLocation);
						caliberDelegate.vp.updateLocation(currentLocation)
						.then(
								function(response) {
									loadAllLocations();
									$log.debug("Location Updated: "
											+ response);
								});
					}
*/
					   
					// get location from input
					$scope.populateDeleteLocation = function(index) {
						$scope.selectedLocation = $scope.allLocations[index];
					}
					
					// removing location - deactivate
					$scope.removeLocation = function(){
						$scope.selectedLocation.active=0; // take this out for just delete
                        caliberDelegate.vp.deactivateLocation($scope.selectedLocation).then(
                                function(response) {
                                    loadAllLocations();
                                    $log.debug("Location removed:"
                                            + response);
                                }
                        )
                        angular.element("#deleteLocationModal").modal("hide");
                    }
					
					

					/**
					 * *********************************************** Code to
					 * create and update Trainer************
					 *//*

					// load training tiers
					caliberDelegate.all.enumTrainerTier().then(function(tiers) {
						$log.debug(tiers);
						var filteredTiers = tiers.filter(function(ary) {
							return ary !== 'ROLE_INACTIVE'
						});
						for (var i = 0; i < filteredTiers.length; i++) {
							filteredTiers[i] = filteredTiers[i].substr(5);
						}
						$scope.trainerTiers = filteredTiers;
					});

					// load tainers titles
					caliberDelegate.vp.trainersTitles().then(function(titles) {
						$log.debug(titles);
						$scope.trainersTitles = titles;
					});

					*//** Save email verification modal* *//*
					$scope.checkTrainerEmail = function(trainerForm) {
						caliberDelegate.vp
								.getTrainerEmail(trainerForm.email)
								.then(
										function(response) {
											$log.debug(response)
											if (response.data === "") {
												$scope.saveTrainer(trainerForm);
											} else {
												$log.debug(response)
												angular
														.element(
																"#trainerEmailVerificationModal")
														.modal("show");
												return false;
											}
										})
					};

					*//** Save New Trainer Input * *//*
					$scope.saveLocation = function(locationForm) {
						var newLocation = locationForm;
						createAddressObject(newLocation);
						// newLocation.tier = submitTier(newTrainer.tier);
						caliberDelegate.vp.createLocation(newLocation).then(
								function(response) {
									$log.debug("location added: " + response);
									$scope.loadAllLocations();
								});
						angular.element("#createLocationModal").modal("hide");
					};

					*//** Create new Address Object * *//*
					
					*//** Create scopes for location form * *//*
				

					*//** Resets location form* *//*
					$scope.resetLocationForm = function() {
						$scope.locationForm.company = "";
						$scope.locationForm.street = "";
						$scope.locationForm.city = "";
						$scope.locationForm.state = "";
						$scope.locationForm.zipcode = "";
						$scope.Save = "Save";
					};

					*//** Fill update form with trainer's previous data *//*
					$scope.populateLocation = function(index) {
						$log.debug($scope.allTrainers[index]);
						$scope.locationForm.company = $scope.allLocations[index].company;
						$scope.locationForm.street = $scope.allLocations[index].street;
						$scope.locationForm.city = $scope.allLocations[index].city;
						$scope.locationForm.state = $scope.allLocations[index].state;
						$scope.Save = "Update";
					};

					*//** Update Trainer Input * *//*
					$scope.updateTrainer = function() {
						$scope.locationForm.tier = submitTier($scope.trainerForm.tier);
						caliberDelegate.vp.updateTrainer($scope.trainerForm)
								.then(function(response) {
									$log.debug("trainer updated: " + response);
									$scope.loadAllLocations();
								});
						angular.element("#editTrainerModal").modal("hide");
					};

					*//**
					 * Adam Baker deactivation function
					 *//*
					$scope.makeInactive = function() {
						$log.debug($scope.trainerForm);
						$scope.trainerForm.tier = "ROLE_INACTIVE";
						caliberDelegate.vp
								.deactivateTrainer($scope.trainerForm).then(
										function() {
											$log.debug("trainer deactivated");
											$scope.loadAllTrainers();
										});
						angular.element("#deleteTrainerModal").modal("hide");
					};*/

				});
