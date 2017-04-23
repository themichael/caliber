angular.module("charts").factory("lineChartFactory", function($log) {
	$log.debug("Booted Line Chart Factory");

	var lineChart = {};

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

	lineChart.getBatchOverallLineChart = function(dataArray) {
		var chartData = {};
		// data and labels
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
						max : 100,
						stepSize : 20
					}
				} ]
			}
		}
		// traverse through array of objects and grab labels and data
		angular.forEach(dataArray, function(value, key) {
			chartData.labels.push(key);
			chartData.data[0].push(value.toFixed(2));
		});
		return chartData;
	};

	// Yanilda
	lineChart.getTraineeUpToWeekLineChart = function(dataArray) {
		var chartData = {};

		// data and labels
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
						max : 100,
						stepSize : 20
					}
				} ]
			},

		};

		var series1 = [];
		var series2 = [];

		// traverse through array of objects and grab labels and
		// data
		angular.forEach(dataArray, function(value, key) {
			chartData.labels.push(key);
			series1.push(value[0].toFixed(2));
			series2.push(value[1].toFixed(2));
		});

		/*
		 * chartData.datasetOverride = [ { xAxisID : 'x-axis-1' } ];
		 */

		chartData.data.push(series1);
		chartData.data.push(series2);

		return chartData;
	};

	lineChart.getTraineeOverallLineChart = function(dataArray) {
		var chartData = {};
		chartData.series = [ "Trainee", "Batch" ]
		chartData.data = [];
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
						max : 100,
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
		return chartData;

	};
	// vpHome Line Chart
	lineChart.getCurrentBatchesAverageScoreChart = function(dataArray) {
		var chartData = {};
		chartData.data = [];
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
						max : 100,
						stepSize : 20
					}
				} ]
			}
		};
		var weeks = 0;
		angular.forEach(data, function(value, key) {
			chartData.series.push(key);
			var temp = [];
			angular.forEach(value, function(value2, key2) {
				temp.push(value2.toFixed(2));
			});
			if (value.length > weeks) {
				weeks = value.length;
			}
			chartData.data.push(temp);
		});
		for (var i = 1; i <= weeks; i++) {
			chartData.labels.push("Week " + i);
		}
		return chartData;
	}
	return lineChart;
});
