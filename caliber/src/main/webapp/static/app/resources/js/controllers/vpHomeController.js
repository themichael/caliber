angular
		.module("vp")
		.controller(
				"vpHomeController",
				function($scope, $log, caliberDelegate, chartsDelegate, $filter) {
					$log.debug("Booted vp home controller.");
					$scope.averageScoreData = {};
					$scope.states=[];
					$scope.cities=[];
					$scope.onLineCharAddressStateChange = onLineCharAddressStateChange;
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
									$scope.states = generateStateList(data);
									createCurrentBatchesAverageScoreChart(data);
									NProgress.done();
								},function() {
									NProgress.done();
								});
					}
					
					function generateStateList(addressList){
						var states = [];
						angular.forEach(addressList, function(value, key){
							states.push(value.address.state);
						});
						let uniqueStates = [...new Set(states)];
						return uniqueStates;
					}
					
					function generateCityListFromState(addressList, state){
						var cities = [];
						angular.forEach(addressList, function(value, key){
							if(value.address.state == state){
								cities.push(value.address.city);
							}
						});
						let uniqueCities = [...new Set(cities)];
						return uniqueCities;
					}
					
					function onLineCharAddressCityChange(city){
						// TODO filter the addressList to city
					}
					
					function filterAddressByState(){
						
					}
					
					function onLineCharAddressStateChange(state){
						if(state!=""){
							// TODO filter the addressList to state;
							console.log(state);
//							$scope.cities = generateCityListFromState($scope.averageScoreData,state);
							
						}
					}

				});