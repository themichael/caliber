angular
		.module("qc")
		.controller(
				"qcAssessController",
				function($log, $scope, chartsDelegate, caliberDelegate,
						qcFactory, allBatches) {
					$log.debug("Booted Trainer Assess Controller");

					$scope.batches = allBatches;
					$scope.bnote = null;
					$scope.tnote = [];
					$scope.faces = [];
					$scope.weeks = [];
					
					function Note(noteId, content, status, week, batch, trainee) {
						this.noteId = noteId;
						this.content = content;
						this.status = status;
						this.week = week;
						this.batch = batch;
						this.trainee = trainee;
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
										});
						// Get qc notes for trainees in selected batch
						caliberDelegate.qc
								.traineeNote($scope.currentBatch.batchId, $scope.currentWeek)
								.then(
										function(notes) {
											//$scope.tnote = notes;
											for (i = 0; i < $scope.currentBatch.trainees.length; i++) {
												var content = null;
												var status = null;
												var id = null;
												for(j = 0; j < notes.length; j++) {
													/*$log.debug("$scope.currentBatch.trainees[i].name");
													$log.debug($scope.currentBatch.trainees[i].name);
													$log.debug("$scope.notes[j].trainee.name");
													$log.debug(notes[j].trainee.name);*/
													if($scope.currentBatch.trainees[i].name === notes[j].trainee.name) {
														content = notes[j].content;
														status = notes[j].qcStatus;
														id = notes[j].noteId;
													}
												}
												$scope.faces.push(new Note(id, content, status, $scope.currentWeek, $scope.currentBatch, $scope.currentBatch.trainees[i]));
											}
											$log.debug($scope.faces);
										});
					}
					
					$scope.pickIndividualStatus = function(trainee, status,
							index) {
						$scope.faces[index].status = status;
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
											});
							// Get qc notes for trainees in selected batch
							caliberDelegate.qc
									.traineeNote($scope.currentBatch.batchId, $scope.currentWeek)
									.then(
											function(notes) {
												$scope.tnote = notes;
											});
						} else {
							$log.debug("No weeks");
							$scope.bnote = null;
							$scope.tnote = [];
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
											});
							// Get qc notes for trainees in selected batch
							caliberDelegate.qc
									.traineeNote($scope.currentBatch.batchId, $scope.currentWeek)
									.then(
											function(notes) {
												$scope.tnote = notes;
											});
						} else {
							$scope.bnote = null;
							$scope.tnote = [];
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

					$scope.saveTraineeNotes = function(traineeName) {
						//$log.debug(index);
						//$log.debug(($scope.tnote));
						
						for(i = 0; i < $scope.tnote.length; i++) {
							if(traineeName === $scope.tnote[i].trainee.name) {
								caliberDelegate.qc.updateNote($scope.tnote[i]);
							}
						}
						
						// caliberDelegate.qc.updateNote($scope.tnote[index]);
					};
					
					$scope.saveQCNotes = function() {
						$log.debug(document.getElementById("qcBatchNotes").value);
						$log.debug(caliberDelegate.qc.updateNote($scope.bnote));
					}
				});