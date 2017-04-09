/**
 * 
 * @param $log
 * @param barChartFactory
 * @param radarChartFactory
 * @param lineChartFactory
 * @returns {{}}
 */
angular.module("delegate")
		.factory(
				"chartsDelegate",
				function($log, barChartFactory, radarChartFactory,
						lineChartFactory) {
					$log.debug("Booted charts delegate");

					var delegate = {};

					delegate.bar = {};
					delegate.doughnut = {};
					delegate.radar = {};
					delegate.line = {};

					/**
					 * ********************* Bar *********************
					 */
					delegate.bar.getBatchAvgChart = function(dataArray) {
						return barChartFactory.getBatchAvgChart(dataArray);
					};

					/**
					 * ************************** Radar
					 * *************************
					 */
					delegate.radar.getBatchRankComparisonChart = function(
							dataArray) {
						return radarChartFactory
								.getBatchRankComparisonChart(dataArray);

						/**
						 * ************************** Line
						 * **************************
						 */

						delegate.line.getBatchProgressChart = function(
								dataArray) {
							return lineChartFactory
									.getBatchProgressChart(dataArray);
						};

						/**
						 * ************************** Doughnut
						 * **************************
						 */

						delegate.doughnut.getBatchProgressChart = function(
								dataArray) {
							return lineChartFactory
									.getBatchProgressChart(dataArray);
						};

						return delegate;
					}
				});