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

			// load tainers titles
			caliberDelegate.vp.TrainersTitles().then(function(titles) {
				$log.debug(titles);
				$scope.trainersTitles = titles;
			});

			/** show email verification modal* */
			$scope.checkTrainerEmail = function(trainerForm) {
				$log.log("inside check email");
				$log.log(trainerForm.email);
				$log.log("inside check email");
				caliberDelegate.all.getTrainerByEmail(trainerForm.email).then(
						function(response) {
							$log.log("find email " + response)
							$log.debug(response)
							if (response.data === "") {
								$log.log("email does not exist")
								$scope.saveTrainer(trainerForm);
							} else {
								$log.debug(response)
								$log.log("email already exists")
								angular.element(
										"#trainerEmailVerificationModal")
										.modal("show");
								return false;
							}
						})
			}

			/** Save New Trainer Input * */
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
			}
			;

			/** Create scopes for trainer form* */
			$scope.trainerForm = {
				name : null,
				email : null,
				title : null,
				tier : null
			};

			/** Resets trainer form* */
			$scope.resetTrainerForm = function() {
				$scope.trainerForm.name = "";
				$scope.trainerForm.email = "";
				$scope.trainerForm.title = "";
				$scope.trainerForm.tier = "";
				$scope.Save = "Save";
			}
			/** Fill update form with trainer's previous data */
			$scope.populateTrainer = function(trainer) {
				$log.debug(trainer);
				$scope.trainerForm.name = trainer.name;
				$scope.trainerForm.email = trainer.email;
				$scope.trainerForm.title = trainer.title;
				$scope.trainerForm.tier = trainer.tier;
				$scope.Save = "Update";
			};

			/** Update Trainer Input * */
			$scope.updateTrainer = function() {
				console.log($scope.trainerForm);
				caliberDelegate.vp.updateTrainer($scope.trainerForm).then(
						function(response) {
							$log.debug("trainer updated: " + response);
						});
				angular.element("#updateTrainerModal").modal("hide");
			};

			/**
			 * Adam Baker deactivation method
			 */
			$scope.makeInactive = function() {
				$scope.trainerForm.trainerTier = "ROLE_INACTIVE";
				$log.debug("trainer deactivated");

			}

		});
