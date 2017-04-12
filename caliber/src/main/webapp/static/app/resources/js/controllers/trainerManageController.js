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
									$scope.role = $cookies.get("role");
								});
						$log.debug(allBatches);
						$scope.batches = allBatches;
						$scope.selectedBatches = [];
						sortByDate(new Date().getFullYear());
						$scope.notUpdating = "true";
					})();

					/** Filter batches by year * */
					$scope.years = addYears();
					function addYears() {
						var currentYear = new Date().getFullYear();
						$scope.selectedYear = currentYear;

						var data = [];
						// List all years from 2014 --> current year
						for (var y = currentYear + 1; y >= currentYear - 2; y--) {
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
						$scope.batchRow = index;
						$log.log($scope.currentBatch);
					};

					$scope.Updating = false;

					/** Fill update form with batch previous data* */
					$scope.populateBatch = function(batch) {
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
						$scope.startDate.model = new Date(batch.startDate
								.replace(/-/g, '/'));
						$scope.endDate.model = new Date(batch.endDate.replace(
								/-/g, '/'));
						$scope.goodGradeThreshold.model = batch.goodGradeThreshold;
						$scope.borderlineGradeThreshold.model = batch.borderlineGradeThreshold;
						$scope.benchmarkStartDate.model = new Date(
								batch.benchmarkStartDate.replace(/-/g, '/'));
						$scope.Save = "Update";
						$scope.Updating = true;
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
						$scope.Updating = false;
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

							if ($scope.trainers[i].name === trainer_name) {
								batch.trainer = $scope.trainers[i];
							}
							if ($scope.trainers[i].name === cotrainer_name) {
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
						// if current batch is being edited, update batch
						// otherwise create new batch
						if ($scope.Updating) {
							createBatchObject($scope.currentBatch);
							caliberDelegate.all
									.updateBatch($scope.currentBatch)
									.then(
											$scope.selectedBatches[$scope.batchRow] = $scope.currentBatch)
						} else {
							var newBatch = {};
							createBatchObject(newBatch);
							$log.log('this is' + newBatch);
							caliberDelegate.all.createBatch(newBatch).then(
									function(response) {
										// coTrainer may be undefined

										if ($scope.coTrainer) {
											$scope.batches.push(response.data);
										} else {
											$scope.batches.push(response.data);
											$log.log($scope.batches)
										}

										sortByDate($scope.selectedYear);
									});
						}
						angular.element("#createBatchModal").modal("hide");

					};

					/** Delete batch* */
					$scope.deleteBatch = function() {
						caliberDelegate.all
								.deleteBatch($scope.currentBatch.batchId)
								.then(
										function(response) {
											if (response.status === 204) {
												for (var i = 0; i < $scope.batches.length; i++) {
													if ($scope.batches[i] === $scope.currentBatch) {
														$scope.batches.splice(
																i, 1);
														break;
													}
												}
												$scope.selectedBatches.splice(
														$scope.row, 1);
											}
										});
						angular.element("#deleteBatchModal").modal("hide");
					}
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

					/** Get trainee info* */
					$scope.getTrainee = function(index) {

						$scope.currentTrainee = $scope.selectedBatches[$scope.batchRow].trainees[index];
						$scope.traineeRow = index;

					}

					// load training types
					caliberDelegate.all.enumTrainingStatus().then(
							function(statuses) {
								$log.debug(statuses);
								$scope.trainingStatuses.options = statuses;
							});

					/** Fill update form with trainee previous data* */
					$scope.populateTrainee = function(trainee) {
						$log.log(trainee);
						$scope.traineeName = trainee.name;
						$scope.traineeEmail = trainee.email;
						$scope.traineeSkypeId = trainee.skypeId;
						$scope.traineePhoneNumber = trainee.phoneNumber;
						$scope.traineeProfileUrl = trainee.profileUrl;
						$scope.traineeTrainingStatus = trainee.trainingStatus;
						$scope.Save = "Update";
						$scope.Updating = true;
						$scope.traineeFormName = "Update Trainee";
					}

					/** Resets trainee form for creating new trainee* */
					$scope.resetTraineeForm = function() {
						$scope.traineeFormName = "Add Trainee";
						$scope.traineeName = "";
						$scope.traineeEmail = "";
						$scope.traineeSkypeId = "";
						$scope.traineePhoneNumber = "";
						$scope.traineeProfileUrl = "";

						$scope.Save = "Save";
						$scope.Updating = false;
						if ($scope.currentTrainee) {
							$scope.currentTrainee = null;
						}
					}

					/** Create new Trainee Object * */
					function createTraineeObject(trainee) {
						trainee.name = $scope.traineeName;
						trainee.email = $scope.traineeEmail;
						trainee.skypeId = $scope.traineeSkypeId;
						trainee.phoneNumber = $scope.traineePhoneNumber;
						trainee.profileUrl = $scope.traineeProfileUrl;
						trainee.trainingStatus = $scope.traineeTrainingStatus;
						$log.log(trainee);
						trainee.batch = {
							batchId : $scope.currentBatch.batchId
						};

					}

					/** Save New Trainee Input * */
					$scope.addNewTrainee = function() {
						if ($scope.Updating) {
							$log.log($scope.currentTrainee);
							createTraineeObject($scope.currentTrainee);
							$log.log($scope.currentTrainee);
							caliberDelegate.all
									.updateTrainee($scope.currentTrainee)
									.then(
											$scope.selectedBatches[$scope.batchRow].trainees[$scope.traineeRow] = $scope.currentTrainee,
											$scope.Updating = false,
											$scope.resetTraineeForm())
						} else {
							var newTrainee = {};
							createTraineeObject(newTrainee);
							caliberDelegate.all.createTrainee(newTrainee).then(
									function(response) {
										$scope.trainees.push(response);
									});
						}
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
						$log.log(traineeId);
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
