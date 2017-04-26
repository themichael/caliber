/**
 * 
 * @param $log
 * @returns {{}}
 */
angular
		.module("charts")
		.factory(
				"barChartFactory",
				function($log) {
					$log.debug("Booted Bar Chart Factory");

					var barChart = {};
					var mainColor = {
						backgroundColor : 'rgba(114, 164, 194, .5)',
						pointBackgroundColor : 'rgba(114, 164, 194, .5)',
						borderColor : 'rgba(114, 164, 194, 1)',
						pointHoverBackgroundColor : 'rgba(114, 164, 194, .3)',
						pointHoverBorderColor : 'rgba(114, 164, 194, .3)',
						pointBorderColor : '#fff'
					}

					var secondaryColor = {
						backgroundColor : 'rgba(252, 180, 20, .6)',
						pointBackgroundColor : 'rgba(252, 180, 20, .6)',
						borderColor : 'rgba(252, 180, 20, 1)',
						pointHoverBackgroundColor : 'rgba(252, 180, 20, .3)',
						pointHoverBorderColor : 'rgba(252, 180, 20, .3)',
						pointBorderColor : '#fff'
					}

					// yanilda
					barChart.getBatchWeekAvgBarChart = function(dataArray) {
						var chartData = {};

						// data and labels
						chartData.data = [];
						chartData.data.push([]);
						chartData.labels = [];
						chartData.series = [];
						chartData.colors = [ mainColor ];
						chartData.options = {
							scales : {
								yAxes : [ {
									scaleLabel : {
										display : true,
										labelString : 'Average'
									},
									ticks : {
										suggestedMin : 40,
										max : 100,
										stepSize : 20
									}
								} ]
							}
						}

						$log.debug("Yanilda");
						// traverse through array of objects and grab labels and
						// data

						angular.forEach(dataArray, function(value, key) {
							if (value[0] > 0) {
								chartData.labels.push(key);
								chartData.series.push(key);
								chartData.data[0].push(value[0].toFixed(2));
							}
						});
						return chartData;
					}

					barChart.getTraineeWeeklyAssessAvgs = function(dataArray) {
						var chartData = {};

						// series
						chartData.series = [ "Trainee", "Batch" ];

						// labels and data
						chartData.data = [];
						chartData.labels = [];
						chartData.colors = [ mainColor, secondaryColor ];
						var trainee = [];
						var batch = [];
						chartData.options = {
							scales : {
								yAxes : [ {
									scaleLabel : {
										display : true,
										labelString : 'Average'
									},
									ticks : {
										suggestedMin : 40,
										max : 100,
										stepSize : 20
									}
								} ]
							}
						};
						// loop through object array
						angular.forEach(dataArray, function(value, key) {
							trainee.push(value[0].toFixed(2));
							batch.push(value[1].toFixed(2));
							chartData.labels.push(key);
						});

						chartData.data.push(trainee);
						chartData.data.push(batch);

						return chartData;
					}

					barChart.getTraineeOverallAssessAvgs = function(dataArray) {
						var chartData = {};

						// series
						chartData.series = [ "Trainee", "Batch" ];
						// labels and data
						chartData.data = [];
						chartData.labels = [];
						chartData.colors = [ mainColor, secondaryColor ];

						chartData.options = {
							scales : {
								yAxes : [ {
									scaleLabel : {
										display : true,
										labelString : 'Average'
									},
									ticks : {
										suggestedMin : 40,
										max : 100,
										stepSize : 20
									}
								} ]
							}
						}

						var trainee = [];
						var batch = [];
						// loop through object array
						angular.forEach(dataArray, function(value, key) {
							trainee.push(value[0].toFixed(2));
							batch.push(value[1].toFixed(2));
							chartData.labels.push(key);
						});

						chartData.data.push(trainee);
						chartData.data.push(batch);

						return chartData;
					}

					/***********************************************************
					 * Batch Overall *
					 **********************************************************/
					barChart.getBatchOverallBarChart = function(dataArray,
							comparison, bad, good) {
						var chartData = {};

						var sorted = [];
						// make the object to an array
						angular.forEach(dataArray, function(value, key) {
							sorted.push({
								'name' : key,
								'value' : value
							})
						});
						// sorted the array
						sorted.sort(function(a, b) {
							return b.value - a.value;
						});

						// series
						chartData.series = [ "Trainee", "Average" ];

						// labels and data
						chartData.data = [];
						chartData.labels = [];
						chartData.colors = [ mainColor ];
						// make all bar same color
						chartData.data.push([], [], [], []);
						chartData.options = {
							scales : {
								yAxes : [ {
									scaleLabel : {
										display : true,
										labelString : 'Average'
									},
									ticks : {
										suggestedMin : 40,
										max : 100,
										stepSize : 20
									}
								} ]
							}
						}
						// loop through object array
						angular.forEach(sorted, function(obj) {
							chartData.labels.push(obj.name);
							chartData.data[0].push(obj.value.toFixed(2));
							chartData.data[1].push(comparison);
							chartData.data[2].push(good);
							chartData.data[3].push(bad);
						});

						chartData.datasetOverride = [ {
							label : "Batch Scores",
							type : 'bar'
						}, {
							fill : false,
							label : "Benchmark",
							pointRadius: 0,
							pointHoverRadius: 0,
							borderWidth : 5,
							borderColor : "rgba(252,180,20,1)",
							pointBackgroundColor : "rgba(252,180,20,1)",
							pointHoverBackgroundColor : "rgba(252,180,20,1)",
							pointHoverBorderColor : "rgba(252,180,200, 0.5)",
							type : 'line',
							xAxes: [{
							      ticks: {
							        beginAtZero: true
							      }
							    }]
						}, {
							fill : false,
							label : "Good Grade",
							pointRadius: 0,
							pointHoverRadius: 0,
							borderWidth : 5,
							borderColor : "rgba(129,245,117, 1)",
							pointBackgroundColor : "rgba(129,245,117, 1)",
							pointHoverBackgroundColor : "rgba(129,245,117, 1)",
							pointHoverBorderColor : "rgba(129,245,117,0.5)",
							type : 'line',
							xAxes: [{
							      ticks: {
							        beginAtZero: true
							      }
							    }]
						}, {
							fill : false,
							label : "Borderline Grade",
							pointRadius: 0,
							pointHoverRadius: 0,
							borderWidth : 5,
							borderColor : "rgba(255,117,117,1)",
							pointBackgroundColor : "rgba(255,117,117,1)",
							pointHoverBackgroundColor : "rgba(255,117,117,1)",
							pointHoverBorderColor : "rgba(255,117,117,0.5)",
							type : 'line',
							xAxes: [{
							      ticks: {
							        beginAtZero: true
							      }
							    }]
						}

						]
						return chartData;
					}
					/***********************************************************
					 * * Batch Week **
					 **********************************************************/

					barChart.getBatchWeekSortedBarChart = function(dataArray,
							comparison, bad, good) {
						var chartData = {};
						// making a sorted array
						var sorted = [];
						// make the object to an array
						angular.forEach(dataArray, function(value, key) {
							sorted.push({
								'name' : key,
								'value' : value
							})
						});
						// sorted the array
						sorted.sort(function(a, b) {
							return b.value - a.value;
						});

						chartData.series = [ 'Average Score' ];
						chartData.data = [];
						chartData.data.push([], [], [], []);
						chartData.colors = [ mainColor ];
						chartData.labels = [];
						chartData.options = {
							scales : {
								yAxes : [ {
									scaleLabel : {
										display : true,
										labelString : 'Average'
									},
									ticks : {
										suggestedMin : 40,
										max : 100,
										stepSize : 20
									}
								} ]
							}
						}

						angular.forEach(sorted, function(obj) {
							chartData.labels.push(obj.name);
							chartData.data[0].push(obj.value.toFixed(2));
							chartData.data[1].push(comparison);
							chartData.data[2].push(good);
							chartData.data[3].push(bad);
						});

						chartData.datasetOverride = [ {
							label : "Batch Scores",
							type : 'bar'
						}, {
							fill : false,
							label : "Benchmark",
							pointRadius: 0,
							pointHoverRadius: 0,
							borderWidth : 5,
							borderColor : "rgba(252,180,20,1)",
							pointBackgroundColor : "rgba(252,180,20,1)",
							pointHoverBackgroundColor : "rgba(252,180,20,1)",
							pointHoverBorderColor : "rgba(252,180,20,0.5)",
							type : 'line',
							yAxes: [{
							      ticks: {
							        beginAtZero: true
							      }
							    }]
						}, {
							fill : false,
							label : "Good Grade",
							pointRadius: 0,
							pointHoverRadius: 0,
							borderWidth : 5,
							borderColor : "rgba(129,245,117, 1)",
							pointBackgroundColor : "rgba(129,245,117, 1)",
							pointHoverBackgroundColor : "rgba(129,245,117, 1)",
							pointHoverBorderColor : "rgba(129,245,117,0.5)",
							type : 'line',
							yAxes: [{
							      ticks: {
							        beginAtZero: true
							      }
							    }]
						}, {
							fill : false,
							label : "Borderline Grade",
							pointRadius: 0,
							pointHoverRadius: 0,
							borderWidth : 5,
							borderColor : "rgba(255,117,117,1)",
							pointBackgroundColor : "rgba(255,117,117,1)",
							pointHoverBackgroundColor : "rgba(255,117,117,1)",
							pointHoverBorderColor : "rgba(255,117,117,0.5)",
							type : 'line',
							xyAxes: [{
							      ticks: {
							        beginAtZero: true
							      }
							    }]
						}

						]
						return chartData;
					}

					barChart.getAllBatchesCurrentWeekQCStats = function(data) {
						var chartData = {};
						chartData.series = [];
						chartData.data = [];
						chartData.labels = [];
						chartData.colors = [];

						angular.forEach(data, function(value, key) {
							chartData.labels.push(key);
							var i = 0;
							angular.forEach(value, function(value2, key2) {
								if (chartData.data[i] === undefined) {
									chartData.data.push([]);
									chartData.series.push(key2);
									if (key2 === "Superstar")
										chartData.colors.push("#7972ff");
									else if (key2 === "Good")
										chartData.colors.push("#81f575");
									else if (key2 === "Average")
										chartData.colors.push("#e8b00b");
									else if (key2 === "Poor")
										chartData.colors.push("#ff7575");
								}
								chartData.data[i].push(value2);
								i++;
							});

						});

						chartData.options = {
							legend : {
								display : true,
								labels : {
									boxWidth : 10
								}
							},
							scales : {
								yAxes : [ {
									stacked : true,
									ticks : {
										mirror : true
									}
								} ],
								xAxes : [ {
									stacked : true,
									ticks : {
										mirror : true
									}
								} ]
							}
						}

						$log
								.debug("TESTING STACKED INFORMATION!!!!!!!!!!!!!!!!!!!!!!!!!!!");
						$log.debug(chartData);

						return chartData;
					}

					barChart.getDummyBarChart = function(dataArray) {
						// Return with commas in between
						var numberWithCommas = function(x) {
							return x.toString().replace(
									/\B(?=(\d{3})+(?!\d))/g, ",");
						};

						var chartData = {
							type : 'bar',
							data : {
								labels : dataArray.batches,
								datasets : [ dataArray.poor, dataArray.good,
										dataArray.average, dataArray.superstar, ]
							},

						};
						$log.debug("Hello from the other side");
						return chartData;
					}
					$log.debug("Hello, is it me you are looking for?");
					return barChart;
				});
