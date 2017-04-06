angular
		.module("trainer")
		.controller(
				"trainerManageController",
				function($scope, $log, caliberDelegate, allBatches) {
					$log.debug("Booted trainer manage controller.");
					$log.debug('test trainermanager cntroller -j');
					/**
					 * ************************** Batch
					 * ****************************
					 */

					/** On page start --> load all trainers * */
					(function start() {
						caliberDelegate.all.getAllTrainers().then(
								function(trainers) {
									$scope.trainers = trainers;
									$log.log("=========TRAINERS=========");
									$log.debug(trainers);
								});
						$log.debug(allBatches);
						$scope.batches = allBatches;
						$scope.selectedBatches = [];
						sortByDate(new Date().getFullYear());
					})();

					/** Filter batches by year * */
					$scope.years = addYears();
					function addYears() {
						var currentYear = new Date().getFullYear();
						$scope.selectedYear = currentYear;

						var data = [];
						// List all years from 2014 --> current year
						for (var y = currentYear; y >= 2014; y--) {
							data.push(y)
						}
						return data;
					}

					$scope.selectYear = function(index) {
						$scope.selectedYear = $scope.years[index];
						sortByDate($scope.selectedYear);
					};

					function sortByDate(currentYear) {
						$scope.selectedBatches = [];
						for (var i = 0; i < $scope.batches.length; i++) {
							var date = new Date($scope.batches[i].startDate);
							if (date.getFullYear() === currentYear) {
								$scope.selectedBatches.push($scope.batches[i]);
							}
						}
					}

					/** Add & View Batches * */
					$scope.trainingName = {
						model : null
					};

					$scope.trainingType = {
						model : null,
						options : [ 'Revature' ]
					};
					// load training types
					caliberDelegate.all.enumTrainingType().then(
							function(trainingTypes) {
								$log.debug(trainingTypes);
								$scope.trainingType.options = trainingTypes;
							});

					$scope.skillType = {
						model : null,
						options : [ 'J2EE', '.NET', 'SDET' ]
					};
					// load skill types
					caliberDelegate.all.enumSkillType().then(
							function(skillTypes) {
								$log.debug(skillTypes);
								$scope.skillType.options = skillTypes;
							});

					$scope.location = {
						model : null,
						options : [ 'Revature LLC, 11730 Plaza America Drive, 2nd Floor | Reston, VA 20190' ]
					};
					// load common locations
					caliberDelegate.all.enumCommonLocations().then(
							function(locations) {
								$log.debug(locations);
								$scope.location.options = locations;
							});

					$scope.receivers = [ {
						value : ""
					} ];
					$scope.addRecipient = function() {
						$scope.receivers.push({
							value : ""
						});
					};
					$scope.trainer = {
						model : null
					};
					$scope.coTrainer = {
						model : null
					}
					$scope.startDate = {
						model : null
					};
					$scope.endDate = {
						model : null
					};
					$scope.goodGradeThreshold = {
						model : null
					};
					$scope.borderlineGradeThreshold = {
						model : null
					};
					$scope.benchmarkStartDate = {
						model : null
					};

					/** Get batches for user and trainees in each batch * */
					$scope.selectCurrentBatch = function(index) {
						$scope.currentBatch = $scope.selectedBatches[index];
						$scope.trainees = $scope.selectedBatches[index].trainees;
					};

					/** Get trainee info* */
					$scope.getTrainee = function(trainee) {
						// TODO: MAKE EDIT BUTTON VISABLE AND INVISBLE WHEN
						// FINISHED
						$scope.editTrainee = trainee;
						console.log(editTrainee);
						editTrainee.batch = $scope.currentBatch;

					}

					$scope.update = function(editedTrainee) {

						console.log(editedTrainee);
						for (var i = 0; i < $scope.receivers.length; i++) {

							if ($scope.receivers[i].name == "") {
								$scope.receivers[i].name = editedTrainee.name;
							}

							if ($scope.receivers[i].email == "") {
								$scope.receivers[i].email = editedTrainee.email;
							}

							if ($scope.receivers[i].trainingStatus == "") {
								$scope.receivers[i].trainingStatus = editedTrainee.trainingStatus;
							}

							if ($scope.receivers[i].phoneNumber == "") {
								$scope.receivers[i].phoneNumber = editedTrainee.phoneNumber;
							}

							if ($scope.receivers[i].skypeId == "") {
								$scope.receivers[i].skypeId = editedTrainee.skypeId;
							}

							if ($scope.receivers[i].profileUrl == "") {
								$scope.receivers[i].profileUrl = editedTrainee.profileUrl;
							}
							var updTrainee = {
								traineeId : editedTrainee.traineeId,
								name : $scope.receivers[i].name,
								email : $scope.receivers[i].email,
								trainingStatus : $scope.receivers[i].trainingStatus,
								phoneNumber : $scope.receivers[i].phoneNumber,
								skypeId : $scope.receivers[i].skypeId,
								profileUrl : $scope.receivers[i].profileUrl,
								batch : $scope.currentBatch
							};

						}
						console.log(updTrainee);
						editedTrainee = updTrainee;
						console.log(editedTrainee);
						caliberDelegate.all.updateTrainee(editedTrainee);

						/*
						 * $scope.editTrainee.name = "";
						 * $scope.editTrainee.email = "";
						 * $scope.editTrainee.phoneNumber = "";
						 * $scope.editTrainee.skypeId = "";
						 * $scope.editTrainee.profileUrl = "";
						 */

					};

					/** Fill update form with batch previous data* */
					$scope.populateBatch = function(batch, index) {
						$scope.batchFormName = "Update Batch";
						$scope.trainingName.model = batch.trainingName;
						$scope.trainingType.model = batch.trainingType
						$scope.skillType.model = batch.skillType;
						$scope.location.model = batch.location;
						$scope.trainer.model = batch.trainer.name;
						if (batch.coTrainer) {
							$scope.coTrainer.model = batch.coTrainer.name;
						} else {
							$scope.coTrainer.model = ""
						}
						$scope.startDate.model = new Date(batch.startDate);
						$scope.endDate.model = new Date(batch.endDate);
						$scope.goodGradeThreshold.model = batch.goodGradeThreshold;
						$scope.borderlineGradeThreshold.model = batch.borderlineGradeThreshold;
						$scope.benchmarkStartDate.model = new Date(
								batch.benchmarkStartDate);
						$scope.Save = "Update";
						$scope.row = index;
					}

					/** Resets batch form for creating new batch* */
					$scope.resetBatchForm = function() {
						$scope.batchFormName = "Create New Batch"
						$scope.trainingName.model = "";
						$scope.trainingType.model = "";
						$scope.skillType.model = "";
						$scope.location.model = "";
						$scope.trainer.model = "";
						$scope.coTrainer.model = "";
						$scope.startDate.model = "";
						$scope.endDate.model = "";
						$scope.goodGradeThreshold.model = "";
						$scope.borderlineGradeThreshold.model = "";
						$scope.benchmarkStartDate.model = "";
						$scope.Save = "Save";
						if ($scope.currentBatch) {
							$scope.currentBatch = null;
						}
					}

					/** Create new Batch Object * */
					function createBatchObject(batch) {

						batch.trainingName = $scope.trainingName.model;
						batch.trainingType = $scope.trainingType.model;
						batch.skillType = $scope.skillType.model;
						batch.location = $scope.location.model;
						batch.trainer = null;
						batch.coTrainer = null;
						batch.startDate = $scope.startDate.model;
						batch.endDate = $scope.endDate.model;
						batch.goodGradeThreshold = $scope.goodGradeThreshold.model;
						batch.borderlineGradeThreshold = $scope.borderlineGradeThreshold.model;
						batch.benchmarkStartDate = $scope.benchmarkStartDate.model;

						/*
						 * if ($scope.currentBatch) { newBatch.batchId =
						 * $scope.currentBatch.batchId; }
						 */

						if ($scope.trainer) {
							var trainer_name = $scope.trainer.model;
						}
						if ($scope.coTrainer) {
							var cotrainer_name = $scope.coTrainer.model;
						}
						for (var i = 0; i < $scope.trainers.length; i++) {

							if ($scope.trainers[i].name == trainer_name) {
								batch.trainer = $scope.trainers[i];
							}
							if ($scope.trainers[i].name == cotrainer_name) {
								batch.coTrainer = $scope.trainers[i];
							}
						}

						// return newBatch;
					}

					$scope.update = function() {

						$scope.editTrainee.name = "";
						$scope.editTrainee.email = "";
						$scope.editTrainee.phoneNumber = "";
						$scope.editTrainee.skypeId = "";
						$scope.editTrainee.profileUrl = "";

					};

					/** Save Batch * */
					$scope.addNewBatch = function() {
						// Ajax call check for 200 --> then assemble batch
						// $log.debug($scope.currentBatch);
						if ($scope.currentBatch) {
							createBatchObject($scope.currentBatch);
							caliberDelegate.all
									.updateBatch($scope.currentBatch)
									.then(
											$scope.selectedBatches[$scope.row] = $scope.currentBatch)
						} else {
							var newBatch={};
							createBatchObject(newBatch);
							console.log('this is' + newBatch);
							caliberDelegate.all
									.createBatch(newBatch)
									.then(
											function() {
												// coTrainer may be undefined

												if ($scope.coTrainer) {
													$scope.batches
															.push({
																trainingName : $scope.trainingName.model,
																trainingType : $scope.trainingType.model,
																skillType : $scope.skillType.model,
																location : $scope.location.model,
																trainer : newBatch.trainer,
																coTrainer : newBatch.coTrainer,
																startDate : $scope.startDate.model,
																endDate : $scope.endDate.model,
																goodGradeThreshold : $scope.goodGradeThreshold.model,
																borderlineGradeThreshold : $scope.borderlineGradeThreshold.model,
																benchmarkStartDate : $scope.benchmarkStartDate.model
															});
												} else {
													$scope.batches
															.push({
																trainingName : $scope.trainingName.model,
																trainingType : $scope.trainingType.model,
																skillType : $scope.skillType.model,
																location : $scope.location.model,
																trainer : newBatch.coTrainer,
																startDate : $scope.startDate.model,
																endDate : $scope.endDate.model,
																goodGradeThreshold : $scope.goodGradeThreshold.model,
																borderlineGradeThreshold : $scope.borderlineGradeThreshold.model,
																benchmarkStartDate : $scope.benchmarkStartDate.model
															});
													console.log($scope.batches)
												}

												sortByDate($scope.selectedYear);
											});
						}
						angular.element("#createBatchModal").modal("hide");

					};

					/**
					 * ************************** Trainees
					 * ****************************
					 */

					/** Load trainees for batch * */
					$scope.name = {
						model : null
					};
					$scope.email = {
						model : null
					};
					/* Set default training status for new trainee */
					$scope.trainingStatus = "Training";
					$scope.trainingStatuses = {
						model : null,
						options : [ 'Training' ]
					};
					// load training types
					caliberDelegate.all.enumTrainingStatus().then(
							function(statuses) {
								$log.debug(statuses);
								$scope.trainingStatuses.options = statuses;
							});

					/** Save New Trainee Input * */
					$scope.addNewTrainee = function() {
						for (var i = 0; i < $scope.receivers.length; i++) {
							var newTrainee = {
								name : $scope.receivers[i].name,
								email : $scope.receivers[i].email,
								trainingStatus : $scope.receivers[i].trainingStatus,
								phoneNumber : $scope.receivers[i].phoneNumber,
								skypeId : $scope.receivers[i].skypeId,
								profileUrl : $scope.receivers[i].profileUrl,
								batch : $scope.currentBatch
							};
							$log.debug(newTrainee);
							caliberDelegate.all
									.createTrainee(newTrainee)
									.then(
											function() {
												$scope.trainees
														.push({
															name : newTrainee.name,
															email : newTrainee.email,
															trainingStatus : newTrainee.trainingStatus,
															phoneNumber : newTrainee.phoneNumber,
															skypeId : newTrainee.skypeId,
															profileUrl : newTrainee.profileUrl,
															batch : null

														});
											});
						}
						$scope.receivers = [ {
							name : "",
							email : "",
							phoneNumber : "",
							skypeId : "",
							profileUrl : ""
						} ];
					};

					/** Add Or Remove New Trainee Form */
					$scope.receivers = [ {
						name : "",
						email : "",
						phoneNumber : "",
						skypeId : "",
						profileUrl : ""
					} ];
					$scope.addTrainee = function() {
						$scope.receivers.push({
							name : "",
							email : "",
							phoneNumber : "",
							skypeId : "",
							profileUrl : ""
						});
					};
					$scope.deleteTrainee = function(receiver) {
						for (var i = 0; i < $scope.receivers.length; i++) {
							if ($scope.receivers[i] === receiver) {
								$scope.receivers.splice(i, 1);
								break;
							}
						}
					};

					/** Delete Trainee* */

					$scope.removeTrainee = function(traineeId) {
						console.log(traineeId);
						caliberDelegate.all
								.deleteTrainee(traineeId)
								.then(
										function() {
											for (var i = 0; i < $scope.receivers.length; i++) {
												if ($scope.receivers[i] === receiver) {
													$scope.receivers.splice(i,
															1);
													break;
												}
											}
										})

					};

				});
