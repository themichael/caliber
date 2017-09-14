/**
 * Team !Uncharted
 * 
 * @author Pier Yos
 * @author Hossain Yahya
 * @author Yanilda Peralta
 * @author Igor Gluskin
 * @author Ateeb Khawaja
 * 
 */
angular.module("charts").factory("lineChartFactory", function($log) {
	$log.debug("Booted Line Chart Factory");

	Chart.defaults.global.elements.line.fill = false;
	console.log("load");
	
	var lineChart = {};

	var mainColor = {
		backgroundColor : 'rgba(114, 164, 194, .5)',
		pointBackgroundColor : 'rgba(114, 164, 194, .5)',
		borderColor : 'rgba(114, 164, 194, 1)',
		pointHoverBackgroundColor : 'rgba(114, 164, 194, .3)',
		pointHoverBorderColor : 'rgba(114, 164, 194, .3)',
		pointBorderColor : '#fff'
	};

	var secondaryColor = {
		backgroundColor : 'rgba(252, 180, 20, .6)',
		pointBackgroundColor : 'rgba(252, 180, 20, .6)',
		borderColor : 'rgba(252, 180, 20, 1)',
		pointHoverBackgroundColor : 'rgba(252, 180, 20, .3)',
		pointHoverBorderColor : 'rgba(252, 180, 20, .3)',
		pointBorderColor : '#fff'
	};

	lineChart.getBatchOverallLineChart = function(dataArray) {
		var chartData = {};
		chartData.data = [];
		chartData.data.push([]);
		chartData.labels = [];
		chartData.colors = [ mainColor ];
		chartData.options = {
			scales : {
				yAxes : [ {
					scaleLabel : {
						display : true,
						labelString : 'Average'
					},
					ticks : {
						suggestedMin : 40,
						suggestedmax : 100,
						stepSize : 20
					}
				} ]
			}
		};
		chartData.datasetOverride = [ {
			fill : false
		} ];
		// traverse through array of objects and grab labels and data

		angular.forEach(dataArray, function(value, key) {
			chartData.labels.push(key);
			chartData.data[0].push(value.toFixed(2));
		});
		return chartData;
	};

	lineChart.getTraineeUpToWeekLineChart = function(dataArray) {
		var chartData = {};

		chartData.data = [];
		chartData.labels = [];
		chartData.series = [ "Trainee", "Batch" ];
		chartData.colors = [ mainColor, secondaryColor ];
		chartData.options = {
			scales : {
				xAxes : [ {
					scaleLabel : {
						display : true,
						labelString : 'Week'
					}

				} ],
				yAxes : [ {
					scaleLabel : {
						display : true,
						labelString : 'Score'
					},

					ticks : {
						suggestedMin : 40,
						suggestedmax : 100,
						stepSize : 20
					}
				} ]
			},
		};
		
		var series1 = [];
		var series2 = [];

		angular.forEach(dataArray, function(value, key) {
			chartData.labels.push(key);
			series1.push(value[0].toFixed(2));
			series2.push(value[1].toFixed(2));
		});

		chartData.data.push(series1);
		chartData.data.push(series2);
		chartData.datasetOverride = [ {
			fill : false
		}, {
			fill : false
		} ];
		return chartData;
	};

	lineChart.getTraineeOverallLineChart = function(dataArray) {
		var chartData = {};
		chartData.series = [ "Trainee", "Batch" ];
		chartData.colors = [ mainColor, secondaryColor ];
		chartData.data = [];
		chartData.options = {
			scales : {
				xAxes : [ {
					scaleLabel : {
						display : true,
						labelString : 'Week'
					}

				} ],
				yAxes : [ {
					scaleLabel : {
						display : true,
						labelString : 'Score'
					},

					ticks : {
						suggestedMin : 40,
						suggestedmax : 100,
						stepSize : 20
					}
				} ]
			},
		};
		// loop through object array
		var trainee = [];
		var batch = [];
		var week = [];
		angular.forEach(dataArray, function(value, key) {
			trainee.push(value[0].toFixed(2));
			batch.push(value[1].toFixed(2));
			week.push(key);
		});
		chartData.data.push(trainee);
		chartData.data.push(batch);
		chartData.labels = week;
		
		chartData.datasetOverride = [ {
			fill : false
		}, {
			fill : false
		} ];
		
		return chartData;

	};

	lineChart.getCurrentBatchesAverageScoreChart = function(dataArray) {
		var chartData = {};
		chartData.data = [];
		chartData.colors = [ mainColor, secondaryColor ];
		chartData.series = [];
		chartData.labels = [];

		chartData.options = {
				legend : {
					display : true,
					labels : {
						boxWidth : 10
					}
				},	
			scales : {
				xAxes : [ {
					scaleLabel : {
						display : true,
						labelString : 'Week'
					}

				} ],
				yAxes : [ {
					scaleLabel : {
						display : true,
						labelString : 'Score'
					},

					ticks : {
						suggestedMin : 40,
						suggestedmax : 100,
						stepSize : 20
					}
				} ]
			}
		};
	
		var highestWeek = 0;
		angular.forEach(dataArray, function(batch) {
			var currentWeek = 1;
			chartData.series.push(batch.label);
			var temp = [];
			angular.forEach(batch.grades, function(value2, key2) {
				while (currentWeek < key2) {
					temp.push(0);
					currentWeek++;
				}
				temp.push(value2.toFixed(2));
				currentWeek++;
				if (currentWeek > highestWeek) {
					highestWeek = currentWeek;
				}
			});
			chartData.data.push(temp);
		});
		chartData.datasetOverride = [];
		for (var i = 1; i < highestWeek; i++) {
			chartData.labels.push("Week " + i);
			chartData.datasetOverride.push({fill:false});
		}
		return chartData;
	};
	return lineChart;
});
