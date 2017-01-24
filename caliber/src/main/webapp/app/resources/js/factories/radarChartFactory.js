angular.module("charts").factory("radarChartFactory", function($log){
	$log.debug("Booted Radar Chart Factory");
	
	var radarChart = {};
	
	radarChart.batchRankComparison = function(standard, batch){
		var radarData = {};

		// This relies on both data charts having the same labels
		
		// series 
		radarData.series = [ "Average", "Batch" ];

		// labels and data
		radarData.labels = [];
		radarData.data = [];
		
		// push empty arrays for data
		radarData.data.push([]);
		radarData.data.push([]);
		
		// loop through standard data - create labels only once
		standard.forEach(function(element){
			radarData.data[0].push(element.average);
			radarData.labels.push(element.tech);
		});
		
		// loop through batch data
		batch.forEach(function(element){
			radarData.data[1].push(element.average);
		});

		$log.log("Radar Data: ");
		$log.log(radarData.data);
		$log.log("Radar labels: ");
		$log.log(radarData.labels);

		return radarData;
	};
	
	return radarChart;
});