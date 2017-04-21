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
				$log.debug("QC Home Controller: createAllBatchesCurrentWeekQCStats");
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