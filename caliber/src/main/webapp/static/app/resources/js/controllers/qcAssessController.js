angular
		.module("qc")
		.controller(
				"qcAssessController",
				function($log, $scope, chartsDelegate, caliberDelegate,
						qcFactory, allBatches) {
					$log.debug("Booted Trainer Assess Controller");

					$scope.batches = allBatches;
					$scope.bnote = [];
					$scope.tnote = [];
					$scope.weeks = [];

					/**
					 * ****************** Weeks
					 * *************************************
					 */
					function Week(weekNumber, note) {
						this.weekNumber = weekNumber;
						this.note = note;
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

					// /////////////////////// overall
					// feedback//////////////////////////
					$scope.finalQCBatchNote = null;
					$scope.pickOverallStatus = function(batch, pick) {
						$scope.qcBatchAssess = pick;
						$log.debug(batch.trainingName + " " + pick);
					};

					// /////////////////////// individual
					// feedback/////////////////////

					// used to store which rows have what faces/value
					$scope.faces = [];
					$scope.pickIndividualStatus = function(trainee, status,
							index) {
						$log.debug(trainee);
						$log.debug(status);
						// do your logic to update this trainee feedback

						// update face
						$scope.faces[index] = status;
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
					for (i = 1; i <= $scope.currentBatch.weeks; i++) {
						$scope.weeks.push(new Week(i, []));
					}
					$scope.currentWeek = $scope.weeks[0];
					caliberDelegate.qc
							.batchNote($scope.currentBatch.batchId)
							.then(
									function(notes) {
										$scope.bnote = notes;
										for (i = 0; i < $scope.bnote.length; i++) {
											$scope.weeks[$scope.bnote[i].week - 1].note
													.push($scope.bnote[i]);
										}
									});
					caliberDelegate.qc
							.traineeNote($scope.currentBatch.batchId)
							.then(
									function(notes) {
										$scope.tnote = notes;
										for (i = 0; i < $scope.tnote.length; i++) {
											$scope.weeks[$scope.tnote[i].week - 1].note
													.push($scope.tnote[i]);
										}
									});

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
							$scope.weeks.push(new Week(i, []));
						}
						// Set current week to first week
						$scope.currentWeek = $scope.weeks[0];
						// Get qc notes for selected batch
						caliberDelegate.qc
								.batchNote($scope.currentBatch.batchId)
								.then(
										function(notes) {
											$scope.bnote = notes;
											$log.debug("Batch QC Notes");
											$log.debug($scope.bnote);
											// Put qc notes into week object
											for (i = 0; i < $scope.bnote.length; i++) {
												$scope.weeks[$scope.bnote[i].week - 1].note
														.push($scope.bnote[i]);
											}
											$log.debug("weeks");
											$log.debug($scope.weeks);
										});
						// Get qc notes for trainees in selected batch
						caliberDelegate.qc
								.traineeNote($scope.currentBatch.batchId)
								.then(
										function(notes) {
											$scope.tnote = notes;
											$log.debug("Batch Trainee Notes");
											$log.debug($scope.tnote);
											// Put qc notes into week object
											for (i = 0; i < $scope.tnote.length; i++) {
												$scope.weeks[$scope.tnote[i].week - 1].note
														.push($scope.tnote[i]);
											}
											$log.debug("weeks");
											$log.debug($scope.weeks);
										});
						wipeFaces();
					};

					// Select week
					$scope.selectWeek = function(index) {
						$scope.currentWeek = $scope.weeks[index]
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
					$scope.noteOnTrainee = function(traineeName) {

						// $log.debug($scope.tnote);
						for (i = 0; i < $scope.tnote.length; i++)
							if (traineeName == $scope.tnote[i].trainee.name)
								return $scope.tnote[i].content;
						return "Note on " + traineeName;
					};

					$scope.inputData = [];

					$scope.addedNotes = function(index) {
						$log.debug(index + ": " + $scope.inputData[index]);
					};

					$scope.reset = function() {
						document.getElementById("noteTextArea").value = null;
						$(this).val("");
					};
				});
