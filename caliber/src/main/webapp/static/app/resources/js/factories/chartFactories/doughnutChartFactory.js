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
		chartData.colors = [];
		chartData.options = {
			legend : {
				display : true,
				labels : {
					boxWidth : 10
				}
			}
		};

		// traverse through array of objects and grab labels and data
		angular.forEach(dataArray, function(value, key) {
			// blue(Superstar) = #7972ff, green(Good) = #81f575, yellow(Average)
			// = #e8b00b, red(Poor) = #ff7575
			// hardcoding to get order right
			if (key === "Superstar")
				chartData.colors.push("#7972ff");
			else if (key === "Good")
				chartData.colors.push("#81f575");
			else if (key === "Average")
				chartData.colors.push("#e8b00b");
			else if (key === "Poor")
				chartData.colors.push("#ff7575");
			chartData.labels.push(key);
			chartData.data.push(value);
		});

		return chartData;
	};
	return doughnutChart;
});