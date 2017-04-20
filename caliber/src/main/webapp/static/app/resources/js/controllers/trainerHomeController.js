angular
		.module("trainer")
		.controller(
				"trainerHomeController",
				function($rootScope, $scope, $state, $log, caliberDelegate, chartsDelegate,
						allBatches) {
					$log.debug("Booted trainer home controller.");
					
					/**
					 * ********************************* On Start
					 * ****************************
					 */

					$scope.currentBatch = allBatches[0];
					var reportCurrentWeek = OVERALL;
					var currentBatchWeeks = [];


					(function () {
						// Finishes any left over AJAX animation
						NProgress.done();
						
						// batch null check
						if ($scope.currentBatch === null) {
							$scope.noBatch = true;
						} else {
							$scope.noBatch = false;
							$scope.selectedYear = Number($scope.currentBatch.startDate.substr(0,4));
							batchYears();
							getCurrentBatchWeeks($scope.currentBatch.weeks);
							selectView($scope.currentBatch.batchId,
									$scope.reportCurrentWeek,
									$scope.currentTraineeId);

						}

					})();
					
					
					/**
					 * ********************************************* UI
					 * **************************************************
					 */

					
					
					
					
					
					
				});
