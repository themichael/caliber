angular
		.module("charts")
		.controller(
				"allReportController",
				function($scope, $log, caliberDelegate, chartsDelegate,
						allBatches) {

					$log.debug("Booted Report Controller");
					$log.debug("Peacepapi is here!!!!!");

					(function start() {
						// Finishes any left over ajax animation from another
						// page
						NProgress.done();

						// batch null check
						if (!allBatches || allBatches.length === 0) {
							$scope.noBatches = true;
							$scope.noBatchesMessage = "No Batches belonging to you were found.";
						} else {
							$scope.noBatches = false;
							$log.debug("Here AGAIN!!!!!!");
							createDefaultCharts();
						}
					})();

					/**
					 * ********************************************* UI
					 * **************************************************
					 */
					var viewCharts = 0;

					$scope.batches = allBatches;
					$scope.currentBatch = {
						trainingName : "Batch"
					};
					$scope.currentTrainee = {
						name : "Trainee"
					};

					// hide filter tabs
					$scope.hideOtherTabs = function() {
						return $scope.currentBatch.trainingName !== "Batch";
					};

					// show charts
					$scope.showCharts = function(charts) {
						return charts === viewCharts;
					};

					function createDefaultCharts() {
						// Finishes any left over ajax animation from another
						// page
						NProgress.done();
						NProgress.start();

						createQCStatus();
						createAverageTraineeScoresWeekly();
						createAverageTraineeScoresOverall();
//						createAssessmentAveragesBatchWeekly();
						createAssessmentAveragesTraineeWeekly();
						createAssessmentAveragesTraineeOverall();
						createTechnicalSkillsBatchOverall();
						createTechnicalSkillsTraineeWeekly();
						createTechnicalSkillsTraineeOverall();
						createWeeklyProgressBatchOverall();
						createWeeklyProgressTraineeWeekly();
						createWeeklyProgressTraineeOverall();

					}

					// ********************* Doughnut
					// **************************************

					function createQCStatus() {
						chartsDelegate.doughnut.data
								.getQCStatsData(1050, 1)
								.then(
										function(data) {
											$log.debug(data);
											NProgress.done();
											var doughnutChartObject = chartsDelegate.doughnut
													.getQCStats(data);
											console
													.log("here we are, in the pie method");
											console.log(doughnutChartObject);
											$scope.qcStatsLabels = doughnutChartObject.labels;
											$scope.qcStatsData = doughnutChartObject.data;
											$scope.qcStatsOptions = doughnutChartObject.options;
											$scope.qcStatsColors = doughnutChartObject.colors;
										}, function() {
											NProgress.done();
										});

					}

					// ***************************** Bar
					// *********************************

					function createAverageTraineeScoresWeekly() {
						chartsDelegate.bar.data
						.getAverageTraineeScoresWeeklyData(1050, 1)
						.then(function(data) {
							NProgress.done();
							var barChartObj = chartsDelegate.bar.getAverageTraineeScoresWeekly(data);
							$scope.averageTraineeScoresWeeklyData = barChartObj.data;
							$scope.averageTraineeScoresWeeklyLabels = barChartObj.labels;
							$scope.averageTraineeScoresWeeklySeries = barChartObj.series;
						}, function() {
							NProgress.done();
						});
					}

					function createAverageTraineeScoresOverall() {
//						chartsDelegate.bar.data
//						.getAverageTraineeScoresOverallData(1051)
//								.then(
//										function(data) {
//											$log.debug(data);
//											NProgress.done();
//											var barChartObject = chartsDelegate.bar
//													.getAverageTraineeScoresOverall(data);
//											console.log("batch avg chart");
//											$scope.batchTechLabels = barChartObject.labels;
//											$scope.batchTechData = barChartObject.data;
//											$scope.batchTechOptions = barChartObject.options;
//
//										}, function() {
//											NProgress.done();
//										});
					}
					//Yanilda barchart
					function createAssessmentAveragesBatchWeekly() {
						chartsDelegate.bar.data
						.get(1050, 1)
						.then(
								function(data) {
									$log.debug(data);
									NProgress.done();
									var doughnutChartObject = chartsDelegate.doughnut
											.getQCStats(data);
									console
											.log("here we are, in the pie method");
									console.log(doughnutChartObject);
									$scope.qcStatsLabels = doughnutChartObject.labels;
									$scope.qcStatsData = doughnutChartObject.data;
									$scope.qcStatsOptions = doughnutChartObject.options;
								}, function() {
									NProgress.done();
								});

					}

					function createAssessmentAveragesTraineeWeekly() {

					}

					function createAssessmentAveragesTraineeOverall() {

					}

					// **************************** Radar
					// ***************************************
					function createTechnicalSkillsTraineeWeekly() {
						$log.debug("createTechnicalSkillsTraineeWeekly");
						chartsDelegate.radar.data
						.getTechnicalSkillsTraineeWeeklyData(5, 1059) // up to week, traineeId
						.then(
								function(data) {
									NProgress.done();
									var radarTraineeWeeklyChartObj = chartsDelegate.radar
											.getTechnicalSkillsTraineeWeekly(data);

									$log.debug("Radar Trainee Up To Week Chart Object:");
									$log.debug(radarTraineeWeeklyChartObj);

									$scope.radarTraineeWeeklyData = radarTraineeWeeklyChartObj.data;
									$scope.radarTraineeWeeklyOptions = radarTraineeWeeklyChartObj.options;
									$scope.radarTraineeWeeklyLabels = radarTraineeWeeklyChartObj.labels;
									$scope.radarTraineeWeeklySeries = radarTraineeWeeklyChartObj.series;
								});
					};

					function createTechnicalSkillsTraineeOverall() {
						$log.debug("createTechnicalSkillsTraineeOverall");
						chartsDelegate.radar.data
						.getTechnicalSkillsTraineeOverallData(1059) // traineeId
						.then(
								function(data) {
									NProgress.done();
									var radarTraineeOverallChartObj = chartsDelegate.radar
											.getTechnicalSkillsTraineeOverall(data);
									
									$log.debug("Radar Trainee Overall Chart Object:");
									$log.debug(radarTraineeOverallChartObj);

									$scope.radarTraineeOverallData = radarTraineeOverallChartObj.data;
									$scope.radarTraineeOverallOptions = radarTraineeOverallChartObj.options;
									$scope.radarTraineeOverallLabels = radarTraineeOverallChartObj.labels;
									$scope.radarTraineeOverallSeries = radarTraineeOverallChartObj.series;
								});
					};
					

					function createTechnicalSkillsBatchOverall() {
						$log.debug("createTechnicalSkillsBatchOverall");
						chartsDelegate.radar.data
								.getTechnicalSkillsBatchOverallData(1050) // batchId
								.then(
										function(data) {
											NProgress.done();
											var radarBatchOverallChartObject = chartsDelegate.radar
													.getTechnicalSkillsBatchOverall(data);

											$log.debug("Radar Batch Overall Chart Object:");
											$log.debug(radarBatchOverallChartObject);
											
											$scope.radarBatchOverallData = radarBatchOverallChartObject.data;
											$scope.radarBatchOverallOptions = radarBatchOverallChartObject.options;
											$scope.radarBatchOverallLabels = radarBatchOverallChartObject.labels;
											$scope.radarBatchOverallSeries = radarBatchOverallChartObject.series;
										});

					};

					// ***************************** Line
					// ***************************************

					function createWeeklyProgressBatchOverall() {
						chartsDelegate.line.data
							.getWeeklyProgressBatchOverallData(1050)
							.then(function(data) {
									NProgress.done();
									var lineChartObj = chartsDelegate.line.getWeeklyProgressBatchOverall(data);
									$scope.weeklyProgressBatchOverallLabels = lineChartObj.labels;
									$scope.weeklyProgressBatchOverallData = lineChartObj.data;
							}, function() {
								NProgress.done();
							})

					}
					//Yanilda
					function createWeeklyProgressTraineeWeekly() {

					}
					
					function createWeeklyProgressTraineeOverall() {

					}

					// *******************************************************************************

					/**
					 * Generates a PDF by sending HTML to server. Downloads
					 * automatically in new tab.
					 */
					$scope.generatePDF = function() {
						var html = "<div>Extract report contents into here</div>";
						caliberDelegate.all.generatePDF(html).then(
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
								}, function(error) {
									$log.debug(reason);
								}, function(value) {
									$log.debug(value);
								});
					}

				});
