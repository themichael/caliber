angular
		.module("vp")
		.controller(
				"vpHomeController",
				function($scope, $log, caliberDelegate, chartsDelegate,
						allBatches) {
					$log.debug("Booted vp home controller.");
					(function start() {
						// Finishes any left over ajax animation from another
						// page
						NProgress.done();
						//createDummyBarChart();
						createAllBatchesCurrentWeekQCStats();
						createCurrentBatchesAverageScoreChart();
					})();

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