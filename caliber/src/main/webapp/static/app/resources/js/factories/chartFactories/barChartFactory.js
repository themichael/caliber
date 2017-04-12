/**
 * 
 * @param $log
 * @returns {{}}
 */
angular.module("charts").factory("barChartFactory", function($log) {
	$log.debug("Booted Horizontal Bar Chart Factory");

	var barChart = {};

	barChart.getBatchAvgChart = function(dataArray) {
		var chartData = {};

		// data and labels
		chartData.data = [];
		chartData.labels = [];

		// traverse through array of objects and grab labels and data
		dataArray.forEach(function(element) {
			chartData.labels.push(element.trainee);
			chartData.data.push(element.average);
		});

		chartData.datasetOverride = [ {
			xAxisID : 'x-axis-1'
		} ];

		return chartData;
	};
	// yanilda
	barChart.getBatchWeekAvgBarChart = function(dataArray) {
		var chartData = {};

		// data and labels
		chartData.data = [];
		chartData.labels = [];

		// traverse through array of objects and grab labels and data
		dataArray.forEach(function(key, value) {
			chartData.labels.push(key);
			chartData.data.push(value[0]);
		});

		chartData.datasetOverride = [ {
			xAxisID : 'x-axis-1'
		} ];

		return chartData;
	};

	barChart.getTrainerEvalChart = function(dataArray) {
		var chartData = {};

		// series
		chartData.series = [ "QC Eval" ];

		// labels and data
		chartData.data = [];
		chartData.labels = [];

		// loop through object array
		dataArray.forEach(function(element) {
			chartData.data.push(element.score);
			chartData.labels.push(element.name);
		});

		return chartData;
	};

	barChart.getAllBatchesEvalChart = function(data, batches) {
		var chartData = {};

		// series
		chartData.series = [ "All Batch Eval" ];

		// labels and data
		chartData.data = [];
		chartData.labels = [];

		// loop through object array
		angular.forEach(data, function(value, key) {
			$log.debug(value);
			chartData.data.push(value[0]);
			$log.debug(key);
			chartData.labels.push(key);
		});

		return chartData;
	};

	barChart.getBatchTechEvalChart = function(dataArray) {
		var chartData = {};

		// series
		chartData.series = [ "Tech Batch Eval" ];

		// labels and data
		chartData.data = [];
		chartData.labels = [];

		// loop through object array
		dataArray.forEach(function(key, value) {
			chartData.data.push(value[0]);
			chartData.labels.push(key);
		});

		return chartData;
	};

	barChart.getBatchOverallBarChart = function(dataArray) {
		var chartData = {};

		// series
		chartData.series = [ "batchTechSeries" ];

		// labels and data
		chartData.data = [];
		chartData.labels = [];
		chartData.options = {
			legend : {
				display : true
			}
		};

		// loop through object array
		angular.forEach(dataArray.data, function(value, key) {
			chartData.labels.push(key);
			chartData.data.push(value);
		});

		return chartData;
	};
	
	barChart.getBatchWeekSortedBarChart = function(dataArray) {
		var chartData = {};

		chartData.series = ['Average Score'];
		chartData.data = [];
		chartData.labels = [];
		
		angular.forEach(dataArray, function(value, key) {
			chartData.labels.push(key);
			chartData.data.push(value);
		});
		return chartData;
	};

	return barChart;
});