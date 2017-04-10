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
				function($log, hbarChartFactory, radarChartFactory,
						lineChartFactory, doughnutChartDataFactory) {
					$log.debug("Booted charts delegate");

					var delegate = {};

					delegate.hbar = {};
					delegate.doughnut = {};
					delegate.radar = {};
					delegate.line = {};

					/**
					 * ********************* Doughnut *********************
					 */
					delegate.doughnut.getQCStats = function(dataArray) {
						return doughnutChartDataFactory.report
								.batchWeekQCPie(dataArray);
					};

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