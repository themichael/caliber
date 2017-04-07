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
				"barChartDelegate",
				function($log, hbarChartFactory, radarChartFactory,
						lineChartFactory) {
					$log.debug("Booted charts delegate");

					var delegate = {};
					delegate.bar = {};
					delegate.pie = {};
					delegate.radar = {};
					delegate.line = {};

					/**
					 * ********************* Horizontal Bar
					 * *********************
					 */
					delegate.hbar.getBatchAvgChart = function(dataArray) {
						return hbarChartFactory.getBatchAvgChart(dataArray);
					};

					delegate.hbar.getTrainerEvalChart = function(dataArray) {
						return hbarChartFactory.getTrainerEvalChart(dataArray);
					};

					delegate.hbar.getAllBatchesEvalChart = function(dataArray) {
						return hbarChartFactory
								.getAllBatchesEvalChart(dataArray);
					};

					delegate.hbar.getBatchTechEvalChart = function(dataArray) {
						return hbarChartFactory
								.getBatchTechEvalChart(dataArray);
					};

					/**
					 * ************************** Radar
					 * *************************
					 */
					delegate.radar.getBatchRankComparisonChart = function(
							dataArray) {
						return radarChartFactory
								.getBatchRankComparisonChart(dataArray);
					};

					delegate.radar.getTraineeTechProgressChart = function(
							dataArray) {
						return radarChartFactory
								.getTraineeTechProgressChart(dataArray);
					};

					delegate.radar.getAllBatchRankComparisonChart = function(
							dataArray1, dataArray2) {
						return radarChartFactory
								.getAllBatchRankComparisonChart(dataArray1,
										dataArray2);
					};

					/**
					 * ************************** Line
					 * **************************
					 */
					delegate.line.getTraineeProgressChart = function(dataArray) {
						return lineChartFactory
								.getTraineeProgressChart(dataArray);
					};

					delegate.line.getBatchProgressChart = function(dataArray) {
						return lineChartFactory
								.getBatchProgressChart(dataArray);
					};

					return delegate;
				});