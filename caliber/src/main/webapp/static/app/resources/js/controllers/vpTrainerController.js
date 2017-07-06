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

			caliberDelegate.all.getAllTrainers().then(

			function(trainers) {
				$log.debug(trainers)
				$scope.allTrainers = trainers;
			});
			/*************** Create and update trainer ***************/

			// load training tiers
			caliberDelegate.all.enumTrainerTier().then(function(tiers) {
				$log.debug(tiers);
				$scope.trainerTiers = tiers;
			});

			$scope.trainerTitle = [ "Lead Trainer", "Senior Trainer",
					"Senior Java Developer", "Senior Technology Manager " ];

			/** Save New Trainee Input * */
			$scope.saveTrainer = function(trainerForm) {
				var newTrainer = trainerForm;
				createTrainerObject(newTrainer);
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
		});