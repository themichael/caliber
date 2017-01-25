angular.module("charts").factory("pieChartFactory", function($log) {
	$log.debug("Booted Pie Chart Factory");
	
	var pieChart = {};

	pieChart.getTraineeTechProgressChart = function(dataArray) {
		var chartData = {};
		
		// data and labels
		chartData.pieData = [];
		chartData.pieLabels = [];
				
		// traverse through array of objects and grab labels and data
		dataArray.forEach(function(element){
			chartData.pieData.push(element.average);
			chartData.pieLabels.push(element.skillCategory);
		});
		
		// set pie options
		chartData.pieOptions = {
			legend : {
				display : true,
				position : 'left'
			}
		};

		return chartData;
	};

	return pieChart;
});