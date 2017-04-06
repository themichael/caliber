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
					
					// array of weeks to parse through and display tabs
					
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


                                $scope.category = {
									model : null,
									options : []
								};
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
								 * ********************************************************************************************************************************************************************************************************************************
								 */
								getAllAssessmentsForWeek(
										$scope.currentBatch.batchId,
										$scope.currentWeek);

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
							getAllAssessmentsForWeek(
									$scope.currentBatch.batchId,
									$scope.currentWeek);
						} else
							$scope.currentWeek = null;
						/**
						 * **********************************************TODO
						 * REFACTOR**************************************
						 */
						getAllAssessmentsForWeek($scope.currentBatch.batchId,
								$scope.currentWeek);
					};

					// select week
					$scope.selectWeek = function(index) {
						/**
						 * **********************************************TODO
						 * REFACTOR**************************************
						 */
						$scope.currentWeek = $scope.currentBatch.weeks[index];
						$log.debug($scope.currentWeek);
						/**
						 * **********************************************TODO
						 * REFACTOR**************************************
						 */
						getAllAssessmentsForWeek($scope.currentBatch.batchId,
								$scope.currentWeek);
					};

					// active week
					$scope.showActiveWeek = function(index) {
						/**
						 * **********************************************TODO
						 * REFACTOR**************************************
						 */
						if ($scope.currentWeek === $scope.currentBatch.weeks)
							/**
							 * **********************************************TODO
							 * REFACTOR**************************************
							 */
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
						getAllAssessmentsForWeek($scope.currentBatch.batchId,
								$scope.currentWeek);
						var assessment = {
							assessmentId : 1,
							title : $scope.assessName,
							batch : $scope.currentBatch.batchId,
							type : $scope.assessType,
							/**
							 * **********************************************TODO
							 * REFACTOR**************************************
							 */
							categories : $scope.selectedCategories,
							week : $scope.currentWeek.weekId,
							/**
							 * **********************************************TODO
							 * REFACTOR**************************************
							 */
							weeklyStatus : null,
							rawScore : $scope.rawScore
						};
						$log.debug(assessment);
						caliberDelegate.trainer
								.createAssessment(assessment)
								.then(
										function(response) {
											$log.debug(response);
											getAllAssessmentsForWeek(
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
					// **********************************************************************************************************
					function getAllAssessmentsForWeek(batchId, weekNumb) {
						if (!weekNumb)
							return;
						caliberDelegate.trainer
								.getAllAssessmentsForWeek(batchId, weekNumb)
								.then(
										function(data) {
											$log.debug(data);
											$scope.currentAssessments = data;
											
											var week = new Week(
													$scope.currentWeek,
													$scope.currentAssessments);
											$scope.currentBatch.displayWeek = week;
											$scope.currentBatch.arrayWeeks = [];
											
											for(i = 1; i <= weekNumb; i++){
												$scope.currentBatch.arrayWeeks.push(i);
											}
											
											getAllGradesForWeek();

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
						$log
								.debug("Starting updateGrade for "
										+ "traineeId: " + traineeId + ", "
										+ "assessment: " + assessment + ", "
										+ "and gradeId: " + gradeId);

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

					function getAllGradesForWeek() {
						$scope.grades = [];
						caliberDelegate.all.getGradesForWeek(
								$scope.currentBatch.batchId,
								$scope.currentWeek.weekNumb).then(
								function(data) {
									$log.debug("These are the grades");
									$log.debug(data);
									for ( var i in data) {
										$log.debug("Fetching ");
										$log.debug(data[i]);
										pushUnique($scope.grades, data[i]);
									}
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
				});