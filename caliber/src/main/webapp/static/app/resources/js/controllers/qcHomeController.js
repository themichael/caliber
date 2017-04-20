angular.module("qc").controller(
		"qcHomeController",
		function($rootScope, $scope, $state, $log, caliberDelegate,
				chartsDelegate, allBatches) {
			$log.debug("Booted QC home controller.");
			/*
			 * ******************************************* On Start
			 * ***********************************************
			 */

			(function start() {
				// Finishes any left over AJAX animation
				NProgress.done();

				createDefaultCharts();

			})();

			function createDefaultCharts() {
				NProgress.start();
				createAllBatchesCurrentWeekQCStats();
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
									
									$log.debug("STACKED OBJECT++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
									$log.debug(barChartObj);
									
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