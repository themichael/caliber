/**
 * 
 * @param $log
 * @param hbarChartFactory
 * @param radarChartFactory
 * @param lineChartFactory
 * @returns {{}}
 */
angular
		.module("delegate")
		.factory(
				"chartsDelegate",
				function($log, doughnutChartFactory,
						doughnutChartDataFactory) {
					$log.debug("Booted charts delegate");

					var delegate = {};
					delegate.doughnut = {};
					delegate.doughnut.data = {};

					/**
					 * ********************* Doughnut *********************
					 */
//					delegate.doughnut.getQCStats = function(dataArray) {
//						return doughnutChartFactory.doughnutChart
//								.batchWeekQCPie(dataArray);
//					};
//
//					delegate.doughnut.data.getQCStatsData = function(batchId,
//							weekId) {
//						return doughnutChartDataFactory.report.batchWeekQCPie(
//								batchId, weekId);
//					}

					return delegate;
				});