angular.module("charts").factory("hbarChartFactory", function($log) {
	$log.debug("Booted Horizontal Bar Chart Factory");
	
	var hbarChart = {};

	hbarChart.getBatchAvgChart = function(dataArray) {
		var chartData = {};
		
		// data and labels
		chartData.hbarData = [];
		chartData.hbarLabels = [];
				
		// traverse through array of objects and grab labels and data
		dataArray.forEach(function(element){
			chartData.hbarLabels.push(element.trainee);
			chartData.hbarData.push(element.average);
		});
		
		chartData.hbarDatasetOverride = [{
			xAxisID: 'x-axis-1'
		}];

		chartData.hbarOptions = {
				scales: {
					xAxes: [{
						id: 'x-axis-1',
						position: 'bottom',
						ticks: {
							min: 30,
							max: 100
						}
					}]
				}
		}
		return chartData;
	};

	hbarChart.getTrainerEvalChart = function(dataArray){
		var chartData = {};

		// series
		chartData.series = ["QC Eval"];

        // labels and data
        chartData.data = [];
        chartData.labels = [];

        // loop through object array
        dataArray.forEach(function(element){
        	chartData.data.push(element.score);
        	chartData.labels.push(element.name);
		});

		return chartData;
	};

    hbarChart.getAllBatchesEvalChart = function(dataArray){
        var chartData = {};

        // series
        chartData.series = ["All Batch Eval"];

        // labels and data
        chartData.data = [];
        chartData.labels = [];

        // loop through object array
        dataArray.forEach(function(element){
            chartData.data.push(element.score);
            chartData.labels.push(element.name);
        });

        return chartData;
    };

	return hbarChart;
});