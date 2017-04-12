/**
 * 
 * @param $log
 * @returns {{}}
 */
angular
		.module("charts")
		.factory(
				"lineChartFactory",
				function($log) {
					$log.debug("Booted Line Chart Factory");

					var lineChart = {};

					lineChart.getBatchOverallLineChart = function(dataArray) {
						var chartData = {};

						// data and labels
						chartData.data = [];
						chartData.labels = [];

						// traverse through array of objects and grab labels and
						// data
						angular.forEach(dataArray, function(value, key) {
							$log.debug(key + " " + value);
							chartData.labels.push(key);
							chartData.data.push(value);
						});

						chartData.datasetOverride = [ {
							xAxisID : 'x-axis-1'
						} ];

						return chartData;
					};

					// Yanilda
					lineChart.getTraineeUpToWeekLineChart = function(dataArray) {
						var chartData = {};

						// data and labels
						chartData.data = [];
						chartData.labels = [];

						// traverse through array of objects and grab labels and
						// data
						angular.forEach(dataArray, function(value, key) {
							chartData.labels.push(key);
							chartData.data.push(value);
						});

						/*
						 * chartData.datasetOverride = [ { xAxisID : 'x-axis-1' } ];
						 */

						return chartData;
					};

					lineChart.getTrainerEvalChart = function(dataArray) {
						var chartData = {};

						// series
						chartData.series = [ "QC Eval" ];

						// labels and data
						chartData.data = [];
						chartData.labels = [];

						// loop through object array
						dataArray.forEach(function(element) {
							chartData.data.push(element.score);
							chartData.labels.push(element.name);
						});

						return chartData;
					};

					lineChart.getAllBatchesEvalChart = function(data, batches) {
						var chartData = {};

						// series
						chartData.series = [ "All Batch Eval" ];

						// labels and data
						chartData.data = [];
						chartData.labels = [];

						// loop through object array
						angular.forEach(data, function(value, key) {
							$log.debug(value);
							chartData.data.push(value[0]);
							$log.debug(key);
							chartData.labels.push(key);
						});

						return chartData;
					};

					lineChart.getBatchTechEvalChart = function(dataArray) {
						var chartData = {};

						// series
						chartData.series = [ "Tech Batch Eval" ];

						// labels and data
						chartData.data = [];
						chartData.labels = [];

						// loop through object array
						dataArray.forEach(function(key, value) {
							chartData.data.push(value);
							chartData.labels.push(key);
						});

						return chartData;
					};
					lineChart.getTraineeOverallLineChart = function(dataArray) {
						var chartData = {};
						chartData.series = [ "batch","trainee" ]
						chartData.data = [];
						// chartData.labels = [];

						$log.debug("THIS IS THE DOUBLE[] DATA SET");
						$log.debug(dataArray);

						// loop through object array
						var trainee = [];
						var batch = [];
						var week = [];
						angular.forEach(dataArray, function(value, key) {
							trainee.push(value[0]);
							batch.push(value[1]);
							week.push(key);
						});
						chartData.data.push(trainee);
						chartData.data.push(batch);
						chartData.labels = week;
						return chartData;

					};
					return lineChart;
				});