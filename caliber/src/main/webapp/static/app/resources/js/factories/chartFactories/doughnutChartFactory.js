/**
 * 
 * @param $log
 * @returns {{}}
 */
angular.module("charts").factory("doughnutChartFactory", function($log) {
	$log.debug("Booted Doughtnut Chart Factory");

	var doughnutChart = {};

	doughnutChart.batchWeekQCPie = function(dataArray) {
		var chartData = {};
		// data and labels
		chartData.data = [];
		chartData.labels = [];
		chartData.options = {
			legend : {
				display : true
			}
		};

		// traverse through array of objects and grab labels and data
		angular.forEach(dataArray.data, function(value, key) {
			chartData.labels.push(key);
			chartData.data.push(value);
		});

		return chartData;
	};

	return doughnutChart;
});