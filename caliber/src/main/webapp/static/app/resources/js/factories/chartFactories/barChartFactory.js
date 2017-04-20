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
		chartData.series = [];
		chartData.data = [];
		chartData.labels = [];
		chartData.series = [];
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
		$log.debug("Yanilda");
		// traverse through array of objects and grab labels and data
		angular.forEach(dataArray, function(value, key) {
			if (value[0] > 0) {
				chartData.labels.push(key);
				chartData.series.push(key);
				chartData.data.push(value[0].toFixed(2));
			}
		});

		/*
		 * chartData.datasetOverride = [ { xAxisID : 'x-axis-1' } ];
		 */
		return chartData;
	};

	barChart.getTraineeWeeklyAssessAvgs = function(dataArray) {
		var chartData = {};

		// series
		chartData.series = [ "Trainee", "Batch" ];

		// labels and data
		chartData.data = [];
		chartData.labels = [];
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
				} ],
				xAxes : [ {
					scaleLabel : {
						display : true,
						labelString : 'Assessment'
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
	};

	barChart.getTraineeOverallAssessAvgs = function(dataArray) {
		var chartData = {};

		// series
		chartData.series = [ "Trainee", "Batch" ];
		// labels and data
		chartData.data = [];
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
				} ],
				xAxes : [ {
					scaleLabel : {
						display : true,
						labelString : 'Assessment'
					}
				} ]
			}
		};
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
	};

	barChart.getBatchOverallBarChart = function(dataArray) {
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
		// make all bar same color
		chartData.data.push([]);
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
		angular.forEach(sorted, function(obj) {
			chartData.labels.push(obj.name);
			chartData.data[0].push(obj.value.toFixed(2));
		});
		return chartData;
	};

	barChart.getBatchWeekSortedBarChart = function(dataArray) {
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
		chartData.data.push([]);
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
		};

		angular.forEach(sorted, function(obj) {
			chartData.labels.push(obj.name);
			chartData.data[0].push(obj.value.toFixed(2));
		});
		return chartData;
	};
	barChart.getDummyBarChart= function(dataArray){
			 // Return with commas in between
			  var numberWithCommas = function(x) {
			    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			  };
			  
				/*
				 * chartData.datasetOverride = [ { xAxisID : 'x-axis-1' } ];
				 */
			// Chart.defaults.global.elements.rectangle.backgroundColor = '#FF0000';
/*
			var bar_ctx = document.getElementById('bar-chart');*/
			var bar_chart = new Chart(bar_ctx, {
			    type: 'bar',
			    data: {
			        labels: dataArray.dates,
			        datasets: [
			        {
			            label: 'Poor',
			            data:  dataArray.dataPack1,
									backgroundColor: " #ff0000",
									hoverBackgroundColor: "rgba(55, 160, 225, 0.7)",
									hoverBorderWidth: 2,
									hoverBorderColor: 'lightgrey'
			        },
			        {
			            label: 'Good',
			            data:  dataArray.dataPack2,
									backgroundColor: " #e8b00b",
									hoverBackgroundColor: "rgba(225, 58, 55, 0.7)",
									hoverBorderWidth: 2,
									hoverBorderColor: 'lightgrey'
			        },
			         {
			            label: 'SuperStar',
			            data:  dataArray.dataPack3,
									backgroundColor: "#7972ff",
									hoverBackgroundColor: "rgba(55, 160, 225, 0.7)",
									hoverBorderWidth: 2,
									hoverBorderColor: 'lightgrey'
			        },
			        {
			            label: 'Average',
			            data:  dataArray.dataPack4,
									backgroundColor: "#24d810",
									hoverBackgroundColor: "rgba(225, 58, 55, 0.7)",
									hoverBorderWidth: 2,
									hoverBorderColor: 'lightgrey'
			        },
			        ]
			    },
			    options: {
			     		animation: {
			        	duration: 10,
			        },
			        tooltips: {
								mode: 'label',
			          callbacks: {
			          label: function(tooltipItem, data) { 
			          	return data.datasets[tooltipItem.datasetIndex].label + ": " + numberWithCommas(tooltipItem.yLabel);
			          }
			          }
			         },
			        scales: {
			          xAxes: [{ 
			          	stacked: true, 
			            gridLines: { display: false },
			            }],
			          yAxes: [{ 
			          	stacked: true, 
			            ticks: {
			        			callback: function(value) { return numberWithCommas(value); },
			     				}, 
			            }],
			        }, // scales
			        legend: {display: true}
			    } // options
			   }
			);
			return  bar_chart;
	}

	return barChart;
});