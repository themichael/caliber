angular
		.module("charts")
		.controller(
				"allReportController",
				function($rootScope, $scope, $state, $log, caliberDelegate, chartsDelegate,
						allBatches) {

					const OVERALL = "(All)";
					const ALL = -1;
					// What you see when you open Reports
					$scope.currentBatch = null;
					$scope.reportCurrentWeek = OVERALL;
					$scope.batchWeeks = {"weeks":[]};
					$scope.currentTraineeId = ALL;
					
					
					$scope.noBatch=true;
					$scope.batchWeek = false;
					$scope.batchWeekTrainee = false;
					$scope.batchOverall = false;
					$scope.batchOverallTrainee = false;
					//$scope.currentBatch = allBatches[0];$scope.currentWeek =1; // denise debug line please ignore ... ill delete when im done TODO
					(function start() {
						// Finishes any left over ajax animation from another
						// page
						NProgress.done();

						// batch null check
						if ($scope.currentBatch == null) {
							$scope.noBatch=true;
						} else {
							$scope.noBatch=false;
							selectView($scope.currentBatch.batchId, $scope.reportCurrentWeek, $scope.currentTraineeId);
						}
						
					})();
					
					function selectView(batch, week, trainee){
						
						if (week == OVERALL ){
							// All Weeks
							if ( trainee == ALL){
								// All Trainees
								$scope.batchWeek = false;
								$scope.batchWeekTrainee = false;
								$scope.batchOverall = true;
								$scope.batchOverallTrainee = false;
								createBatchOverall();
								
							} else {
								// Specific Trainee
								$scope.batchWeek = false;
								$scope.batchWeekTrainee = false;
								$scope.batchOverall = false;
								$scope.batchOverallTrainee = true;
								createBatchOverallTrainee();
							}
						} else {
							// Specific Week
							if ( trainee == ALL){
								// All Trainees
								$scope.batchWeek = true;
								$scope.batchWeekTrainee = false;
								$scope.batchOverall = false;
								$scope.batchOverallTrainee = false;
								createBatchWeek();
							} else {
								// Specific trainee
								$scope.batchWeek = false;
								$scope.batchWeekTrainee = true;
								$scope.batchOverall = false;
								$scope.batchOverallTrainee = false;
								createBatchWeekTrainee();
							}
							
						}
						
					}

					function getCurrentBatchWeeks(weeks) {
						$scope.batchWeeks.week = [];
						for(var i = 1; i <= weeks;i++) $scope.batchWeeks.week.push(i);
						$log.debug($scope.batchWeeks);
					}
					/**
					 * ********************************************* UI
					 * **************************************************
					 */
					$scope.selectCurrentBatch = function(index) {
						$scope.currentBatch = $scope.batches[index];
						getCurrentBatchWeeks($scope.currentBatch.weeks);
						$log.debug($scope.batchWeeks.week);
						selectView($scope.currentBatch.batchId, $scope.reportCurrentWeek, $scope.currentTraineeId);
					};
					
					$scope.selectCurrentWeek = function(week) {
						//$scope.currentWeek = week;
						$scope.reportCurrentWeek = week;
						selectView($scope.currentBatch.batchId, $scope.reportCurrentWeek, $scope.currentTraineeId);
					}
					/*scope function to display the table if a batch and week has been selected*/
					$scope.displayTable = function(){
				//		$log.debug("[		THIS IS THE CURRENT BATCHID 		]" +$scope.currentBatch.batchId + " [		THIS IS THE CURRENTWEEK		]" + $scope.reportCurrentWeek);
						if($scope.currentBatch === null  || $scope.currentWeek === null){ // checking to see if the scope variables are null
							return false;
						}
						return true;
					}	
					$scope.selectCurrentTrainee = function(index) {
						if (index == ALL) {
							$scope.currentTrainee = {
								name : "Trainee"
							};
							$scope.currentTraineeId = ALL;
							selectView($scope.currentBatch.batchId, $scope.reportCurrentWeek, $scope.currentTraineeId);
//							viewCharts = 1;
							//createBatchCharts();
						} else {
							$scope.currentTraineeId = $scope.currentBatch.trainees[index].traineeId;
							$scope.currentTrainee = $scope.currentBatch.trainees[index];
							$log.debug($scope.currentTrainee);
							$scope.currentTrainee = {
									name : $scope.currentBatch.trainees[index].name
								};
							selectView($scope.currentBatch.batchId, $scope.reportCurrentWeek, $scope.currentTraineeId);
							//viewCharts = 3;
							//createTraineeCharts();
						}
					};
					
					
					
					$scope.batches = allBatches;
					/*$scope.currentBatch = {
						trainingName : "Batch",
						batchId : 1050		// denise hard coded
					};*/
					//$scope.currentBatch = allBatches[0]; // denise hard code/core
					$scope.currentTrainee = {
						name : "Trainee"
					};

					// hide filter tabs
					$scope.hideOtherTabs = function() {
						return $scope.currentBatch.trainingName !== "Batch";
					};

					
					function createBatchWeek() {
						NProgress.done();
						NProgress.start();
						
						createQCStatus();
						createAverageTraineeScoresWeekly();
						createAssessmentAveragesBatchWeekly();
					}
					
					function createBatchWeekTrainee(){
						NProgress.done();
						NProgress.start();
						
						
						createAssessmentAveragesTraineeWeekly();
						createTechnicalSkillsTraineeWeekly();
						createWeeklyProgressTraineeWeekly();
					}
					
					function createBatchOverall(){
						NProgress.done();
						NProgress.start();
						
						createAverageTraineeScoresOverall();
						createTechnicalSkillsBatchOverall();
						createWeeklyProgressBatchOverall();
					}

					function createBatchOverallTrainee(){
						NProgress.done();
						NProgress.start();
						
						createAssessmentAveragesTraineeOverall();
						createWeeklyProgressTraineeOverall();
						createTechnicalSkillsTraineeOverall();
					}

					// ********************* Doughnut
					// **************************************

					function createQCStatus() {
						chartsDelegate.doughnut.data
								.getQCStatsData($scope.currentBatch.batchId, $scope.reportCurrentWeek)
								.then(
										function(data) {
											NProgress.done();
											var doughnutChartObject = chartsDelegate.doughnut
													.getQCStats(data);
											$scope.qcStatsLabels = doughnutChartObject.labels;
											$scope.qcStatsData = doughnutChartObject.data;
											$scope.qcStatsOptions = doughnutChartObject.options;
											$scope.qcStatsColors = doughnutChartObject.colors;
										});

					}

					// ***************************** Bar
					// *********************************

					function createAverageTraineeScoresWeekly() {
						chartsDelegate.bar.data
						.getAverageTraineeScoresWeeklyData($scope.currentBatch.batchId, $scope.reportCurrentWeek)
						.then(function(data) {
							NProgress.done();
							var barChartObj = chartsDelegate.bar.getAverageTraineeScoresWeekly(data);
							$scope.averageTraineeScoresWeeklyData = barChartObj.data;
							$scope.averageTraineeScoresWeeklyLabels = barChartObj.labels;
							$scope.averageTraineeScoresWeeklySeries = barChartObj.series;
							$scope.averageTraineeScoresWeeklyOptions = barChartObj.options;
							
							$scope.averageTraineeScoresWeeklyTable = chartsDelegate.utility.dataToTable(barChartObj);
						}, function() {
							NProgress.done();
						});
					}

					// Hossain bar chart trainee vs average all week score
					function createAverageTraineeScoresOverall() {
						chartsDelegate.bar.data
								.getAverageTraineeScoresOverallData($scope.currentBatch.batchId) // confirm if batch or trainee
								.then(
										function(data) {
											NProgress.done();
											var barChartObject = chartsDelegate.bar
													.getAverageTraineeScoresOverall(data);
											$scope.batchOverAllLabels = barChartObject.labels;
											$scope.batchOverAllData = barChartObject.data;
											$scope.batchOverAllOptions = barChartObject.options;
										}, function() {
											NProgress.done();
										});

					};
					//Yanilda barchart
					function createAssessmentAveragesBatchWeekly() {
						chartsDelegate.bar.data
						.getAssessmentAveragesBatchWeeklyData($scope.currentBatch.batchId, $scope.reportCurrentWeek)
						.then(
								function(data) {
									NProgress.done();
									var barChartObject = chartsDelegate.bar
									.getAssessmentAveragesBatchWeekly(data);
									console.log("here we are, in the yani barchart method");
									console.log(barChartObject.options);
									console.log(barChartObject);
									console.log(barChartObject.series);
									
									$scope.barcharAWLabels = barChartObject.labels;
									$scope.barcharAWData = barChartObject.data;
									$scope.barcharAWOptions= barChartObject.options;
									$scope.barcharAWseries= barChartObject.series;
								}, function() {
									NProgress.done();
								});

					}

					function createAssessmentAveragesTraineeWeekly() {
						chartsDelegate.bar.data.getAssessmentAveragesTraineeWeeklyData($scope.currentBatch.batchId, $scope.reportCurrentWeek, $scope.currentTraineeId)
						.then(
								function(data) {
									NProgress.done();
									var barChartObject = chartsDelegate.bar
											.getAssessmentAveragesTraineeWeekly(data);
									$scope.AssessmentAveragesTraineeWeeklyLabels = barChartObject.labels;
									$scope.AssessmentAveragesTraineeWeeklyData = barChartObject.data;
									$scope.AssessmentAveragesTraineeWeeklyOptions = barChartObject.options;
									$scope.AssessmentAveragesTraineeWeeklySeries = barChartObject.series;
								}, function() {
									NProgress.done();
								});

					}

					function createAssessmentAveragesTraineeOverall() {
						chartsDelegate.bar.data.getAssessmentAveragesTraineeOverallData($scope.currentBatch.batchId, $scope.currentTraineeId)
						.then(
								function(data) {
									NProgress.done();
									var barChartObject = chartsDelegate.bar
										.getAssessmentAveragesTraineeOverall(data);
									$scope.AssessmentAveragesTraineeOverallLabels = barChartObject.labels;
									$scope.AssessmentAveragesTraineeOverallData = barChartObject.data;
									$scope.AssessmentAveragesTraineeOverallOptions = barChartObject.options;
									$scope.AssessmentAveragesTraineeOverallSeries = barChartObject.series;
								}, function() {
									NProgress.done();
								});


					}

					// **************************** Radar
					// ***************************************
					function createTechnicalSkillsTraineeWeekly() {
						$log.debug("createTechnicalSkillsTraineeWeekly");
						chartsDelegate.radar.data
						.getTechnicalSkillsTraineeWeeklyData($scope.reportCurrentWeek, $scope.currentTraineeId) // up to week, traineeId
						.then(
								function(data) {
									NProgress.done();
									var radarTraineeWeeklyChartObj = chartsDelegate.radar
											.getTechnicalSkillsTraineeWeekly(data, "Temp Trainee Weekly");
									$scope.radarTraineeWeeklyData = radarTraineeWeeklyChartObj.data;
									$scope.radarTraineeWeeklyOptions = radarTraineeWeeklyChartObj.options;
									$scope.radarTraineeWeeklyLabels = radarTraineeWeeklyChartObj.labels;
									$scope.radarTraineeWeeklySeries = radarTraineeWeeklyChartObj.series;
									
									$scope.radarTraineeWeeklyTable = chartsDelegate.utility.dataToTable(radarTraineeWeeklyChartObj);
								});
					};

					function createTechnicalSkillsTraineeOverall() {
						$log.debug("createTechnicalSkillsTraineeOverall");
						chartsDelegate.radar.data
						.getTechnicalSkillsTraineeOverallData($scope.currentTraineeId) // traineeId
						.then(
								function(data) {
									NProgress.done();
									var radarTraineeOverallChartObj = chartsDelegate.radar
											.getTechnicalSkillsTraineeOverall(data, "Temp Trainee Overall");
									$scope.radarTraineeOverallData = radarTraineeOverallChartObj.data;
									$scope.radarTraineeOverallOptions = radarTraineeOverallChartObj.options;
									$scope.radarTraineeOverallLabels = radarTraineeOverallChartObj.labels;
									$scope.radarTraineeOverallSeries = radarTraineeOverallChartObj.series;
									
									$scope.radarTraineeOverallTable = chartsDelegate.utility.dataToTable(radarTraineeOverallChartObj);
								});
					};
					

					function createTechnicalSkillsBatchOverall() {
						$log.debug("createTechnicalSkillsBatchOverall");
						chartsDelegate.radar.data
								.getTechnicalSkillsBatchOverallData($scope.currentBatch.batchId) // batchId
								.then(
										function(data) {
											NProgress.done();
											var radarBatchOverallChartObject = chartsDelegate.radar
													.getTechnicalSkillsBatchOverall(data, "Temp Batch Overall");
											$scope.radarBatchOverallData = radarBatchOverallChartObject.data;
											$scope.radarBatchOverallOptions = radarBatchOverallChartObject.options;
											$scope.radarBatchOverallLabels = radarBatchOverallChartObject.labels;
											$scope.radarBatchOverallSeries = radarBatchOverallChartObject.series;
											
											$scope.radarBatchOverallTable = chartsDelegate.utility.dataToTable(radarBatchOverallChartObject);
										});

					};

					// ***************************** Line
					// ***************************************

					function createWeeklyProgressBatchOverall() {
						chartsDelegate.line.data
						.getWeeklyProgressBatchOverallData($scope.currentBatch.batchId)
						.then(function(data) {
								NProgress.done();
								var lineChartObj = chartsDelegate.line.getWeeklyProgressBatchOverall(data);
								$scope.weeklyProgressBatchOverallLabels = lineChartObj.labels;
								$scope.weeklyProgressBatchOverallData = lineChartObj.data;
								$scope.weeklyProgressBatchOverallOptions = lineChartObj.options;
						}, function() {
							NProgress.done();
						})
					}
					//Yanilda
					function createWeeklyProgressTraineeWeekly() {
						chartsDelegate.line.data
						.getWeeklyProgressTraineeWeeklyData($scope.reportCurrentWeek, $scope.currentTraineeId)
						.then(
								function(data) {
									NProgress.done();
									var lineChartObjectwd = chartsDelegate.line
									.getWeeklyProgressTraineeWeekly(data);
									$scope.linecharTWLabels = lineChartObjectwd.labels;
									$scope.linecharTWData = lineChartObjectwd.data;
									$scope.linecharTWOptions = lineChartObjectwd.options;
									$scope.linecharTWSeries = lineChartObjectwd.series;
								}, function() {
									NProgress.done();
								});

					}
					
					function createWeeklyProgressTraineeOverall() {
						chartsDelegate.line.data
						.getWeeklyProgressTraineeOverallData($scope.currentBatch.batchId, $scope.currentTraineeId)
						.then(
								function(data) {
									$log.debug(data);
									NProgress.done();
									var lineChartObject = chartsDelegate.line
									.getWeeklyProgressTraineeOverall(data);
									console.log("chart completed!");
									$scope.batchOverallWeeklyLabels = lineChartObject.labels;
									$scope.batchOverallWeeklyData = lineChartObject.data;
									$scope.batchOverallWeeklySeries = lineChartObject.series;
									$scope.batchOverallWeeklyOptions = lineChartObject.options;
									console.log(lineChartObject);

								}, function() {
									NProgress.done();
								});

					}
					//********************************************************************************
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
					

					// *******************************************************************************

					/**
					 * Generates a PDF by sending HTML to server. Downloads
					 * automatically in new tab.
					 */
					$scope.generatePDF = function() {
						// indicate to user the PDF is processing
						$scope.reticulatingSplines = true;
						
						// get html element #caliber-container
						var caliber = document.getElementById("caliber-container");
						$log.debug(caliber);
						
						// iterate over all childrens to convert <canvas> to <img src=base64>
						var html = $scope.generateImgFromCanvas(caliber).innerHTML;
						$log.debug(html);
						
						var title = "Progress for " + $scope.currentBatch.trainingName;
						// generate the title
						if($scope.currentWeek)
							title = "Week "+ $scope.currentWeek +" Progress for " + $scope.currentBatch.trainingName;
						else if ($scope.currentTrainee)
							title = "Progress for " + $scope.currentTrainee.name;
						
						// send to server and download generated PDF
						caliberDelegate.all.generatePDF(title, html).then(
								function(pdf) {
									// extract PDF bytes
									var file = new Blob([ pdf ], {
										type : "application/pdf"
									});
									// create temporary 'url' and download
									// automatically
									var fileURL = URL.createObjectURL(file);
									var a = document.createElement("a");
									a.href = fileURL;
									a.target = "_blank";
									a.download = "report.pdf";
									document.body.appendChild(a);
									a.click();
									$scope.reticulatingSplines = false;
								}, function(error) {
									$log.debug(reason);
								}, function(value) {
									$log.debug(value);
								});
					}
				});
