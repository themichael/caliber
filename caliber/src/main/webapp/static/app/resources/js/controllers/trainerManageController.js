/*******************************************************************************
 * Team: Fareed SSH 
 * Team Lead: Sudish 
 * Authors: Fareed Ali, 
 * Sean Connelly, chili
 * Sudish Itwaru, 
 * Hendy Guy
 * 
 * Fareed worked on delete functionality for batch and trainees,
 * Sean and Hendy worked on add and edit trainees
 * Sudish worked on add and edit batch
 ******************************************************************************/
/*******************************************************************************
 * Things to consider for the future(or leave it for the next batch): 1)try to
 * mimic inline edits for batch and trainee. Basically, when clicking edit on
 * row on batch or trainee, make that entire row into text inputs or dropdowns
 * for each field, and edit it there. after that, click the save button that
 * appears to return to normal view without edit boxes
 * 
 * 2)make add trainee form into slidedown on trainee view: when clicking add
 * trainee, form slides down in trainee view instead of using a pop up. can keep
 * ading tainees by pressing enter. after finish entering trainee for batch,
 * close trainee form. so after pressing enter or save, trainee is added
 * automagically to batch and trainee form is blank and ready for next trainee
 * info to be entered
 * 
 * 3) nothing else, maybe change function and scope objects as you wish, maybe
 * separate this giant javascript file into separate js file and attach to each
 * view for manage batch inside routeConfig.js
 ******************************************************************************/

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
					(function() {
						caliberDelegate.all.getAllTrainers().then(
								function(trainers) {
									$scope.trainers = trainers;
									$log.debug("=========TRAINERS=========");
									$log.debug(trainers);
								});
					
						caliberDelegate.all.importAvailableBatches().then(
								function(availableBatches){
									$scope.allAvailableBatches = availableBatches;
									$log.debug("=============IMPORT BATCHES==========")
									$log.debug(availableBatches);
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

					/** Set end date in form* */
					$scope.setEndDate = function() {
						$scope.endDate.model = $scope.startDate.model;
					};

					/** prevent end date being before startdate* */
					$scope.preventEndDate = function() {
						if ($scope.endDate.model < $scope.startDate.model) {
							$scope.setEndDate();
						}
					};

					/** Set minimum grade* */
					$scope.setMinGrade = function() {
						$scope.borderlineGradeThreshold.model = $scope.goodGradeThreshold.model;
					}

					/** prevent minimum grade being more than good grade* */
					$scope.lowerMinGrade = function() {
						if ($scope.borderlineGradeThreshold.model > $scope.goodGradeThreshold.model) {
							$scope.setMinGrade();
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
					/** status check if updating or not for batch and trainees* */
					$scope.Updating = {
						status : false
					};

					/** Get batches for user and trainees in each batch * */
					$scope.selectCurrentBatch = function(index) {
						$scope.currentBatch = $scope.selectedBatches[index];
						$scope.activeTrainees = $scope.selectedBatches[index].trainees;
						$scope.trainees = $scope.activeTrainees;
						caliberDelegate.all.getDroppedTrainees(
								$scope.currentBatch.batchId).then(
								function(data) {
									$scope.droppedTrainees = data;
								});

						// caliberdlegeate get trainees by batch id and load nto
						$scope.batchRow = index;
						$scope.showdropped = false;

						$log.debug($scope.currentBatch);
					};

					/** Validation for the dates * */
					$scope.checkDates = function() {

						$scope.startDate.model = new Date(moment(
								$scope.startDate.model, "YYYY-MM-DD").format(
								"YYYY/MM/DD"));

						$scope.endDate.model = new Date(moment(
								$scope.endDate.model, "YYYY-MM-DD").format(
								"YYYY/MM/DD"));

						$log.info($scope.startDate);

						if ($scope.endDate.model > $scope.startDate.model
								&& $scope.trainer.model !== $scope.coTrainer.model) {
							$log.debug("True");
							$scope.addNewBatch();
						} else {
							$log.info("False");
							angular.element("#checkBatchModal").modal("show");
							return false;
						}

						$log.info($scope.validDate);

					}

					/**
					 * variable to determine if trainee or batch is being
					 * updated or created*
					 */

					/** Fill update form with batch previous data* */
					$scope.populateBatch = function(batch) {
						$log.debug(batch);
						$scope.Save = "Update";
						$scope.Updating.status = true;
						$scope.batchFormName = "Update Batch";
						$scope.trainingName.model = batch.trainingName;
						$scope.trainingType.model = batch.trainingType
						$scope.skillType.model = batch.skillType;
						$scope.location.model = batch.location;
						$log.debug("=====testbah=============")
						$log.debug(batch);
						$scope.trainer.model = batch.trainer.name;
						if (batch.coTrainer) {
							$scope.coTrainer.model = batch.coTrainer.name;
						} else {
							$scope.coTrainer.model = ""
						}

						$scope.startDate.model = new Date(moment(
								batch.startDate, "YYYY-MM-DD").format(
								"YYYY/MM/DD"));
						$scope.endDate.model = new Date(moment(batch.endDate,
								"YYYY-MM-DD").format("YYYY/MM/DD"));
						$scope.goodGradeThreshold.model = batch.goodGradeThreshold;
						$scope.borderlineGradeThreshold.model = batch.borderlineGradeThreshold;

					}

					
					/** Selected import batch**/
					 $scope.selectedBatchToImport = function(){
						$scope.batchToImport = this.selectedBatch;
						caliberDelegate.all.getAllTraineesFromBatch(this.selectedBatch.resourceId).then(
								function(trainees){
									$scope.traineeToImport = trainees;
									$log.debug("============TRAINEES============");
									$log.debug(trainees)
					 	});
					 };
					 
					/** Import batch form for creating new batch**/
					$scope.importBatchForm = function() {
						$scope.batchFormName = "Import New Batch"
						$scope.Save = "Save";
						
					}			
					/** Select batch by year **/
					$scope.selectBatchYear = function(index) {
						$scope.selectedBatchYear = $scope.years[index];
						sortByDate($scope.selectedBatchYear);
					};
					
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
						$scope.Save = "Save";
						$scope.Updating.status = false;
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
							if ($scope.trainers[i].name === cotrainer_name
									&& cotrainer_name !== trainer_name) {
								batch.coTrainer = $scope.trainers[i];
							}
						}

						// return newBatch;
					}
					/** reformat dates on batch correctly* */
					/** Save Batch * */
					$scope.addNewBatch = function() {
						// Ajax call check for 200 --> then assemble batch
						// if current batch is being edited, update batch
						// otherwise create new batch
						$log.debug("current satus of Updating.status scope"
								+ $scope.Updating.status.status);
						if ($scope.Updating.status) {
							createBatchObject($scope.currentBatch);
							caliberDelegate.all
									.updateBatch($scope.currentBatch)
									.then(
											function() {
											
												$scope.selectedBatches[$scope.batchRow] = $scope.currentBatch
											});

						} else {
							var newBatch = {};
							createBatchObject(newBatch);
							$log.debug('this is');
							$log.debug(newBatch);
							caliberDelegate.all
									.createBatch(newBatch)
									.then(
											function(response) {
												// set new batch id with
												// whatever was inserted into
												// database
												newBatch.batchId = response.data.batchId;
												// create empty array for
												// trainees so trainees can be
												// added immediately
												newBatch['trainees'] = [];
												// create empty array for weeks,
												// so weeks can be added
												// immediately
												newBatch['arrayWeeks'] = [];
												newBatch['weeks'] = response.data.weeks;
												// format dates so qc, assess
												// and reports can access
												// batches immediately
											

												$scope.batches.push(newBatch);

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
											// if delete successful, remove from
											// batch table
											if (response.status === 204) {
												for (var i = 0; i < $scope.batches.length; i++) {
													if ($scope.batches[i] === $scope.currentBatch) {
														$scope.batches.splice(
																i, 1);
														break;
													}
												}
												for (var j = 0; j < $scope.selectedBatches.length; j++) {
													if ($scope.selectedBatches[j] === $scope.currentBatch) {
														$scope.selectedBatches
																.splice(j, 1);
														break;
													}
												}
											} else if (response.status === 500) {
												angular
														.element(
																"#deleteBatchErrorModal")
														.modal("show");
											}
										});
						angular.element("#deleteBatchModal").modal("hide");
					}
					/**
					 * ************************** Trainees
					 * ****************************
					 */

					/** create scopes for trainee form* */
					$scope.traineeForm = {
						name : null,
						email : null,
						skypeId : null,
						phoneNumber : null,
						profileUrl : null,
						trainingStatus : null
					}

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

						$scope.currentTrainee = $scope.trainees[index];
						$scope.traineeRow = index;

					}

					/** switch to dropped trainees* */
					$scope.switchTraineeView = function() {
						if ($scope.showdropped) {
							$scope.trainees = $scope.activeTrainees;
							$scope.showdropped = false;
						} else {
							$scope.trainees = $scope.droppedTrainees;
							$scope.showdropped = true;
						}

					};

					// load training types
					caliberDelegate.all.enumTrainingStatus().then(
							function(statuses) {
								$log.debug(statuses);
								$scope.trainingStatuses.options = statuses;
							});

					/** Fill update form with trainee's previous data* */
					$scope.populateTrainee = function(trainee) {
						$log.debug(trainee);
						$scope.traineeForm.name = trainee.name;
						$scope.traineeForm.email = trainee.email;
						$scope.traineeForm.skypeId = trainee.skypeId;
						$scope.traineeForm.phoneNumber = trainee.phoneNumber;
						$scope.traineeForm.profileUrl = trainee.profileUrl;
						$scope.traineeForm.trainingStatus = trainee.trainingStatus;
						$scope.Save = "Update";
						$scope.Updating.status = true;
						$scope.traineeFormName = "Update Trainee";
					}

					/** Resets trainee form for creating new trainee* */
					$scope.resetTraineeForm = function() {
						$scope.traineeFormName = "Add Trainee";
						$scope.traineeForm.name = "";
						$scope.traineeForm.email = "";
						$scope.traineeForm.skypeId = "";
						$scope.traineeForm.phoneNumber = "";
						$scope.traineeForm.profileUrl = "";
						$scope.traineeForm.trainingStatus = "";
						$scope.Save = "Save";
						$scope.Updating.status = false;
						if ($scope.currentTrainee) {
							$scope.currentTrainee = null;
						}
					}

					/** checks if email already exists in database* */
					$scope.verifyTraineeEmail = function() {
						if (!$scope.Updating.status) {
							$scope.checkEmail();
						} else {
							if ($scope.currentTrainee.email !== $scope.traineeForm.email) {
								$scope.checkEmail();
							} else {
								$scope.addNewTrainee();
							}
						}
					}

					/** show email verification modal* */
					$scope.checkEmail = function() {
						$log.log("inside check email");
						$log.log($scope.traineeForm.email);
						$log.log("inside check email");
						caliberDelegate.all.getTraineeByEmail(
								$scope.traineeForm.email).then(
								function(response) {
									$log.debug("find email response ")
									$log.debug(response)
									if (response.data === "") {
										$log.debug("email does not exist")
										$scope.addNewTrainee();
									} else {
										$log.debug("email already exists")
										angular.element(
												"#emailVerificationModal")
												.modal("show");
										return false;
									}
								})
					}
					/** Create new Trainee Object * */
					function createTraineeObject(trainee) {
						trainee.name = $scope.traineeForm.name;
						trainee.email = $scope.traineeForm.email;
						trainee.skypeId = $scope.traineeForm.skypeId;
						trainee.phoneNumber = $scope.traineeForm.phoneNumber;
						trainee.profileUrl = $scope.traineeForm.profileUrl;
						trainee.trainingStatus = $scope.traineeForm.trainingStatus;
						$log.debug(trainee);
						trainee.batch = {
							batchId : $scope.currentBatch.batchId
						};

					}

					/** Save New Trainee Input * */
					$scope.addNewTrainee = function() {
						if ($scope.Updating.status) {
							createTraineeObject($scope.currentTrainee);
							caliberDelegate.all
									.updateTrainee($scope.currentTrainee)
									.then(
											function() {
												$scope.Updating.status = false;
												$scope.trainees[$scope.traineeRow] = $scope.currentTrainee;
												$scope.resetTraineeForm();
												// if trainee is dropped, splice
												// from allbatches list and add
												// to drop list
												if ($scope.trainees[$scope.traineeRow].trainingStatus === "Dropped") {
													for (var i = 0; i < $scope.activeTrainees.length; i++) {
														if ($scope.activeTrainees[i].traineeId === $scope.trainees[$scope.traineeRow].traineeId) {
															$scope.droppedTrainees
																	.push($scope.trainees[$scope.traineeRow]);
															$scope.activeTrainees
																	.splice(i,
																			1);

														}
													}
												} else {
													for (var j = 0; j < $scope.droppedTrainees.length; j++) {
														if ($scope.droppedTrainees[j].traineeId === $scope.trainees[$scope.traineeRow].traineeId) {
															$scope.activeTrainees
																	.push($scope.trainees[$scope.traineeRow]);
															$scope.droppedTrainees
																	.splice(j,
																			1);
														}
													}
												}
											});
						} else {
							var newTrainee = {};
							createTraineeObject(newTrainee);
							caliberDelegate.all
									.createTrainee(newTrainee)
									.then(
											function(response) {
												// if successfully inserted into
												// db, trainee is added to table
												if (response.status === 201) {
													if (response.data.trainingStatus === "Dropped") {
														$scope.droppedTrainees
																.push(response.data);

													} else {
														$scope.activeTrainees
																.push(response.data);
													}
												}
												$scope.resetTraineeForm();
											});
						}
						angular.element("#addTraineeModal").modal("hide");
					};

					/** Get Trainee to delete* */
					$scope.getTraineeToDelete = function(index) {
						$scope.traineeToBeDeleted = $scope.trainees[index];
						$scope.traineeRow = index;
					}

					/** Delete Trainee* */

					$scope.removeTrainee = function() {
						// search through allbatches trainees and splice from
						// there
						$log.debug("Deleting trainee with id of:  "
								+ $scope.traineeToBeDeleted.traineeId);

						caliberDelegate.all
								.deleteTrainee($scope.traineeToBeDeleted.traineeId);
						$scope.currentBatch.trainees.splice($scope.traineeRow,
								1);
						angular.element("#deleteTraineeModal").modal("hide");

					};
					
					/** When multiple modals are opened upon removing one the modal-open is removed.
					 *  The following code adds the modal-open back into the HTML */
					
					$(document).on('hidden.bs.modal','#addTraineeModal', function () {
						$("body").addClass("modal-open");
					});
					$(document).on('hidden.bs.modal','#deleteTraineeModal', function () {
						$("body").addClass("modal-open");
					});

				});
