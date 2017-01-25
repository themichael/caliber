angular.module("charts").factory("radarChartFactory", function($log){
	$log.debug("Booted Radar Chart Factory");
	
	var radarChart = {};
	
	radarChart.batchRankComparison = function(standard, batch){
		var chartData = {};

		// This relies on both data charts having the same labels
		
		// series 
		chartData.series = [ "Average", "Batch" ];

		// labels and data
		chartData.labels = [];
		chartData.data = [];
		
		// push empty arrays for data
		chartData.data.push([]);
		chartData.data.push([]);
		
		// loop through standard data - create labels only once
		standard.forEach(function(element){
			chartData.data[0].push(element.average);
			chartData.labels.push(element.tech);
		});
		
		// loop through batch data
		batch.forEach(function(element){
			chartData.data[1].push(element.average);
		});

        // set radar options
        chartData.options = {
            legend : {
                display : true,
                position : 'bottom'
            }
        };
		return chartData;
	};

	return radarChart;
});
