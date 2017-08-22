angular
		.module("trainer")
		.controller(
				"trainerHomeController",
				function($rootScope, $scope, $state, $log, caliberDelegate,
						chartsDelegate, allBatches) {
					$log.debug("Booted trainer home controller.");

					/**
					 * ********************************* On Start
					 * ****************************
					 */
					$scope.currentBatch = allBatches[0];
					if($scope.currentBatch === undefined || $scope.currentBatch === null) 
						return;

					(function() {
						// Finishes any left over AJAX animation
						NProgress.done();

						createDefaultCharts();

					})();

					function createDefaultCharts() {
					    NProgress.start();
						createAverageTraineeScoresOverall();
						createTechnicalSkillsBatchOverall();
						createCurrentQCStatus();
					}

					/**
					 * ********************************************* UI
					 * **************************************************
					 */

					// *******************************************************************************
					// *** Bar Charts
					// *******************************************************************************
					function createAverageTraineeScoresOverall() {
						chartsDelegate.bar.data
								.getAverageTraineeScoresOverallData(
										$scope.currentBatch.batchId)
								// confirm if batch or trainee
								.then(
										function(data) {
											NProgress.done();
											var barChartObject = chartsDelegate.bar
													.getAverageTraineeScoresOverall(data, 80, $scope.currentBatch.borderlineGradeThreshold, $scope.currentBatch.goodGradeThreshold);
											$scope.batchOverAllLabels = barChartObject.labels;
											$scope.batchOverAllData = barChartObject.data;
											$scope.batchOverAllOptions = barChartObject.options;
											$scope.batchOverAllColors = barChartObject.colors;
											$scope.batchOverAllDsOverride = barChartObject.datasetOverride;
										}, function() {
											NProgress.done();
										});

					}

					// *******************************************************************************
					// *** Radar Charts
					// *******************************************************************************

					function createTechnicalSkillsBatchOverall() {
						$log.debug("createTechnicalSkillsBatchOverall");
						chartsDelegate.radar.data
								.getTechnicalSkillsBatchOverallData(
										$scope.currentBatch.batchId)
								// batchId
								.then(
										function(data) {
											NProgress.done();
											var radarBatchOverallChartObject = chartsDelegate.radar
													.getTechnicalSkillsBatchOverall(
															data,
															$scope.currentBatch.trainingName);
											$scope.radarBatchOverallData = radarBatchOverallChartObject.data;
											$scope.radarBatchOverallOptions = radarBatchOverallChartObject.options;
											$scope.radarBatchOverallLabels = radarBatchOverallChartObject.labels;
											$scope.radarBatchOverallSeries = radarBatchOverallChartObject.series;
											$scope.radarBatchOverallColors = radarBatchOverallChartObject.colors;

											$scope.radarBatchOverallTable = chartsDelegate.utility
													.dataToTable(radarBatchOverallChartObject);
										});

					}

					// *******************************************************************************
					// *** Doughnut Charts
					// *******************************************************************************

					function createCurrentQCStatus() {
						$log.debug($scope.currentBatch.batchId);
						chartsDelegate.doughnut.data
								.getCurrentQCStatsData($scope.currentBatch.batchId)
								.then(
										function(data) {
											$log.debug(data);
											NProgress.done();
											var doughnutChartObject = chartsDelegate.doughnut
													.getCurrentQCStats(data);
											$scope.currentQCStatsLabels = doughnutChartObject.labels;
											$scope.currentQCStatsData = doughnutChartObject.data;
											$scope.currentQCStatsOptions = doughnutChartObject.options;
											$scope.currentQCStatsColors = doughnutChartObject.colors;
										});

					}
					
					// *******************************************************************************
					// ALERT POP UP
					//      NOTE: This is for existing batches that do not have their locations updated 
					//       *already-ended batches( old batches) do not need to be updated     
					//*********************************************************************************
					$scope.alertPopup = function() {
						
						
						caliberDelegate.trainer.getAllBatches()
						.then(
								function(batches) {
									// holds all batches that current trainer has
									$scope.allBatches = batches; 
									
									// Holds the address of the a batch
									var address;
									
									// Holds the end-date of a batch
									var endDate;
									
									// get current Date
									var cd = new Date();
									
									// Format the date (in order to do comparison
									var currentDate =  cd.getFullYear() + "-" + cd.getMonth() + "-" +cd.getDate();
									
									// holds the batches that needs to be updated
									var alertBatches = "";
									
									
									var showAlert = false;
									
									
									// Iterate through each batch
									for(var i = 0; i < batches.length; i++) {
										address = batches[i].address;
										endDate = batches[i].endDate;
										
										// check if the batch needs to be updated
										if(address === null && currentDate < endDate) {
											showAlert = true;
											alertBatches += "" + batches[i].trainingName + "\n";
										}
									}
									
									if(showAlert) {
										// TODO if we have time: new alert window style, instead of the alert()
										alert("Please update the location on the following batches:\n" + alertBatches +"\n\nThank you!");
									}
								});
					}

				});
