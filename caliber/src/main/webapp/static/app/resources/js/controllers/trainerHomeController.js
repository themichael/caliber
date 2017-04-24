angular
		.module("trainer")
		.controller(
				"trainerHomeController",
				function($rootScope, $scope, $state, $log, caliberDelegate,
						chartsDelegate, allBatches) {
					$log.debug("Booted trainer home controller.");

					/**
					 * ********************************* On Start
					 * ****************************
					 */
					const OVERALL = "(All)";
					$scope.currentBatch = allBatches[0];
					$scope.reportCurrentWeek = OVERALL;

					(function(start) {
						// Finishes any left over AJAX animation
						NProgress.done();

						createDefaultCharts();

					})();

					function createDefaultCharts() {
						// NProgress.start();
						createAverageTraineeScoresOverall();
						createTechnicalSkillsBatchOverall();
						createQCStatus();
					}

					/**
					 * ********************************************* UI
					 * **************************************************
					 */

					// *******************************************************************************
					// *** Bar Charts
					// *******************************************************************************
					function createAverageTraineeScoresOverall() {
						chartsDelegate.bar.data
								.getAverageTraineeScoresOverallData(
										$scope.currentBatch.batchId)
								// confirm if batch or trainee
								.then(
										function(data) {
											NProgress.done();
											var barChartObject = chartsDelegate.bar
													.getAverageTraineeScoresOverall(data);
											$scope.batchOverAllLabels = barChartObject.labels;
											$scope.batchOverAllData = barChartObject.data;
											$scope.batchOverAllOptions = barChartObject.options;
											$scope.batchOverAllColors = barChartObject.colors;
										}, function() {
											NProgress.done();
										});

					}

					// *******************************************************************************
					// *** Radar Charts
					// *******************************************************************************

					function createTechnicalSkillsBatchOverall() {
						$log.debug("createTechnicalSkillsBatchOverall");
						chartsDelegate.radar.data
								.getTechnicalSkillsBatchOverallData(
										$scope.currentBatch.batchId)
								// batchId
								.then(
										function(data) {
											NProgress.done();
											var radarBatchOverallChartObject = chartsDelegate.radar
													.getTechnicalSkillsBatchOverall(
															data,
															$scope.currentBatch.trainingName);
											$scope.radarBatchOverallData = radarBatchOverallChartObject.data;
											$scope.radarBatchOverallOptions = radarBatchOverallChartObject.options;
											$scope.radarBatchOverallLabels = radarBatchOverallChartObject.labels;
											$scope.radarBatchOverallSeries = radarBatchOverallChartObject.series;
											$scope.radarBatchOverallColors = radarBatchOverallChartObject.colors;

											$scope.radarBatchOverallTable = chartsDelegate.utility
													.dataToTable(radarBatchOverallChartObject);
										});

					}

					// *******************************************************************************
					// *** Doughnut Charts
					// *******************************************************************************

					function createQCStatus() {
						chartsDelegate.doughnut.data
								.getQCStatsData($scope.currentBatch.batchId,
										$scope.reportCurrentWeek)
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

				});
