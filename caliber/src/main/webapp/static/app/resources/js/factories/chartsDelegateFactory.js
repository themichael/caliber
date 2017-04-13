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
				function($log, doughnutChartFactory, doughnutChartDataFactory,
						barChartFactory, barChartDataFactory,
						radarChartFactory, radarChartDataFactory,
						lineChartFactory, lineChartDataFactory) {
					$log.debug("Booted charts delegate");

					var delegate = {};
					delegate.doughnut = {};
					delegate.doughnut.data = {};
					delegate.bar = {};
					delegate.bar.data = {};
					delegate.radar = {};
					delegate.radar.data = {};
					delegate.line = {};
					delegate.line.data = {};

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
					};

					/**
					 * ************************* Bar *************************
					 */

					// yani barchart
					delegate.bar.getAssessmentAveragesBatchWeekly = function(
							dataArray) {
						return barChartFactory
								.getBatchWeekAvgBarChart(dataArray);
					};
					// yani barchart data
					delegate.bar.data.getAssessmentAveragesBatchWeeklyData = function(
							batchId, week) {
						return barChartDataFactory.getBatchWeekAvgBarChart(
								batchId, week);
					};

					delegate.bar.getAverageTraineeScoresWeekly = function(
							dataArray) {
						return barChartFactory
								.getBatchWeekSortedBarChart(dataArray);
					};

					delegate.bar.data.getAverageTraineeScoresWeeklyData = function(
							batchId, week) {
						return barChartDataFactory
								.getBatchWeekSortedBarChartData(batchId, week);
					};

					delegate.bar.getAssessmentAveragesTraineeWeekly = function(
							dataArray) {
						return barChartFactory
								.getTraineeWeeklyAssessAvgs(dataArray)
					};

					delegate.bar.data.getAssessmentAveragesTraineeWeeklyData = function(
							batchId, weekNum, traineeId) {
						return barChartDataFactory.batchWeekTraineeAssessBar(
								batchId, weekNum, traineeId);
					};

					delegate.bar.getAssessmentAveragesTraineeOverall = function(
							dataArray) {
						return barChartFactory
								.getTraineeOverallAssessAvgs(dataArray)
					};

					delegate.bar.data.getAssessmentAveragesTraineeOverallData = function(
							batchId, traineeId) {
						return barChartDataFactory
								.batchOverallTraineeAssessBar(batchId,
										traineeId)
					};

					delegate.bar.getAverageTraineeScoresOverall = function(
							dataArray) {
						return barChartFactory
								.getBatchOverallBarChart(dataArray)
					};

					delegate.bar.data.getAverageTraineeScoresOverallData = function(
							batchId) {
						return barChartDataFactory
								.getBatchOverallBarChart(batchId)
					};

					/**
					 * ************************ Radar ************************
					 */
					delegate.radar.getTechnicalSkillsTraineeWeekly = function(
							dataArray, seriesName) {
						return radarChartFactory
								.getTraineeUpToWeekRadarChart(dataArray, seriesName);
					};

					delegate.radar.data.getTechnicalSkillsTraineeWeeklyData = function(
							week, traineeId) {
						return radarChartDataFactory
								.getTraineeUpToWeekRadarChart(week, traineeId);
					};

					delegate.radar.getTechnicalSkillsTraineeOverall = function(
							dataArray, seriesName) {
						return radarChartFactory
								.getTraineeOverallRadarChart(dataArray, seriesName);
					};

					delegate.radar.data.getTechnicalSkillsTraineeOverallData = function(
							traineeId) {
						return radarChartDataFactory
								.getTraineeOverallRadarChart(traineeId);
					};

					delegate.radar.getTechnicalSkillsBatchOverall = function(
							dataArray, seriesName) {
						return radarChartFactory
								.getBatchOverallRadarChart(dataArray, seriesName);
					};

					delegate.radar.data.getTechnicalSkillsBatchOverallData = function(
							batchId) {
						return radarChartDataFactory
								.getBatchOverallRadarChart(batchId);
					};

					
					delegate.radar.addRadarToExistingRadar = function(currentChartData, otherDataArray, seriesName){
						return radarChartFactory.addDataToExistingRadar(currentChartData, otherDataArray, seriesName);
					}
					
					
					
					/**
					 * ************************ Line ************************
					 */

					delegate.line.getWeeklyProgressBatchOverall = function(
							dataArray) {
						return lineChartFactory
								.getBatchOverallLineChart(dataArray);
					};

					delegate.line.data.getWeeklyProgressBatchOverallData = function(
							batchId) {
						return lineChartDataFactory
								.getBatchOverallLineChartData(batchId);
					};
					/*
					 * delegate.line.getWeeklyProgressBatchOverall =
					 * function(dataArray){ return lineChartFactory.(dataArray); };
					 * 
					 * delegate.line.data.getWeeklyProgressBatchOverallData =
					 * function(){ return lineChartDataFactory.(); };
					 */
					// yani linechart
					delegate.line.getWeeklyProgressTraineeWeekly = function(
							dataArray) {
						return lineChartFactory
								.getTraineeUpToWeekLineChart(dataArray);
					};

					// yani linechartdata
					delegate.line.data.getWeeklyProgressTraineeWeeklyData = function(
							week, traineeId) {
						return lineChartDataFactory
								.getTraineeUpToWeekLineChart(week, traineeId);
					};

					delegate.line.getWeeklyProgressTraineeOverall = function(
							dataArray) {
						return lineChartFactory
								.getTraineeOverallLineChart(dataArray);
					};

					delegate.line.data.getWeeklyProgressTraineeOverallData = function(
							batchId, traineeId) {
						return lineChartDataFactory.getTraineeOverallLineChart(
								batchId, traineeId);

					};

					return delegate;
				});