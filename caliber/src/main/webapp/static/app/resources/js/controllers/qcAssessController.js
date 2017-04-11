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
					function Note(noteId, content, status, week, batch,
							trainee, maxVisibility, type, qcFeedback) {
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
						$scope.saveQCNotes();
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
					for (var i = 1; i <= $scope.currentBatch.weeks; i++) {
						$scope.weeks.push(i);
					}

					// function to get notes
					$scope.getNotes = function() {
						// Check if there are not weeks
						if ($scope.currentWeek !== undefined) {
							// Get qc notes for selected batch
							caliberDelegate.qc
									.batchNote($scope.currentBatch.batchId,
											$scope.currentWeek)
									.then(
											function(notes) {
												// If no batch note found create
												// empty note
												if (notes === "") {
													$log.debug("EMPTY!");
													$scope.bnote = new Note(null,
															null, null,
															$scope.currentWeek,
															$scope.currentBatch,
															null, "QC", "QC_BATCH",
															true);
												}
												// If note found set to note and
												// face
												else {
													$scope.bnote = notes;
													$scope.qcBatchAssess = notes.qcStatus;
												}
											});
							// Get qc notes for trainees in selected batch
							caliberDelegate.qc
									.traineeNote($scope.currentBatch.batchId,
											$scope.currentWeek)
									.then(
											function(notes) {
												for (var i = 0; i < $scope.currentBatch.trainees.length; i++) {
													var content = null;
													var status = null;
													var id = null;
													// Set content, status and id to note if found
													for (var j = 0; j < notes.length; j++) {
														if ($scope.currentBatch.trainees[i].name === notes[j].trainee.name) {
															content = notes[j].content;
															status = notes[j].qcStatus;
															id = notes[j].noteId;
															break;
														}
													}
													// Push note object into array, batch set to null for trainee note always
													$scope.faces
															.push(new Note(
																	id,
																	content,
																	status,
																	$scope.currentWeek,
																	null,
																	$scope.currentBatch.trainees[i],
																	"QC",
																	"QC_TRAINEE",
																	true));
												}
											});
						// If there are no weeks
						} else {
							$scope.bnote = null;
							for (var i = 0; i < $scope.currentBatch.trainees.length; i++) {
								$scope.faces.push(new Note(null, null, null,
										$scope.currentWeek, $scope.currentBatch,
										$scope.currentBatch.trainees[i], "QC",
										"QC_TRAINEE", true));
							}
						}
					}
					
					// Set current week to first week
					$scope.currentWeek = $scope.weeks[0];
					// Get notes
					$scope.getNotes();

					$scope.pickIndividualStatus = function(trainee, status,
							index) {
						$scope.faces[index].qcStatus = status;
						$scope.saveTraineeNote(index);
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
						for (var i = 1; i <= $scope.currentBatch.weeks; i++) {
							$scope.weeks.push(i);
						}
						// Set current week to first week
						$scope.currentWeek = $scope.weeks[0];
						$scope.getNotes();
						wipeFaces();
					};

					// Select week
					$scope.selectWeek = function(index) {
						$scope.currentWeek = $scope.weeks[index];
						$scope.getNotes();
						wipeFaces();
					};

					// Show week
					$scope.showActiveWeek = function(index) {
						if ($scope.currentWeek === $scope.weeks[index])
							return "active";
					}

					$scope.createWeek = function() {
						caliberDelegate.trainer
								.createWeek($scope.currentBatch.batchId)
								.then(
										function(response) {
											$scope.currentBatch.weeks += 1;
											$scope.weeks
													.push($scope.currentBatch.weeks);
											$scope
													.showActiveWeek($scope.currentBatch.weeks);
											$scope
													.selectWeek($scope.currentBatch.weeks - 1); // the
											// new
											// index
											// of
											// the
											// week
											// selected
										});
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

					/**
					 * ******************************************* QCFeedBack
					 * **********************************************************
					 */
					var readMe = false;
					$scope.readOnlyPage = function() {
						readMe = !readMe;
						$log.debug(readMe);
						return readMe;
					}

					$scope.saveTraineeNote = function(index) {
						$log.debug($scope.faces[index]);
						// Create if noteId is null so nothing in database
						if ($scope.faces[index].noteId === null) {
							$log.debug("create");
							caliberDelegate.qc.createNote($scope.faces[index])
									.then(function(id) {
										$scope.faces[index].noteId = id;
									});
						}
						// Update if noteId is there
						else {
							$log.debug("update");
							caliberDelegate.qc.updateNote($scope.faces[index]);
						}
					};

					$scope.saveQCNotes = function() {
						// Create note
						if ($scope.bnote.noteId === null) {
							caliberDelegate.qc.createNote($scope.bnote).then(
							// Set id to created notes id
							function(id) {
								$scope.bnote.noteId = id;
							});
						}
						// Update existing note
						else {
							$log
									.debug(document
											.getElementById("qcBatchNotes").value);
							$log.debug(caliberDelegate.qc
									.updateNote($scope.bnote));
						}
					}

					$scope.saveQCandTrainee = function() {
						$log.debug($scope.faces);

						$log.debug("update");
						caliberDelegate.qc.updateNote($scope.faces);

						$log.debug("create");
						caliberDelegate.qc.createNote($scope.faces);

						$log
								.debug(document.getElementById("qcBatchNotes").value);
						$log.debug(caliberDelegate.qc.updateNote($scope.bnote));
					}
				});