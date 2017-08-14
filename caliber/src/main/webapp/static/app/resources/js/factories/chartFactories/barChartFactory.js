/**
 * Team !Uncharted
 * 
 * @author Pier Yos
 * @author Hossain Yahya
 * @author Yanilda Peralta
 * @author Igor Gluskin
 * @author Ateeb Khawaja
 * 
 */
angular.module("charts").factory(
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

			barChart.getBatchWeekAvgBarChart = function(dataArray) {
				var chartData = {};
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
								suggestedmax : 100,
								stepSize : 20
							}
						} ]
					}
				}

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
				chartData.series = [ "Trainee", "Batch" ];

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
								suggestedmax : 100,
								stepSize : 20
							}
						} ]
					}
				};

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

				chartData.series = [ "Trainee", "Batch" ];
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
								suggestedmax : 100,
								stepSize : 20
							}
						} ]
					}
				}

				var trainee = [];
				var batch = [];
				angular.forEach(dataArray, function(value, key) {
					trainee.push(value[0].toFixed(2));
					batch.push(value[1].toFixed(2));
					chartData.labels.push(key);
				});

				chartData.data.push(trainee);
				chartData.data.push(batch);

				return chartData;
			}

			barChart.getBatchOverallBarChart = function(dataArray, comparison) {
				var chartData = {};

				var sorted = [];
				angular.forEach(dataArray, function(value, key) {
					sorted.push({
						'name' : key,
						'value' : value
					})
				});

				sorted.sort(function(a, b) {
					return b.value - a.value;
				});

				chartData.series = [ "Trainee", "Average" ];
				chartData.data = [ [], [] ];
				chartData.labels = [];
				chartData.colors = [ secondaryColor, mainColor ];
				chartData.options = {
					legend : {
						display : true
					},
					scales : {
						yAxes : [ {
							scaleLabel : {
								display : true,
								labelString : 'Average'
							},
							ticks : {
								suggestedMin : 40,
								suggestedmax : 100,
								stepSize : 20
							}
						} ]
					}
				}

				angular.forEach(sorted, function(obj) {
					chartData.labels.push(obj.name);
					chartData.data[0].push(comparison.toFixed(2));
					chartData.data[1].push(obj.value.toFixed(2));
				});

				chartData.datasetOverride = [ {
					fill : false,
					label : "Benchmark",
					pointRadius : 0,
					pointHoverRadius : 0,
					borderWidth : 3,
					borderColor : "rgba(252,180,20,1)",
					pointBackgroundColor : "rgba(252,180,20,1)",
					pointHoverBackgroundColor : "rgba(252,180,20,1)",
					pointHoverBorderColor : "rgba(252,180,200, 0.5)",
					type : 'line'
				}, {
					label : "Batch Scores",
					type : 'bar'
				} ]
				return chartData;
			}

			barChart.getBatchWeekSortedBarChart = function(dataArray) {
				var chartData = {};
				var sorted = [];
				angular.forEach(dataArray, function(value, key) {
					sorted.push({
						'name' : key,
						'value' : value
					})
				});
				sorted.sort(function(a, b) {
					return b.value - a.value;
				});

				chartData.series = [ 'Average Score' ];
				chartData.data = [];
				chartData.data.push([]);
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
								suggestedmax : 100,
								stepSize : 20
							}
						} ]
					}
				}

				angular.forEach(sorted, function(obj) {
					chartData.labels.push(obj.name);
					chartData.data[0].push(obj.value.toFixed(2));
				});

				chartData.datasetOverride = [ {
					label : "Batch Scores",
					type : 'bar'
				} ]
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
								chartData.colors.push("#393fef");
							else if (key2 === "Good")
								chartData.colors.push("#18ad18");
							else if (key2 === "Average")
								chartData.colors.push("#f9e900");
							else if (key2 === "Poor")
								chartData.colors.push("#ea2825");
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
					onClick: function(evnt,array){
						var e = array[0];
						var barIndex = e._index;
						console.log(barIndex);
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
				return chartData;
			}

			barChart.getDummyBarChart = function(dataArray) {
				/*
				 * unused function declaration.. need clarification here (PW) 
			 	var numberWithCommas = function(x) {
					return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
				};*/

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
