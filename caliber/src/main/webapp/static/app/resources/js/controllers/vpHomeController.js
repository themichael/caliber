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
						createDummyBarChart();
						createAllBatchesCurrentWeekQCStats();
						createCurrentBatchesAverageScoreChart();
					})();

					// Yanilda dummy barchart
					function createDummyBarChart() {
						data = chartsDelegate.bar.data.getDummyBarData();
						NProgress.done();
						var barChartObject = chartsDelegate.bar
								.getDummyBarChartDelegate(data);
						$scope.barchartDLabels = barChartObject.data.labels;
						$scope.barchartDData = barChartObject.data.datasets;

					}
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

				});