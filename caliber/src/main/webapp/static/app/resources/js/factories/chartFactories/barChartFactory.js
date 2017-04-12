/**
 * 
 * @param $log
 * @returns {{}}
 */
angular.module("charts").factory("barChartFactory", function($log) {
	$log.debug("Booted Horizontal Bar Chart Factory");

	var barChart = {};

	barChart.getBatchAvgChart = function(dataArray) {
		var chartData = {};

		// data and labels
		chartData.data = [];
		chartData.labels = [];

		// traverse through array of objects and grab labels and data
		dataArray.forEach(function(element) {
			chartData.labels.push(element.trainee);
			chartData.data.push(element.average);
		});

		chartData.datasetOverride = [ {
			xAxisID : 'x-axis-1'
		} ];

		return chartData;
	};
	// yanilda
	barChart.getBatchWeekAvgBarChart = function(dataArray) {
		var chartData = {};

		// data and labels
		chartData.data = [];
		chartData.labels = [];
		
		console.log("Yanilda")
		// traverse through array of objects and grab labels and data
		angular.forEach(dataArray,function(value, key) {
			chartData.labels.push(key);
			$log.debug(key);
			chartData.data.push(value[0]);
			$log.debug(value);
		});
		return chartData;
	};



	barChart.getBatchOverallBarChart = function(dataArray) {
		var chartData = {};

		// series
		chartData.series = [ "Average Score" ];

		// labels and data
		chartData.data = [];
		chartData.labels = [];
		chartData.data.push([]);
		
		
		chartData.options = {
			legend : {
				display : true
			}
		};

		// loop through object array
		angular.forEach(dataArray, function(value,key) {
			chartData.labels.push(key);
			chartData.data[0].push(value);
		});

		return chartData;
	};
	
	barChart.getBatchWeekSortedBarChart = function(dataArray) {
		var chartData = {};
		//making a sorted array
		var sorted = [];
		//make the object to an array
	    angular.forEach(dataArray, function(value, key) {
	        sorted.push({'name' : key, 'value': value})
	    });
	    // sorted the array
	    sorted.sort(function (a, b) {
	      return b.value - a.value;
	    });
	     
		chartData.series = ['Average Score'];
		chartData.data = [];
		chartData.labels = [];
		
		angular.forEach(sorted, function(obj) {
			chartData.labels.push(obj.name);
			chartData.data.push(obj.value);
		});
		return chartData;
	};

	return barChart;
});