angular
		.module("vp")
		.controller(
				"vpHomeController",
				function($scope, $log, caliberDelegate, chartsDelegate, $filter) {
					$log.debug("Booted vp home controller.");
					$scope.averageScoreData = {};
					$scope.filterState="";
					(function() {
						// Finishes any left over ajax animation from another
						// page
						NProgress.done();
						getCurrentBatchesAvergeScoreData();
						createAllBatchesCurrentWeekQCStats();
					})();

					function createAllBatchesCurrentWeekQCStats() {
						chartsDelegate.bar.data
								.getAllBatchesCurrentWeekQCStatsData()
								.then(
										function(data) {
											NProgress.done();
											var barChartObj = chartsDelegate.bar
													.getAllBatchesCurrentWeekQCStats(data);

											$scope.stackedBarData = barChartObj.data;
											$scope.stackedBarLabels = barChartObj.labels;
											$scope.stackedBarSeries = barChartObj.series;
											$scope.stackedBarOptions = barChartObj.options;
											$scope.stackedBarColors = barChartObj.colors;

										}, function() {
											NProgress.done();
										});
					}
					function createCurrentBatchesAverageScoreChart(data) {
											var lineChartObj = chartsDelegate.line
													.getCurrentBatchesAverageScoreChart(data);
											$scope.currentBatchesLineData = lineChartObj.data;
											$scope.currentBatchesLineLabels = lineChartObj.labels;
											$scope.currentBatchesLineSeries = lineChartObj.series;
											$scope.currentBatchesLineOptions = lineChartObj.options;
											$scope.currentBatchesLineColors = lineChartObj.colors;
											$scope.currentBatchesDsOverride = lineChartObj.datasetOverride;
					}
					
					function getCurrentBatchesAvergeScoreData(){
						chartsDelegate.line.data
						.getCurrentBatchesAverageScoreChartData()
						.then(
								function(data) {
									$scope.averageScoreData = data;
									createCurrentBatchesAverageScoreChart(data);
									NProgress.done();
								},function() {
									NProgress.done();
								});
					}
					
					$scope.onLineCharAddressStateChange = function(state){
						$scope.filterState = state;
						filterLineChartByState(state);
					}
					
					$scope.onLineCharAddressCityChange = function(city){
							filterLineChartByCity(city);
					}
					
					
					
					var filterLineChartByState = function(state){
						if(state){
							var filteredData = $scope.averageScoreData.filter(function(batch){
								return batch.address.state==state;
							});
							createCurrentBatchesAverageScoreChart(filteredData);
						}else{
							createCurrentBatchesAverageScoreChart($scope.averageScoreData);
						}
					}
					
					var filterLineChartByCity = function(city){
						if(city){
							var filteredData = $scope.averageScoreData.filter(function(batch){
								return batch.address.city==city;
							});
							createCurrentBatchesAverageScoreChart(filteredData);	
						}else{
							filterLineChartByState($scope.filterState);
						}
					}

				});