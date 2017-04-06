angular
		.module("qc")
		.controller(
				"qcHomeController",
				function($scope, $log, caliberDelegate, chartsDelegate,
						allBatches) {
					$log.log("All Batches: ");
					$log.debug(allBatches);
					/**
					 * ******************************************* On Start
					 * ***********************************************
					 */

					(function start() {
						// Finishes any left over ajax animation from another
						// page
						NProgress.done();

						// batch null check
						if (!allBatches || allBatches.length === 0) {
							$scope.noBatches = true;
							$scope.noBatchesMessage = "There are no current batches.";
							$scope.currentBatch = {
								trainingName : "Batch"
							};
						} else {
							$scope.noBatches = false;
							createDefaultCharts();
						}
					})();

					function createDefaultCharts() {
						$scope.batches = allBatches;
						$scope.currentBatch = allBatches[0];

						NProgress.start();
						createBatchCharts();
					}

					/**
					 * ********************************************* UI
					 * **************************************************
					 */
					var viewCharts = 1;
					$scope.currentTrainee = {
						name : "Trainee"
					};

					// on batch selection
					$scope.selectCurrentBatch = function(index) {
						$scope.currentTrainee = {
							name : "Trainee"
						};
						$scope.currentBatch = $scope.batches[index];
						viewCharts = 1;
						createBatchCharts();
					};

					// on trainee selection
					$scope.selectCurrentTrainee = function(index) {
						if (index === -1) {
							$scope.currentTrainee = {
								name : "Trainee"
							};
							viewCharts = 1;
							createBatchCharts();
						} else {
							$scope.currentTrainee = $scope.currentBatch.trainees[index];
							viewCharts = 3;
							createTraineeCharts();
						}
					};

					// hide filter tabs
					$scope.hideOtherTabs = function() {
						return $scope.currentBatch.trainingName !== "Batch";
					};

					// show charts
					$scope.showCharts = function(charts) {
						return charts === viewCharts;
					};

					// create charts on batch selection
					function createBatchCharts() {
						NProgress.start();
						caliberDelegate.agg
								.getAggTechBatch($scope.currentBatch.batchId)
								.then(
										function(data) {
											var radarChartObject2 = chartsDelegate.radar
													.getBatchRankComparisonChart(data);
											$scope.batchTechData = radarChartObject2.data;
											$scope.batchTechLabels = radarChartObject2.labels;
											$scope.batchTechSeries = radarChartObject2.series;
											$scope.batchTechOptions = radarChartObject2.options;
										}, function() {
											NProgress.done();
										});

						caliberDelegate.agg
								.getAggWeekBatch($scope.currentBatch.batchId)
								.then(
										function(data) {
											NProgress.done();
											var lineChartObject = chartsDelegate.line
													.getBatchProgressChart(data);
											$scope.batchProgressLabels = lineChartObject.labels;
											$scope.batchProgressData = lineChartObject.data;
											$scope.batchProgressSeries = lineChartObject.series;
											$scope.batchProgressOptions = lineChartObject.options;
											$scope.batchProgressDatasetOverride = lineChartObject.datasetOverride;
										}, function() {
											NProgress.done();
										});
					}

					// create charts on trainee selection
					function createTraineeCharts() {

						NProgress.start();
						caliberDelegate.agg
								.getAggWeekTrainee(
										$scope.currentTrainee.traineeId)
								.then(
										function(data) {
											$log.debug(data);
											NProgress.done();
											var lineChartObject = chartsDelegate.line
													.getTraineeProgressChart(data);
											$scope.traineeProgressLabels = lineChartObject.labels;
											$scope.traineeProgressSeries = lineChartObject.series;
											$scope.traineeProgressData = lineChartObject.data;
											$scope.traineeProgressDatasetOverride = lineChartObject.datasetOverride;
											$scope.traineeProgressOptions = lineChartObject.options;
										}, function() {
											NProgress.done();
										});
						caliberDelegate.agg
								.getAggTechTrainee(
										$scope.currentTrainee.traineeId)
								.then(
										function(data) {
											$log.debug(data);
											var radarChartObject = chartsDelegate.radar
													.getTraineeTechProgressChart(data);
											$scope.traineeTechData = radarChartObject.data;
											$scope.traineeTechLabels = radarChartObject.labels;
											$scope.traineeTechSeries = radarChartObject.series;
											$scope.traineeTechOptions = radarChartObject.options;
										}, function() {
											NProgress.done();
										});
					}

				});