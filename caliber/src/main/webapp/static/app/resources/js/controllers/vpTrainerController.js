/*******************************************************************************
 * Team: SDAR Team Lead: Roderick Dye Authors: Roderick Dye, Stanley Chouloute,
 * Daniel Zorrilla, Adam Baker
 * 
 * Daniel worked on viewing all trainers, Stanley worked on adding trainers,
 * Roderick worked on editing trainers, Adam worked on delete functionality for
 * trainers.
 * ******************************************************************************
 */

angular.module("vp").controller(
		"vpTrainerController",
		function($scope, $log, caliberDelegate) {
			$log.debug("Booted trainer manage controller.");
			$log.debug('test trainermanager cntroller -j');
			/**
			 * ************************** Batch ****************************
			 */

			/** On page start --> load all trainers * */

			caliberDelegate.all.getAllTrainers().then(function(trainers) {
				$log.debug(trainers)
				$scope.allTrainers = trainers;
			})

			/*
			 * $scope.trainers = trainers;
			 * $log.debug("=========TRAINERS========="); $log.debug(trainers);
			 */

			/*
			 * $log.debug(allTrainers); $scope.trainers = allTrainers;
			 * $scope.selectedTrainers = [];
			 */

			/**
			 * *********************************************** Code to create
			 * and update Trainer************
			 */

			// load training tiers
			caliberDelegate.all.enumTrainerTier().then(function(tiers) {
				$log.debug(tiers);
				$scope.trainerTiers = tiers;
			});

			caliberDelegate.all.TrainersTitles().then(function(titles) {
				$log.debug(titles);
				$scope.trainersTitles = titles;
			});

			/** Save New Trainee Input * */
			$scope.saveTrainer = function(trainerForm) {
				var newTrainer = trainerForm;
				createTrainerObject(newTrainer);
				if (newTrainer.tier == "VP") {
					newTrainer.tier = "ROLE_VP";
				} else if (newTrainer.tier == "QC") {
					newTrainer.tier = "ROLE_QC";
				} else {
					newTrainer.tier = "ROLE_TRAINER";
				}
				caliberDelegate.all.createTrainer(newTrainer).then(
						function(response) {
							$log.debug("trainer added: " + response);
						});
			}

			/** Create new Trainee Object * */
			function createTrainerObject(trainer) {
				trainer = $scope.trainerForm;
				$log.debug(trainer);
			}
			;

			/** Fill update form with trainer's previous data */
			$scope.populateTrainer = function(trainer) {
				$log.debug(trainer);
				$scope.trainerForm.name = trainer.name;
			};
			/**
			 * Adam Baker
			 * deactivation method */
			$scope.makeInactive = function(){
				$scope.trainerForm.trainerTier = "ROLE_INACTIVE";
				$log.debug("deactivation complete");
				
			}

		});

