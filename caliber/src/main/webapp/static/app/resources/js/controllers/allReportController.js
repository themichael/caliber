angular
		.module("charts")
		.controller(
				"allReportController",
				function($scope, $log, caliberDelegate, chartsDelegate,
						allBatches) {

					$log.debug("Booted Report Controller");
					$log.debug("Peacepapi is here!!!!!");

					(function start() {
						// Finishes any left over ajax animation from another
						// page
						NProgress.done();

						// batch null check
						if (!allBatches || allBatches.length === 0) {
							$scope.noBatches = true;
							$scope.noBatchesMessage = "No Batches belonging to you were found.";
						} else {
							$scope.noBatches = false;
							createDefaultCharts();
						}
					})();

					/**
					 * ********************************************* UI
					 * **************************************************
					 */
					var viewCharts = 0;

					$scope.batches = allBatches;
					$scope.currentBatch = {
						trainingName : "Batch"
					};
					$scope.currentTrainee = {
						name : "Trainee"
					};

					// on batch selection
					$scope.selectCurrentBatch = function(index) {
						$scope.currentTrainee = {
							name : "Trainee"
						};
						// turn of batches
						if (index === -1) {
							viewCharts = 0;
							$scope.currentBatch = {
								trainingName : "Batch"
							};
							createDefaultChart();
						} else {
							$scope.currentBatch = $scope.batches[index];
							viewCharts = 1;
							createBatchCharts();
						}
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

					function createDefaultCharts() {
						// Finishes any left over ajax animation from another
						// page
						NProgress.done();
						NProgress.start();
						chartsDelegate.doughnut.data
								.getQCStatsData(1050, 1)
								.then(
										function(data) {
											$log.debug(data);
											NProgress.done();
											var doughnutChartObject = chartsDelegate.doughnut
													.getQCStats(data);
											$scope.qcStatsLabels = doughnutChartObject.labels;
											$scope.qcStatsData = doughnutChartObject.data;
											$scope.qcStatsOptions = doughnutChartObject.options;
										}, function() {
											NProgress.done();
										});
					}

				});
