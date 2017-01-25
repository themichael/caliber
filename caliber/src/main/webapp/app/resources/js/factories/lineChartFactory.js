angular.module("charts").factory("lineChartFactory", function ($log) {
	$log.debug("Booted Line Chart Factory.");

	var lineChart = {};
	
	lineChart.getTraineeProgressChart = function(dataArray) {
		var chartData = {};

		// series
		chartData.lineSeries = [ 'Average', 'Fail Threshold' ];

		// data and labels
		chartData.lineData = [];
		chartData.lineLabels = [];

		// push empty array for averages into parent array
		chartData.lineData.push([]);

		// push empty array for threshold into parent array
		chartData.lineData.push([]);

		// traverse through array of objects and grab labels and data
		dataArray.forEach(function(element){
			chartData.lineData[0].push(element.average);
			chartData.lineData[1].push(40);
			chartData.lineLabels.push(element.week);
		});

		// set data override
		chartData.lineDatasetOverride = [ {
			yAxisID : 'y-axis-1',
			fill : false
		}, {
			yAxisID : 'y-axis-2',
			backgroundColor : 'rgba(255, 0, 0, .5)',
			borderColor : 'rgba(255, 0, 0, .5)',
			pointRadius : 0,
			pointHoverRadius : 0
		} ];

		// set line options
		chartData.lineOptions = {
				legend : {
					display : true,
					position : 'bottom'
				},
				scales : {
					yAxes : [ {
						id : 'y-axis-1',
						type : 'linear',
						display : true,
						position : 'left',
						ticks : {
							min : 30,
							max : 100
						}
					}, {
						id : 'y-axis-2',
						type : 'linear',
						display : false,
						position : 'left',
						ticks : {
							min : 30,
							max : 100
						}
					} ]
				}
		};
		return chartData;
	};

    lineChart.getBatchProgressChart = function(dataArray) {
    	var chartData = {};

    	// series
		chartData.series = ["Average"];

		// labels and data
		chartData.labels = [];
		chartData.data = [];

		// push empty array to hold data
		chartData.data.push([]);

		// loop through array
		dataArray.forEach(function(element){
			chartData.data[0].push(element.average);
			chartData.labels.push(element.week);
		});

        // set data override
        chartData.datasetOverride = [ {
            yAxisID : 'y-axis-1',
            fill : false
        }];

        // set line options
        chartData.options = {
            legend : {
                display : true,
                position : 'bottom'
            },
            scales : {
                yAxes: [{
                    id: 'y-axis-1',
                    type: 'linear',
                    display: true,
                    position: 'left',
                    ticks: {
                        min: 30,
                        max: 100
                    }
                }]
            }
        };
    	return chartData;
    };

	return lineChart;
});