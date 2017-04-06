/**
 * Refactor to use week index instead of Week object
 */
angular
		.module("trainer")
		.controller(
				"trainerAssessController",
				function($log, $scope, chartsDelegate, caliberDelegate,
						allBatches) {
					// Week object
					function Week(weekNumb, assessments) {
						this.weekNumb = weekNumb;
						this.assessments = assessments;
					}
					$log.debug("Booted Trainer Aesess Controller");

					// load categories
					$scope.skill_categories = function() {
						caliberDelegate.all.getAllCategories().then(
								function(categories) {
									$scope.categories = categories;
									$log.debug("all Categories");
									$log.debug(categories);
								});
					};
					/**
					 * ***************************************** UI
					 * ********************************************
					 */
					// ////////////////////////////////////////////////////////////////////////
					// load note types
					caliberDelegate.all.enumNoteType().then(
							function(noteTypes) {
								$log.debug(noteTypes);
								// do something with note type
							});
					$scope.assessmentType = {
						model : null,
						options : []
					};
					caliberDelegate.all
							.enumAssessmentType()
							.then(
									function(assessmentTypes) {
										$log.debug(assessmentTypes);
										$scope.assessmentType.options = assessmentTypes;
									});
					$log.debug("Batches " + allBatches);
					$log.debug(allBatches);
					(function start(allBatches) {
						$scope.batches = allBatches;
						if (allBatches.length > 0) {
							$scope.currentBatch = allBatches[0];
							$log.debug("This is the current batch "
									+ $scope.currentBatch);
							if (allBatches[0].weeks > 0) {
								// TODO check if it is sorting as objects-->
								// allBatches[0].weeks.sort(weekComparator);

								/**
								 * ****************************TEST
								 * DATA**********************
								 */

								$scope.category = {
									model : null,
									options : []
								};
								caliberDelegate.all
										.getAllCategories()
										.then(
												function(categories) {
													$log.debug(categories);
													$scope.category.options = categories;
												});

								/**
								 * ***************************************** UI
								 * ********************************************
								 */
								// ////////////////////////////////////////////////////////////////////////
								// load note types
								caliberDelegate.all.enumNoteType().then(
										function(noteTypes) {
											$log.debug(noteTypes);
											// do something with note type
										});
								$scope.assessmentType = {
									model : null,
									options : []
								};
								caliberDelegate.all
										.enumAssessmentType()
										.then(
												function(assessmentTypes) {
													$log.debug(assessmentTypes);
													$scope.assessmentType.options = assessmentTypes;
												});
								$log.debug("Batches " + allBatches);
								$log.debug(allBatches);

								var totalWeeks = allBatches[0].weeks; // the
								// number
								// of
								// weeks
								// for
								// that
								// batch
								$log
										.debug("this is the total week for this batch "
												+ allBatches[0].trainingName
												+ ": " + totalWeeks);

								$scope.currentWeek = totalWeeks;
								/**
								 * *****************************************
								 * getAllAssessmentsForWeek
								 * ***************************************************************************************88888******************************************************************************************************************************************************************************************
								 */
								caliberDelegate.trainer
										.getAllAssessmentsForWeek(
												$scope.currentBatch.batchId,
												$scope.currentWeek)
										.then(
												function(data) {
													$log
															.debug("Week "
																	+ 1
																	+ " Assessments for "
																	+ $scope.currentBatch.batchId);
													$log.debug(data);
													$scope.currentAssessments = data;
													$scope.getAllAssessmentsForWeek = function(
															batchId, week) {
														if (!week)
															return;
														caliberDelegate.trainer
																.getAllAssessmentsForWeek(
																		batchId,
																		week)
																.then(
																		function(
																				data) {
																			$log
																					.debug("Week "
																							+ 1
																							+ " Assessments for "
																							+ $scope.currentBatch.batchId);
																			$scope.currentAssessments = data;
																		});
													};
												});
								var week = new Week($scope.currentWeek,
										$scope.currentAssessments);
								$scope.currentBatch.displayWeek = week;

								getAllGradesForWeek();

								$log
										.debug("[THIS IS THE CURRENT BATCH AND THE NUMBER WEEK]"
												+ allBatches[0]
												+ " : "
												+ totalWeeks);
							} else
								$scope.currentWeek = null;
						} else {
							$scope.currentBatch = null;
							$scope.currentWeek = null;
						}
						/**
						 * **********************************************TODO
						 * REFACTOR**************************************
						 */
						$log
								.debug("Starting Values: currentBatch and currentWeek");
						$log.debug($scope.currentBatch);
						$log.debug($scope.currentWeek);
					})(allBatches);

					// default -- view assessments table
					$scope.currentView = true;

					// back button
					$scope.back = function() {
						$scope.currentView = true;
					};

					// batch drop down select
					$scope.selectCurrentBatch = function(index) {
						$scope.currentBatch = $scope.batches[index];
						$log.debug("Selected batch " + index);
						/**
						 * **********************************************TODO
						 * REFACTOR**************************************
						 */
						if ($scope.currentBatch.weeks > 0) {
							// $scope.currentBatch.weeks.sort(weekComparator);
							$scope.currentWeek = $scope.currentBatch.weeks;
						} else
							$scope.currentWeek = null;
						/**
						 * **********************************************TODO
						 * REFACTOR**************************************
						 */
						$scope
								.getAllAssessmentsForWeek(
										$scope.currentBatch.batchId,
										$scope.currentWeek);
					};

					// select week
					$scope.selectWeek = function(index) {
					
						$scope.currentWeek = $scope.currentBatch.arrayWeeks[index];
						$log.debug("[***********This is the week selected*************]:  "+$scope.currentWeek);
	
						getAllAssessmentsForWeek($scope.currentBatch.batchId,
								$scope.currentWeek);
					};

					// active week
					$scope.showActiveWeek = function(index) {
						if ($scope.currentWeek === $scope.currentBatch.arrayWeeks[index])
							return "active";

					};

					// create week
					$scope.createWeek = function() {
						/**
						 * **********************************************TODO
						 * REFACTOR**************************************
						 */
						var weekNumber;
						if (!$scope.currentBatch.weeks)
							weekNumber = 1;
						else
							weekNumber = $scope.currentBatch.weeks.length + 1;
						$log.debug(weekNumber);
						var weekObj = {
							weekId : 1,
							weekNumber : weekNumber,
							batch : $scope.currentBatch,
							topics : null
						};
						/**
						 * *************************NOW TAKES BATCHID--NO
						 * OBJECT***********************
						 */
						caliberDelegate.trainer.createWeek(weekObj).then(
								function(response) {
									pushUnique($scope.currentBatch.weeks, {
										weekId : response,
										weekNumber : weekNumber,
										batch : null,
										topics : null
									});
									$log.debug($scope.currentBatch.weeks);
								});
						/**
						 * **********************************************TODO
						 * REFACTOR**************************************
						 */

					};

					// select assessment from list
					$scope.selectAssessment = function(index) {
						$scope.currentAssessment = $scope.currentAssessment[index];
						$scope.currentView = false;
						/** replace with ajax call to get grades by assessmentId * */
					};

					$scope.addAssessment = function() {
						$scope
								.getAllAssessmentsForWeek(
										$scope.currentBatch.batchId,
										$scope.currentWeek);
						$scope.category.model.assessments = null;
						var assessment = {
								batch : $scope.currentBatch,
								type : $scope.assessmentType.model,
								category : angular.fromJson($scope.category.model),
								week : $scope.currentWeek,
								rawScore : $scope.rawScore
						};
						$log.debug(assessment);
						caliberDelegate.trainer
								.createAssessment(assessment)
								.then(
										function(response) {
											$log.debug(response);
											$scope
													.getAllAssessmentsForWeek(
															$scope.currentBatch.batchId,
															$scope.currentWeek);
											if ($scope.currentAssessments > 0)
												$scope.currentAssessments
														.unshift(assessment);
											else
												$scope.currentAssessments = assessment;
											angular.element(
													"#createAssessmentModal")
													.modal("hide");
										});
					};
					$scope.selectedCategories = [];

					$scope.toggleSelection = function(category) {
						var index = $scope.selectedCategories.indexOf(category);
						if (index > -1)
							$scope.selectedCategories.splice(index, 1);
						else
							$scope.selectedCategories.push(category);
					};
					// get all assesments
					// **********************************************************8888888888***********************************************************
					function getAllAssessmentsForWeek(batchId, weekNumb) {
						if (!weekNumb)
							return;
						caliberDelegate.trainer
								.getAllAssessmentsForWeek(batchId, weekNumb)
								.then(
										function(data) {
											$log.debug(data);
											$scope.currentAssessments = data;
											$scope.getAllGradesForWeek($scope.currentBatch.batchId,$scope.currentWeek);
											var week = new Week(
													$scope.currentWeek,
													$scope.currentAssessments);
											
											$scope.currentBatch.displayWeek = week;
											$scope.currentBatch.arrayWeeks = [];
											
											for(i = 1; i <= $scope.currentBatch.weeks; i++){
												$scope.currentBatch.arrayWeeks.push(i);
											}
											

										});
					};
					
					/**
					 * Updates Grade if exists, else create new Grade, then
					 * saves to $scope
					 * 
					 * @param gradeId
					 * @param traineeId
					 * @param assessment
					 */
					$scope.updateGrade = function(gradeId, traineeId,
							assessment) {
						// constructs Grade object from the data in table
						var grade = {
							gradeId : gradeId,
							trainee : traineeId,
							assessment : assessment,
							dateReceived : new Date(),
							score : document
									.getElementById((traineeId + "-" + assessment.assessmentId)).value
						};
						// adds new Grade if not exists, else update,
						// response contains the ID of the created/updated Grade
						caliberDelegate.trainer.addGrade(grade).then(
								function(response) {
									$log.debug("Adding grade to $scope");
									/**
									 * checks if new Grade was created or
									 * updated assigns newGradeId = created
									 * Grade id else takes the id of previously
									 * fetched Grade ID from view
									 */
									var newGradeId;
									if (response != null)
										newGradeId = response;
									else
										newGradeId = gradeId;
									pushUnique($scope.grades, {
										gradeId : newGradeId,
										assessment : assessment,
										trainee : traineeId,
										dateReceived : new Date()
									});
									$log.debug(response);
								})
					}; // updateGrade
					$scope.findGrade = function(traineeId, assessmentId) {
						for ( var i in $scope.grades) {
							if ($scope.grades[i].trainee == traineeId
									&& $scope.grades[i].assessment.assessmentId == assessmentId) {
								$log.debug("FOUND GRADE "
										+ $scope.grades[i].gradeId);
								return $scope.grades[i];
							}

						}
					};
					/**
					 * **********************************************TODO
					 * REFACTOR**************************************
					 */
					function weekComparator(w1, w2) {
						return (w1.weekNumber > w2.weekNumber) ? 1
								: (w2.weekNumber > w1.weekNumber) ? -1 : 0;
					}
					/**
					 * **********************************************TODO
					 * REFACTOR**************************************
					 */

					 $scope.getAllGradesForWeek=function(batchId,weekId) {
						$scope.grades;
						caliberDelegate.all
								.getGradesForWeek(batchId,
										weekId).then(
										function(data) {
											$log.debug("These are the grades");
											$log.debug(data);
											// for ( var i in data) {
											// $log.debug("Fetching ");
											// $log.debug(data[i]);
											// pushUnique($scope.grades,
											// data[i]);
											// }
											$scope.grades = data;
										});
					}
					/**
					 * **********************************************TODO
					 * POSSIBLE REFACTOR FOR WEEK
					 * PROBLEM**************************************
					 */
					function pushUnique(arr, item) {
						if (arr.indexOf(item) == -1) {
							arr.push(item);
						}
					}

					/**
					 * **********************************************TODO
					 * POSSIBLE REFACTOR**************************************
					 */

					$scope.test = function(d){
						console.log(d);
					}
				});

