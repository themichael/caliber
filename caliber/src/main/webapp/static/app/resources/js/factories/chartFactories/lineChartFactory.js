/**
 * 
 * @param $log
 * @returns {{}}
 */
angular.module("charts").factory("lineChartFactory", function($log) {
	$log.debug("Booted Line Chart Factory");

	var lineChart = {};

	lineChart.getBatchOverallLineChart = function(dataArray) {
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
	
	return lineChart;
});