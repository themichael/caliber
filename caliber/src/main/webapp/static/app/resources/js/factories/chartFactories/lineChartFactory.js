angular.module("charts").factory("lineChartFactory", function($log) {
	$log.debug("Booted Line Chart Factory");

	var lineChart = {};

	lineChart.getBatchOverallLineChart = function(dataArray) {
		var chartData = {};

		// data and labels
		chartData.data = [];
		chartData.data.push([]);
		chartData.labels = [];
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
				} ],
				xAxes : [ {
					scaleLabel : {
						display : true,
						labelString : 'Week'
					}
				} ]
			}
		}
		// traverse through array of objects and grab labels and data
		var go = true;
		angular.forEach(dataArray, function(value, key) {
			if (value === 0)
				go = false;
			if (go) {
				chartData.labels.push(key);
				chartData.data[0].push(value.toFixed(2));
			}
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
		chartData.series = [ "batch", "trainee" ]
		chartData.data = [];
		// chartData.labels = [];

		$log.debug("THIS IS THE DOUBLE[] DATA SET");
		$log.debug(dataArray);
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
	return lineChart;
});
