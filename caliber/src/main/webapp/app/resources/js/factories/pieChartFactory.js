angular.module("charts").factory("pieChartFactory", function($log) {
	$log.debug("Booted Pie Chart Factory");
	
	var pieChart = {};

	pieChart.getTraineeTechProgressChart = function(dataArray) {
		var chartData = {};
		
		// data and labels
		chartData.data = [];
		chartData.labels = [];
				
		// traverse through array of objects and grab labels and data
		dataArray.forEach(function(element){
			chartData.data.push(element.average);
			chartData.labels.push(element.skillCategory);
		});
		
		// set pie options
		chartData.options = {
			legend : {
				display : true,
				position : 'left'
			}
		};

		return chartData;
	};

	return pieChart;
});