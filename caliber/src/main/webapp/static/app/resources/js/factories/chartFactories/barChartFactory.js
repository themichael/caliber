/**
 * 
 * @param $log
 * @returns {{}}
 */
angular.module("charts").factory("barChartFactory", function($log) {
	$log.debug("Booted Horizontal Bar Chart Factory");

	var barChart = {};

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
			chartData.data.push(value[0]);
		});

		chartData.datasetOverride = [ {
			xAxisID : 'x-axis-1'
		} ];

		return chartData;
	};

	barChart.getTraineeWeeklyAssessAvgs = function(dataArray) {
		var chartData = {};

		// series
		chartData.series = ["Trainee", "Batch"];

		// labels and data
		chartData.data = [];
		chartData.labels = [];
		var trainee = [];
		var batch = [];
		chartData.options = {
				legend : {
					display : true
				}}
		// loop through object array
		angular.forEach(dataArray, function(value, key) {
			trainee.push(value[0]);
			batch.push(value[1]);
			chartData.labels.push(key);
		});
		
		chartData.data.push(trainee);
		chartData.data.push(batch);
		
		return chartData;
	};

	barChart.getTraineeOverallAssessAvgs = function(dataArray) {
		var chartData = {};

		// series
		chartData.series = ["Trainee", "Batch"];
		chartData.options = {
				legend : {
					display : true
					}}
		// labels and data
		chartData.data = [];
		chartData.labels = [];
		var trainee = [];
		var batch = [];
		// loop through object array
		angular.forEach(dataArray, function(value, key) {
			trainee.push(value[0]);
			batch.push(value[1]);
			chartData.labels.push(key);
		});
		
		chartData.data.push(trainee);
		chartData.data.push(batch);

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
		
//		
//		chartData.options = {
//			legend : {
//				display : true
//			}
//		};

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