/**
 * 
 * @param $log
 * @returns {{}}
 */
angular.module("charts").factory("lineChartFactory", function($log) {
	$log.debug("Booted Line Chart Factory");

	var lineChart = {};

	lineChart.getBatchOverallLineChart = function(dataArray) {
		var chartData = {};

		// data and labels
		chartData.data = [];
		chartData.data.push([]);
		chartData.labels = [];
		chartData.options = {
				  scales: {
					    yAxes: [{
					      scaleLabel: {
					        display: true,
					        labelString: 'Average'
					      },
					      ticks : {
					    	  suggestedMin : 40,
					    	  max : 100,
					    	  stepSize : 20
					      }
					    }],
					    xAxes: [{
					    	scaleLabel: {
					    		display: true,
					    		labelString: 'Week'
					    	}
					    }]
					  }     
					}
		// traverse through array of objects and grab labels and data
		var go = true;
		angular.forEach(dataArray, function(value, key) {
			if(value === 0) go = false;
			if(go) {
				chartData.labels.push(key);
				chartData.data[0].push(value);
			}
		});
		return chartData;
	};
	
	//Yanilda
	lineChart.getTraineeUpToWeekLineChart = function(dataArray) {
		var chartData = {};

		// data and labels
		chartData.data = [];
		chartData.data.push([]);
		chartData.labels = [];
		chartData.series = ["Trainee", "Batch"];
		chartData.options = {
				  scales: {
					    yAxes: [{
					      scaleLabel: {
					        display: true,
					        labelString: 'Average'
					      },
					      ticks : {
					    	  suggestedMin : 40,
					    	  max : 100,
					    	  stepSize : 20
					      }
					    }],
					    xAxes: [{
					    	scaleLabel: {
					    		display: true,
					    		labelString: 'Week'
					    	}
					    }]
					  }     
					}
		// traverse through array of objects and grab labels and data
		angular.forEach(dataArray, function(value, key) {
			chartData.labels.push(key);
			chartData.data[0].push(value);
		});
		return chartData;
	};
	return lineChart;
});