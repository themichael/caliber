angular
		.module("qc")
		.controller(
				"qcHomeController",
				function($rootScope, $scope, $state, $log, caliberDelegate,
						chartsDelegate, allBatches) {
					$log.debug("Booted QC home controller.");
					/*
					 * ******************************************* On Start
					 * ***********************************************
					 */
					
					const ALL = "(All)";

					(function start() {
						// Finishes any left over AJAX animation
						NProgress.done();
						$log.debug(allBatches);
						createDefaultCharts();
						if(!allBatches) $scope.noBatches = true;
					})();

					function createDefaultCharts() {
						NProgress.start();
						createAllBatchesCurrentWeekQCStats();
						createCurrentBatchesAverageScoreChart();
					}

					/*
					 * ********************************************* UI
					 * **************************************************
					 */

					function createAllBatchesCurrentWeekQCStats() {
						chartsDelegate.bar.data
								.getAllBatchesCurrentWeekQCStatsData()
								.then(
										function(data) {
											NProgress.done();
											var barChartObj = chartsDelegate.bar
													.getAllBatchesCurrentWeekQCStats(data);

											$scope.stackedBarData = barChartObj.data;
											$scope.stackedBarLabels = barChartObj.labels;
											$scope.stackedBarSeries = barChartObj.series;
											$scope.stackedBarOptions = barChartObj.options;
											$scope.stackedBarColors = barChartObj.colors;

										}, function() {
											NProgress.done();
										});
					}

					function createCurrentBatchesAverageScoreChart() {
						chartsDelegate.line.data
								.getCurrentBatchesAverageScoreChartData()
								.then(
										function(data) {
											NProgress.done();
											var lineChartObj = chartsDelegate.line
													.getCurrentBatchesAverageScoreChart(data);

											$scope.currentBatchesLineData = lineChartObj.data;
											$scope.currentBatchesLineLabels = lineChartObj.labels;
											$scope.currentBatchesLineSeries = lineChartObj.series;
											$scope.currentBatchesLineOptions = lineChartObj.options;
											$scope.currentBatchesLineColors = lineChartObj.colors;
											$scope.currentBatchesDsOverride = lineChartObj.datasetOverride;

										}, function() {
											NProgress.done();
										});
					}

				});
				