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
				$scope.selectedTrainers = [];
			})

			/**
			 * *********************************************** Code to create
			 * and update Trainer************
			 */

			// load training tiers
			caliberDelegate.all.enumTrainerTier().then(function(tiers) {
				$log.debug(tiers);
				var filteredTiers = tiers.filter(function(ary) { return ary !== 'ROLE_INACTIVE' });
				$scope.trainerTiers = filteredTiers;
			});

			caliberDelegate.vp.TrainersTitles().then(function(titles) {
				$log.debug(titles);
				$scope.trainersTitles = titles;
			});

			/** Save New Trainer Input * */
			$scope.saveTrainer = function(trainerForm) {
				var newTrainer = trainerForm;
				createTrainerObject(newTrainer);
				newTrainer.tier = submitTier(newTrainer.tier);
				caliberDelegate.vp.createTrainer(newTrainer).then(
						function(response) {
							$log.debug("trainer added: " + response);
						});
				angular.element("#createTrainerModal").modal("hide");
			}

			/** Create new Trainer Object * */
			function createTrainerObject(trainer) {
				trainer = $scope.trainerForm;
				$log.debug(trainer);
			};

            /** Create scopes for trainer form* */
            $scope.trainerForm = {
                trainerId : null,
            	name : null,
                email : null,
                title : null,
                tier : null
            };
            
            /** Resets trainer form* */
            $scope.resetTrainerForm = function() {
            	$scope.trainerForm.trainerId = "";
                $scope.trainerForm.name = "";
                $scope.trainerForm.email = "";
                $scope.trainerForm.title = "";
                $scope.trainerForm.tier = "";
                $scope.Save = "Save";
            };
            
            /** Fill update form with trainer's previous data */
            $scope.populateTrainer = function(trainer) {
                $log.debug(trainer);
                $scope.trainerForm.trainerId = trainer.trainerId;
                $scope.trainerForm.name = trainer.name;
                $scope.trainerForm.email = trainer.email;
                $scope.trainerForm.title = trainer.title;
                $scope.trainerForm.tier = trainer.tier.substr(5);
                console.log($scope.trainerForm);
                $scope.Save = "Update";
            };
            
			/** Update Trainer Input * */
			$scope.updateTrainer = function() {
				$scope.trainerForm.tier = submitTier($scope.trainerForm.tier);
				caliberDelegate.vp.updateTrainer($scope.trainerForm).then(
						function(response) {
							$log.debug("trainer updated: " + response);
						});
				angular.element("#editTrainerModal").modal("hide");
			};
			
			/**
			 * Adam Baker
			 * deactivation function */
			$scope.makeInactive = function(){
				$log.debug($scope.trainerForm);
				$scope.trainerForm.tier = "ROLE_INACTIVE";
				caliberDelegate.vp.deactivateTrainer($scope.trainerForm)
				.then(function(response){
					$log.debug("trainer deactivated");
				});
				angular.element("#deleteTrainerModal").modal("hide");
			}
			
			submitTier = function(tier){
				var pre = "ROLE_"
				return pre.concat(tier);
			}

		});

