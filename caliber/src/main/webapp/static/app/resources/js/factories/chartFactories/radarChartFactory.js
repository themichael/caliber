/**
 * 
 * @param $log
 * @returns {{}}
 */
angular.module("charts").factory("radarChartFactory", function($log) {
	$log.debug("Booted radarChartFactory");

	var radar = {};
	
	radar.getTraineeUpToWeekRadarChart = function(dataArray) {
		$log.debug("Data for getTraineeUpToWeekRadarChart");
		$log.debug(dataArray);
		var chartData = {};
		
		chartData.series = [ "Trainee Up To Week" ];
		
		chartData.labels = [];
		
		chartData.data = [];
		chartData.data.push([]);

		angular.forEach(dataArray, function(value, key) {
			chartData.labels.push(key);
			chartData.data[0].push(value.toFixed(2));
		});
		
		chartData.options = radarOptions;
		return chartData;
	};
	
	radar.getTraineeOverallRadarChart = function(dataArray) {
		$log.debug("Data for getTraineeOverallRadarChart");
		$log.debug(dataArray);
		var chartData = {};
		
		chartData.series = [ "Trainee Overall" ];
		
		chartData.labels = [];
		
		chartData.data = [];
		chartData.data.push([]);

		angular.forEach(dataArray, function(value, key) {
			chartData.labels.push(key);
			chartData.data[0].push(value.toFixed(2));
		});
		
		chartData.options = radarOptions;
		return chartData;
	};
	
	radar.getBatchOverallRadarChart = function(dataArray) {
		$log.debug("Data for getBatchOverallRadarChart");
		$log.debug(dataArray);
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
	};
	
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