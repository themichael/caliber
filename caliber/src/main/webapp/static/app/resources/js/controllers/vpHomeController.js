angular
		.module("vp")
		.controller(
				"vpHomeController",
				function($scope, $log, caliberDelegate, chartsDelegate, $filter) {
					$log.debug("Booted vp home controller.");
					$scope.averageScoreData = [];
					$scope.auditData = [];
					$scope.selectedStateFromLineChar="";
					$scope.selectedStateFromBarChar="";
					(function() {
						// Finishes any left over ajax animation from another
						// page
						NProgress.done();
						createDefaultCharts();
					})();

					function createDefaultCharts(){
						NProgress.start();
						getCurrentBatchesAuditData();
						getCurrentBatchesAvergeScoreData();
					}

					//restructured graph functions

					function createAllBatchesCurrentWeekQCStats(data) {
											var barChartObj = chartsDelegate.bar
													.getAllBatchesCurrentWeekQCStats(data);
											$scope.stackedBarData = barChartObj.data;
											$scope.stackedBarLabels = barChartObj.labels;
											$scope.stackedBarSeries = barChartObj.series;
											$scope.stackedBarOptions = barChartObj.options;
											$scope.stackedBarColors = barChartObj.colors;
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

					function getCurrentBatchesAuditData(){
						chartsDelegate.bar.data
						.getAllBatchesCurrentWeekQCStatsData()
						.then(
								function(data) {
									NProgress.done();
									$scope.auditData = data;
									createAllBatchesCurrentWeekQCStats(data);
								}, function() {
									NProgress.done();
								});
					}

					$scope.onLineCharAddressStateChange = function(state){
						if(state!="undefined"){
							$scope.selectedStateFromLineChar = state;
							filterLineChartByState(state);
						}
					}

					$scope.onLineCharAddressCityChange = function(city){
						filterLineChartByCity(city);
					}

					$scope.onBarCharAddressStateChange = function(state){
						if(state!="undefined"){
							$scope.selectedStateFromBarChar = state;
							filterBarChartByState(state);
						}
					}

					$scope.onBarCharAddressCityChange = function(city){
						filterBarChartByCity(city);
					}


					var filterLineChartByState = function(state){
						if(state){
							var filteredData = $scope.averageScoreData.filter(function(batch){
								if(batch.address)
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
								if(batch.address)
									return batch.address.city==city;
							});
							createCurrentBatchesAverageScoreChart(filteredData);
						}else{
							filterLineChartByState($scope.selectedStateFromLineChar);
						}
					}

					var filterBarChartByState = function(state){
						if(state){
							var filteredData = $scope.auditData.filter(function(batch){
								if(batch.address)
									return batch.address.state==state;
							});
							createAllBatchesCurrentWeekQCStats(filteredData);
						}else{
							createAllBatchesCurrentWeekQCStats($scope.auditData);
						}
					}

					var filterBarChartByCity = function(city){
						if(city){
							var filteredData = $scope.auditData.filter(function(batch){
								if(batch.address)
									return batch.address.city==city;
							});
							createAllBatchesCurrentWeekQCStats(filteredData);
						}else{
							filterBarChartByState($scope.selectedStateFromBarChar);
						}
					}
				});
