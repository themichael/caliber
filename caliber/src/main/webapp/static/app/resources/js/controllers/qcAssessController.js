angular
		.module("qc")
		.controller(
				"qcAssessController",
				function($log, $scope, chartsDelegate, caliberDelegate,
						qcFactory, allBatches) {
					$log.debug("Booted Trainer Assess Controller");

					$scope.batches = allBatches;
					$scope.bnote = null;
					$scope.faces = [];
					$scope.weeks = [];
					
					// Note object
					function Note(noteId, content, status, week, batch, trainee, maxVisibility, type, qcFeedback) {
						this.noteId = noteId;
					    this.content = content;
					    this.week = week;
					    this.batch = batch;
					    this.trainee = trainee;
					    this.maxVisibility = maxVisibility;
					    this.type = type;
					    this.qcFeedback = qcFeedback;
					    this.qcStatus = status;
					}

					/**
					 * ***************************************** UI
					 * **********************************************
					 */
					// get status types
					$scope.qcStatusTypes = [];
					caliberDelegate.all.enumQCStatus().then(function(types) {
						$log.debug(types);
						$scope.qcStatusTypes = types;
					});

					// Used to pick face for batch
					$scope.pickOverallStatus = function(batch, pick) {
						$scope.qcBatchAssess = pick;
						$log.debug(batch.trainingName + " " + pick);
						$log.debug("bnote");
						$log.debug($scope.bnote);
						$scope.bnote.qcStatus = pick;
						caliberDelegate.qc.updateNote($scope.bnote);
					};


					// ///////////////////////////////////////////////////////////////////////////////////////////
					// load note types
					caliberDelegate.all.enumNoteType().then(
							function(noteTypes) {
								$log.debug(noteTypes);
								// do something with note type
							});

					// starting scope vars
					$scope.currentBatch = $scope.batches[0];
					// create an array of numbers for number of weeks
					for (i = 1; i <= $scope.currentBatch.weeks; i++) {
						$scope.weeks.push(i);
					}
					
					// Set current week to first week
					$scope.currentWeek = $scope.weeks[0];
					// Check if there are no weeks
					if($scope.currentWeek !== undefined) {
						// Get qc notes for selected batch
						caliberDelegate.qc
								.batchNote($scope.currentBatch.batchId, $scope.currentWeek)
								.then(
										function(notes) {
											$scope.bnote = notes;
											$scope.qcBatchAssess = notes.qcStatus;
										});
						// Get qc notes for trainees in selected batch
						caliberDelegate.qc
								.traineeNote($scope.currentBatch.batchId, $scope.currentWeek)
								.then(
										function(notes) {
											for (i = 0; i < $scope.currentBatch.trainees.length; i++) {
												var content = null;
												var status = null;
												var id = null;
												for(j = 0; j < notes.length; j++) {
													if($scope.currentBatch.trainees[i].name === notes[j].trainee.name) {
														content = notes[j].content;
														status = notes[j].qcStatus;
														id = notes[j].noteId;
														break;
													}
												}
												$scope.faces.push(new Note(id, content, status, $scope.currentWeek, $scope.currentBatch, $scope.currentBatch.trainees[i], "QC", "QC_TRAINEE", true));
											}
										});
					} else {
						$scope.bnote = null;
						for (i = 0; i < $scope.currentBatch.trainees.length; i++) {
							$scope.faces.push(new Note(null, null, null, $scope.currentWeek, $scope.currentBatch, $scope.currentBatch.trainees[i], "QC", "QC_TRAINEE", true));
						}
					}
					
					$log.debug($scope.faces);
					
					$scope.pickIndividualStatus = function(trainee, status,
							index) {
						$scope.faces[index].qcStatus = status;
						caliberDelegate.qc.updateNote($scope.faces[index]);
						$log.debug($scope.faces[index]);
					};
					
					// default -- view assessments table
					$scope.currentView = true;

					// back button
					$scope.back = function() {
						$scope.currentView = true;
					};

					// batch drop down select
					$scope.selectCurrentBatch = function(index) {
						$log.debug("SELECTED DIFFERENT BATCH");
						$scope.currentBatch = $scope.batches[index];
						// Create week array for batch selected
						$scope.weeks = [];
						for (i = 1; i <= $scope.currentBatch.weeks; i++) {
							$scope.weeks.push(i);
						}
						// Set current week to first week
						$scope.currentWeek = $scope.weeks[0];
						// Check if there are no weeks
						if($scope.currentWeek !== undefined) {
							// Get qc notes for selected batch
							caliberDelegate.qc
									.batchNote($scope.currentBatch.batchId, $scope.currentWeek)
									.then(
											function(notes) {
												$scope.bnote = notes;
												$scope.qcBatchAssess = notes.qcStatus;
											});
							// Get qc notes for trainees in selected batch
							caliberDelegate.qc
									.traineeNote($scope.currentBatch.batchId, $scope.currentWeek)
									.then(
											function(notes) {
												for (i = 0; i < $scope.currentBatch.trainees.length; i++) {
													var content = null;
													var status = null;
													var id = null;
													for(j = 0; j < notes.length; j++) {
														if($scope.currentBatch.trainees[i].name === notes[j].trainee.name) {
															content = notes[j].content;
															status = notes[j].qcStatus;
															id = notes[j].noteId;
															break;
														}
													}
													$scope.faces.push(new Note(id, content, status, $scope.currentWeek, $scope.currentBatch, $scope.currentBatch.trainees[i], "QC", "QC_TRAINEE", true));
												}
											});
						} else {
							$log.debug("No weeks");
							$scope.bnote = null;
							for (i = 0; i < $scope.currentBatch.trainees.length; i++) {
								$scope.faces.push(new Note(null, null, null, $scope.currentWeek, $scope.currentBatch, $scope.currentBatch.trainees[i], "QC", "QC_TRAINEE", true));
							}
						}
						wipeFaces();
					};

					// Select week
					$scope.selectWeek = function(index) {
						$scope.currentWeek = $scope.weeks[index];
						// Check if there are no weeks
						if($scope.currentWeek !== undefined) {
							// Get qc notes for selected batch
							caliberDelegate.qc
									.batchNote($scope.currentBatch.batchId, $scope.currentWeek)
									.then(
											function(notes) {
												$scope.bnote = notes;
												$scope.qcBatchAssess = notes.qcStatus;
											});
							// Get qc notes for trainees in selected batch
							caliberDelegate.qc
									.traineeNote($scope.currentBatch.batchId, $scope.currentWeek)
									.then(
											function(notes) {
												for (i = 0; i < $scope.currentBatch.trainees.length; i++) {
													var content = null;
													var status = null;
													var id = null;
													for(j = 0; j < notes.length; j++) {
														if($scope.currentBatch.trainees[i].name === notes[j].trainee.name) {
															content = notes[j].content;
															status = notes[j].qcStatus;
															id = notes[j].noteId;
															break;
														}
													}
													$scope.faces.push(new Note(id, content, status, $scope.currentWeek, $scope.currentBatch, $scope.currentBatch.trainees[i], "QC", "QC_TRAINEE", true));
												}
											});
						} else {
							$scope.bnote = null;
							for (i = 0; i < $scope.currentBatch.trainees.length; i++) {
								$scope.faces.push(new Note(null, null, null, $scope.currentWeek, $scope.currentBatch, $scope.currentBatch.trainees[i], "QC", "QC_TRAINEE", true));
							}
						}
						wipeFaces();
					};

					// Show week
					$scope.showActiveWeek = function(index) {
						if ($scope.currentWeek === $scope.weeks[index])
							return "active";
					}

					// create week
					$scope.createWeek = function() {
						var weekNumber;
						if (!$scope.weeks)
							weekNumber = 1;
						else
							weekNumber = $scope.weeks.length + 1;
						$log.debug(weekNumber);
						/*
						 * var weekObj = { weekNumber: weekNumber, batch:
						 * $scope.currentBatch, topics:null };
						 * caliberDelegate.trainer.createWeek(weekObj).then(function
						 * (response) { pushUnique($scope.currentBatch.weeks, {
						 * weekId:response, weekNumber: weekNumber, batch: null,
						 * topics:null });
						 * $log.debug($scope.currentBatch.weeks); });
						 */
					};

					// ///// wipe faces ;) and selections ///////
					function wipeFaces() {
						$scope.faces = [];
						$scope.qcBatchAssess = null;
						$scope.finalQCBatchNote = null;
					}

					/**
					 * ************************************************ GETTING
					 * NOTES ON TRAINEE
					 * *********************************************
					 */
					// Note for trainee
					$scope.noteOnTrainee = function(index) {
						return $scope.faces[index];
					};

	/********************************************* QCFeedBack ***********************************************************/
					$scope.showCurrentBatch = function() {
						/*{{ currentBatch.trainingName }} - {{
							currentBatch.startDate | date:'shortDate' }}*/
						$log.debug($scope.currentBatch[$scope.currentBatch.length - 1].trainingName);
					}
					
					$scope.saveTraineeNote = function(index) {
						//$log.debug(index);
						$log.debug($scope.faces[index]);
						if($scope.faces[index].id !== null) {
							$log.debug("update");
							caliberDelegate.qc.updateNote($scope.faces[index]);
						}
						else{
							$log.debug("create");
							caliberDelegate.qc.createNote($scope.faces[index]);
						}
					};
					
					$scope.saveQCNotes = function() {
						$log.debug(document.getElementById("qcBatchNotes").value);
						$log.debug(caliberDelegate.qc.updateNote($scope.bnote));
					}
				});