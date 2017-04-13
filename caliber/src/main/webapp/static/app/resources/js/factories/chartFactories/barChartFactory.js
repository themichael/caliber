/**
 * 
 * @param $log
 * @returns {{}}
 */
angular.module("charts").factory("barChartFactory", function($log) {
	$log.debug("Booted Horizontal Bar Chart Factory");

	var barChart = {};

	// yanilda
	barChart.getBatchWeekAvgBarChart = function(dataArray) {
		var chartData = {};

		// data and labels
		chartData.series = [];
		chartData.data = [];
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
						labelString : 'Assessment'
					}
				} ]
			}
		};
		$log.debug("Yanilda");
		// traverse through array of objects and grab labels and data
		angular.forEach(dataArray, function(value, key) {
			if (value[0] > 0) {
				chartData.labels.push(key);
				chartData.series.push(key);
				chartData.data.push(value[0]);
			}
		});

		chartData.options = {
			scales : {
				yAxes : [ {
					scaleLabel : {
						display : true,
						labelString : 'Score'
					},

					ticks : {
						min : 40,
						max : 100,
						stepSize : 20
					}
				} ],
				xAxes : [ {
					scaleLabel : {
						display : true,
						labelString : 'Type'
					}

				} ]
			}
		};

		/*
		 * chartData.datasetOverride = [ { xAxisID : 'x-axis-1' } ];
		 */
		return chartData;
	};

	barChart.getTraineeWeeklyAssessAvgs = function(dataArray) {
		var chartData = {};

		// series
		chartData.series = [ "Trainee", "Batch" ];

		// labels and data
		chartData.data = [];
		chartData.labels = [];
		var trainee = [];
		var batch = [];
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
						labelString : 'Assessment'
					}
				} ]
			}
		};
		// loop through object array
		angular.forEach(dataArray, function(value, key) {
			trainee.push(value[0]);
			batch.push(value[1]);
			chartData.labels.push(key);
		});

		chartData.data.push(trainee);
		chartData.data.push(batch);

		return chartData;
	};

	barChart.getTraineeOverallAssessAvgs = function(dataArray) {
		var chartData = {};

		// series
		chartData.series = [ "Trainee", "Batch" ];
		// labels and data
		chartData.data = [];
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
						labelString : 'Assessment'
					}
				} ]
			}
		};
		var trainee = [];
		var batch = [];
		// loop through object array
		angular.forEach(dataArray, function(value, key) {
			trainee.push(value[0]);
			batch.push(value[1]);
			chartData.labels.push(key);
		});

		chartData.data.push(trainee);
		chartData.data.push(batch);

		return chartData;
	};

	barChart.getBatchOverallBarChart = function(dataArray) {
		var chartData = {};

		// series
		chartData.series = [ "Trainee", "Average" ];

		// labels and data
		chartData.data = [];
		chartData.labels = [];
		// make all bar same color
		chartData.data.push([]);
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
						labelString : 'Trainee'
					}
				} ]
			}
		};

		// loop through object array
		angular.forEach(dataArray, function(value, key) {
			chartData.labels.push(key);
			chartData.data[0].push(value.toFixed(2));
		});
		return chartData;
	};

	barChart.getBatchWeekSortedBarChart = function(dataArray) {
		var chartData = {};
		// making a sorted array
		var sorted = [];
		// make the object to an array
		angular.forEach(dataArray, function(value, key) {
			sorted.push({
				'name' : key,
				'value' : value
			})
		});
		// sorted the array
		sorted.sort(function(a, b) {
			return b.value - a.value;
		});

		chartData.series = [ 'Average Score' ];
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
						labelString : 'Trainee'
					}
				} ]
			}
		};

		angular.forEach(sorted, function(obj) {
			chartData.labels.push(obj.name);
			chartData.data[0].push(obj.value);
		});
		return chartData;
	};

	return barChart;
});