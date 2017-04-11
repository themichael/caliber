/**
 * 
 * @param $log
 * @param hbarChartFactory
 * @param radarChartFactory
 * @param lineChartFactory
 * @returns {{}}
 */
angular.module("delegate")
		.factory(
				"chartsDelegate",
				function($log, doughnutChartFactory, doughnutChartDataFactory) {
					$log.debug("Booted charts delegate");

					var delegate = {};
					delegate.doughnut = {};
					delegate.doughnut.data = {};
					delegate.bar = {};
					delegate.bar.data = {};
					delegate.line = {};
					delegate.line.data = {};
					delegate.radar = {};
					delegate.radar.data = {};

					/**
					 * ********************* Doughnut
					 * *************************************
					 */
					delegate.doughnut.getQCStats = function(dataArray) {
						return doughnutChartFactory.batchWeekQCPie(dataArray);
					};

					delegate.doughnut.data.getQCStatsData = function(batchId,
							weekId) {
						return doughnutChartDataFactory.batchWeekQCPie(batchId,
								weekId);
					}
					/**
					 * ********************* Line *********************
					 */
					delegate.doughnut.getQCStats = function(dataArray) {
						return doughnutChartFactory.batchWeekQCPie(dataArray);
					};

					delegate.doughnut.data.getQCStatsData = function(batchId,
							weekId) {
						return doughnutChartDataFactory.batchWeekQCPie(batchId,
								weekId);
					}

					/**
					 * ********************* Bars
					 * *************************************
					 */
					
					

					/**
					 * ********************* Lines
					 * *************************************
					 */

					/**
					 * ********************* Radars
					 * *************************************
					 */

					return delegate;
				});