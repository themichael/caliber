/**
 * 
 * @param $log
 * @returns {{}}
 */
angular
		.module("charts")
		.factory(
				"radarChartFactory",
				function($log) {
					$log.debug("Booted radarChartFactory");

					var radar = {};
					var mainColor = {
						backgroundColor : 'rgba(114, 164, 194, .5)',
						pointBackgroundColor : 'rgba(114, 164, 194, .5)',
						borderColor : 'rgba(114, 164, 194, 1)',
						pointHoverBackgroundColor : 'rgba(114, 164, 194, .3)',
						pointHoverBorderColor : 'rgba(114, 164, 194, .3)',
						pointBorderColor : '#fff'
					}

					var secondaryColor = {
						backgroundColor : 'rgba(252, 180, 20, .6)',
						pointBackgroundColor : 'rgba(252, 180, 20, .6)',
						borderColor : 'rgba(252, 180, 20, 1)',
						pointHoverBackgroundColor : 'rgba(252, 180, 20, .3)',
						pointHoverBorderColor : 'rgba(252, 180, 20, .3)',
						pointBorderColor : '#fff'
					}

					radar.getTraineeUpToWeekRadarChart = function(dataArray,
							seriesName) {
						return createGenericRadarChartObject(dataArray,
								seriesName);
					};

					radar.getTraineeOverallRadarChart = function(dataArray,
							seriesName) {
						return createGenericRadarChartObject(dataArray,
								seriesName);
					};

					radar.getBatchOverallRadarChart = function(dataArray,
							seriesName) {
						return createGenericRadarChartObject(dataArray,
								seriesName);
					};
		
					var createGenericRadarChartObject = function(dataArray,
							seriesName) {
						var chartData = {};
						chartData.colors = [ mainColor ];
						chartData.series = [];
						chartData.series.push(seriesName);

						chartData.labels = [];

						chartData.data = [];
						chartData.data.push([]);

						angular.forEach(dataArray, function(value, key) {
							chartData.labels.push(key);
							chartData.data[0].push(value.toFixed(2));
						});

						chartData.options = radarOptions;
						return chartData;
					}

					radar.addDataToExistingRadar = function(currentChartData,
							otherDataArray, seriesName) {
						var newData = [];
						var totalTechs = currentChartData.labels.length;
						currentChartData.series.push(seriesName);

						for (var i = 0; i < totalTechs; i++) {
							if (otherDataArray
									.hasOwnProperty(currentChartData.labels[i])) {
								newData
										.push((otherDataArray[currentChartData.labels[i]])
												.toFixed(2));
							}
						}

						currentChartData.data.push(newData);
						return currentChartData;
					}
					radar.createCombineBatchAndAllTrainees = function(dataSet){
						var chartData = {};
						
						chartData.colors= [ mainColor ];
						
						chartData.series = [];
						chartData.labels = [];
						chartData.data = [];

						angular.forEach(dataSet, function(value, key) {
							chartData.series.push(key);
							var averageTemp = [];
							angular.forEach(value, function(average, assess) {
								if(!chartData.labels.includes(assess)) {
									chartData.labels.push(assess);
								}
								averageTemp.push(average.toFixed(2));
							})
							chartData.data.push(averageTemp);
						});
						chartData.options = radarOptions;
						return chartData;
					}
					radar.createFromTwoDataSets = function(batchDataSet,
							traineeDataSet, batchSeriesName, traineeSeriesName) {
						$log.debug("radarChartFactory: createFromTwoDataSets");
						var chartData = {};
						var batchSeries = [];
						var traineeSeries = [];
						var labels = [];

						angular.forEach(batchDataSet, function(value, key) {
							if (traineeDataSet.hasOwnProperty(key)) {
								traineeSeries.push((traineeDataSet[key])
										.toFixed(2));
								batchSeries
										.push((batchDataSet[key]).toFixed(2));
								labels.push(key);
							}
						});

						chartData.labels = labels;

						chartData.series = [];
						chartData.series.push(traineeSeriesName);
						chartData.series.push(batchSeriesName);

						chartData.data = [];
						chartData.data.push(traineeSeries);
						chartData.data.push(batchSeries);

						chartData.colors = [ mainColor, secondaryColor ];

						chartData.options = radarOptions;
						$log.debug(chartData);
						return chartData;
					}

					radarOptions = {
						legend : {
							display : true
						},
						scale : {
							reverse : false,
							ticks : {
								beginAtZero : false,
								fixedStepSize : 10,
								max : 100,
								suggestedMin : 40
							}
						}
					};
					return radar;
				});