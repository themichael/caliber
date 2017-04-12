/**
 * 
 * @param $log
 * @returns {{}}
 */
angular.module("charts").factory("radarChartFactory", function($log) {
	$log.debug("Booted radarChartFactory");

	var radar = {};
	
	radar.getTraineeUpToWeekRadarChart = function(dataArray, seriesName) {
		return createGenericRadarChartObject(dataArray, seriesName);
	};
	
	radar.getTraineeOverallRadarChart = function(dataArray, seriesName) {
		return createGenericRadarChartObject(dataArray, seriesName);
	};
	
	radar.getBatchOverallRadarChart = function(dataArray, seriesName) {
		return createGenericRadarChartObject(dataArray, seriesName);
	};
	
	
	var createGenericRadarChartObject = function(dataArray, seriesName){
		var chartData = {};
		
		chartData.series = [ "Batch" ];
		
		chartData.labels = [];
		
		chartData.data = [];
		chartData.data.push([]);

		angular.forEach(dataArray, function(value, key) {
			chartData.labels.push(key);
			chartData.data[0].push(value.toFixed(2));
		});
		
		chartData.options = radarOptions;
		return chartData;
	}
	
	
	radar.addDataToExistingRadar = function(currentChartData, otherDataArray){
		var newData = [];
		var totalTechs = currentChartData.labels.length;
		currentChartData.series.push("TRAINEE PLACEHOLDER");

		for(var i = 0; i < totalTechs; i++){
			if(otherDataArray.hasOwnProperty(currentChartData.labels[i])){
				newData.push((otherDataArray[currentChartData.labels[i]]).toFixed(2));
			}
		}

		currentChartData.data.push(newData);
		
		return currentChartData;
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