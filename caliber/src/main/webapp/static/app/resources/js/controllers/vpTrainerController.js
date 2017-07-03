/*******************************************************************************
 * Team: SDAR 
 * Team Lead: Roderick Dye
 * Authors: Roderick Dye, 
 * Stanley Chouloute, 
 * Daniel Zorrilla, 
 * Adam Baker
 * 
 * Daniel worked on viewing all trainers,
 * Stanley worked on adding trainers,
 * Roderick worked on editing trainers,
 * Adam worked on delete functionality for trainers.
 *******************************************************************************
**/

angular
		.module("vp")
		.controller(
				"vpTrainerController",
				function($scope, $log, caliberDelegate, allTrainers) {
					$log.debug("Booted trainer manage controller.");
					$log.debug('test trainermanager cntroller -j');
					/**
					 * ************************** Batch
					 * ****************************
					 */

					/** On page start --> load all trainers * */
					(function start(){
						caliberDelegate.all.getAllTrainers().then(
								function(trainers){
									$scope.trainers = trainers;
									$log.debug("=========TRAINERS=========");
									$log.debug(trainers);
									
								});
						$log.debug(allTrainers);
						$scope.trainers = allTrainers;
						$scope.selectedTrainers = [];
					})();
					
					
					
					
					
					
					
					
/*************************************************	Code to create Trainer**************************/				
					
					// load training tiers
					caliberDelegate.all.enumTrainerTier().then(
							function(tiers) {
								$log.debug(tiers);
								$scope.trainerTiers = tiers;
							});
					
					
					$scope.trainerTitle = ["Lead Trainer",
										"Senior Trainer",
										"Senior Java Developer",
										"Senior Technology Manager "];
						
//					
//					
//					/** Create new Trainee Object * */
//					function createTrainerObject(trainer) {
//						trainer.name = $scope.trainerForm.name;
//						trainer.email = $scope.trainerForm.email;
//						trainer.title = $scope.trainerForm.title;
//						trainer.tier = $scope.trainerForm.tier;
//						$log.debug(trainee);
//					}
//					
//					
//					
//					
//					/** Save New Trainee Input * */
//					$scope.saveTrainer = function() {
//					var newTrainer = {};
//					createTrainerObject(newTrainer);
//					caliberDelegate.all
//							.createTrainer(newTrainer)
//							.then(
//									function(response) {
//										$log.debug("trainer added: " + response);
//									}
//							)
//					};
//					
					
					
					
					
				});