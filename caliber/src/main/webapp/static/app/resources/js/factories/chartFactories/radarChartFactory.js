/**
 * 
 * @param $log
 * @returns {{}}
 */
angular.module("charts").factory("radarChartFactory", function($log) {
	$log.debug("Booted radarChartFactory");

	var radar = {};
	
	radar.getTraineeUpToWeekRadarChart = function(dataArray) {
		var chartData = {};
		
		chartData.series = [ "Trainee Up To Week" ];
		
		chartData.data = [];
		chartData.labels = [];
		chartData.data.push([]);

		angular.forEach(dataArray, function(value, key) {
			chartData.labels.push(key);
			chartData.data[0].push(value);
		});
		
		chartData.options = radarOptions;
		return chartData;
	};
	
	radar.getTraineeOverallRadarChart = function(dataArray) {
		var chartData = {};
		
		chartData.series = [ "Trainee Overall" ];
		
		chartData.data = [];
		chartData.labels = [];
		chartData.data.push([]);

		angular.forEach(dataArray, function(value, key) {
			chartData.labels.push(key);
			chartData.data[0].push(value);
		});
		
		chartData.options = radarOptions;
		return chartData;
	};
	
	radar.getBatchOverallRadarChart = function(dataArray) {
		var chartData = {};
		
		chartData.series = [ "Batch" ];
		
		chartData.data = [];
		chartData.labels = [];
		chartData.data.push([]);

		angular.forEach(dataArray, function(value, key) {
			chartData.labels.push(key);
			chartData.data[0].push(value);
		});
		
		chartData.options = radarOptions;
		return chartData;
	};
	
	
	

	radarOptions = {
		legend : {
			display : true,
			position : 'bottom'
		},
		scale : {
			reverse : false,
			ticks : {
				beginAtZero : false,
				fixedStepSize : 10,
				max : 100,
				suggestedMin : 40
			}

		}
	};

	return radar;
});