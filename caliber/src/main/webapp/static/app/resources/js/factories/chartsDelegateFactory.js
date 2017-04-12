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

					/*
					 * delegate.bar.getAssessmentAveragesBatchWeekly =
					 * function(dataArray){ return barChartFactory.(dataArray); };
					 * 
					 * delegate.bar.data.getAssessmentAveragesBatchWeeklyData =
					 * function(){ return barChartDataFactory.(); };
					 */

//					delegate.bar.getAverageTraineeScoresOverall = function(
//							dataArray) {
//						return barChartFactory
//								.getBatchWeekSortedBarChart(dataArray);
//					};
					//yani barchart
					delegate.bar.getAssessmentAveragesBatchWeekly = function(dataArray){
						return barChartFactory.getBatchWeekAvgBarChart(dataArray);
					};
					//yani barchart data
					delegate.bar.data.getAssessmentAveragesBatchWeeklyData = function(){
						return barChartDataFactory.getBatchWeekAvgBarChart(batchId, week);
					};
					
					delegate.bar.getAverageTraineeScoresWeekly = function(dataArray){
						return barChartFactory.getBatchWeekSortedBarChart(dataArray);
					};
					
					delegate.bar.data.getAverageTraineeScoresWeeklyData = function(batchId, week){
						return barChartDataFactory.getBatchWeekSortedBarChartData(batchId, week);
					};
					
					/*delegate.bar.getAssessmentAveragesTraineeWeekly = function(dataArray){
						return barChartFactory.(dataArray);
					};
					
					delegate.bar.data.getAssessmentAveragesTraineeWeeklyData = function(){
						return barChartDataFactory.();
					}*/

					
					/*delegate.bar.getAssessmentAveragesTraineeOverall = function(dataArray){
						return barChartFactory.(dataArray);
					};

					delegate.bar.data.getAverageTraineeScoresOverallData = function(batchId, week) {
						return barChartDataFactory
								.getBatchWeekSortedBarChartData(batchId, week);
					};

					/*
					 * delegate.bar.getAssessmentAveragesTraineeWeekly =
					 * function(dataArray){ return barChartFactory.(dataArray); };
					 * 
					 * delegate.bar.data.getAssessmentAveragesTraineeWeeklyData =
					 * function(){ return barChartDataFactory.(); }
					 */

					/*
					 * delegate.bar.getAssessmentAveragesTraineeOverall =
					 * function(dataArray){ return barChartFactory.(dataArray); };
					 * 
					 * delegate.bar.data.getAssessmentAveragesTraineeOverallData =
					 * function(){ return barChartDataFactory.(); };
					 */

					/**
					 * ************************ Radar ************************
					 */
					delegate.radar.getTechnicalSkillsTraineeWeekly = function(
							dataArray) {
						return radarChartFactory
								.getTraineeUpToWeekRadarChart(dataArray);
					};

					delegate.radar.data.getTechnicalSkillsTraineeWeeklyData = function(week, traineeId) {
						return radarChartDataFactory
								.getTraineeUpToWeekRadarChart(week, traineeId);
					};

					delegate.radar.getTechnicalSkillsTraineeOverall = function(
							dataArray) {
						return radarChartFactory
								.getTraineeOverallRadarChart(dataArray);
					};

					delegate.radar.data.getTechnicalSkillsTraineeOverallData = function(traineeId) {
						return radarChartDataFactory
								.getTraineeOverallRadarChart(traineeId);
					};

					delegate.radar.getTechnicalSkillsBatchOverall = function(
							dataArray) {
						return radarChartFactory
								.getBatchOverallRadarChart(dataArray);
					};

					delegate.radar.data.getTechnicalSkillsBatchOverallData = function(batchId) {
						return radarChartDataFactory
								.getBatchOverallRadarChart(batchId);
					};

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
					/*delegate.line.getWeeklyProgressBatchOverall = function(dataArray){
						return lineChartFactory.(dataArray);
					};
					
					delegate.line.data.getWeeklyProgressBatchOverallData = function(){
						return lineChartDataFactory.();
					};*/
					
					delegate.line.getWeeklyProgressTraineeWeekly = function(dataArray){
						return lineChartFactory.getTraineeUpToWeekLineChart(dataArray);
					};
					
					
					delegate.line.data.getWeeklyProgressTraineeWeeklyData = function(){
						return lineChartDataFactory.getTraineeUpToWeekLineChart(week, traineeId);
					};
					
					/*delegate.line.getWeeklyProgressTraineeOverall = function(dataArray){
						return lineChartFactory.(dataArray);
					};

					/*
					 * delegate.line.getWeeklyProgressTraineeWeekly =
					 * function(dataArray){ return lineChartFactory.(dataArray); };
					 * 
					 * delegate.line.data.getWeeklyProgressTraineeWeeklyData =
					 * function(){ return lineChartDataFactory.(); };
					 */

					/*
					 * delegate.line.getWeeklyProgressTraineeOverall =
					 * function(dataArray){ return lineChartFactory.(dataArray); };
					 * 
					 * delegate.line.data.getWeeklyProgressTraineeOverallData =
					 * function(){ return lineChartDataFactory.(); };
					 */

					return delegate;
				});