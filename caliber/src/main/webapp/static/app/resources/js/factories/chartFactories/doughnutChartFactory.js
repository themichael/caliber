/**
 * 
 * @param $log
 * @returns {{}}
 */
angular.module("charts").factory("doughnutChartFactory", function($log) {
	$log.debug("Booted Doughtnut Bar Chart Factory");

	var doughnutChart = {};

	doughnutChart.batchWeekQCPie = function(dataArray) {
		var chartData = {};

		// data and labels
		chartData.data = [];
		chartData.labels = [];
		chartData.options = {legend:{display:true}};

		// traverse through array of objects and grab labels and data
		angular.forEach(dataArray, function(key, value) {
			chartData.labels.push(key);
			chartData.data.push(value);
		});
		
		return chartData;
	};

	return doughnutChart;
});