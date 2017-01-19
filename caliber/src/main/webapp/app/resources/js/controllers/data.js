// bubble chart
			$scope.bubbleOptions = {
				scales : {
					xAxes : [ {
						display : false,
						ticks : {
							max : 125,
							min : -125,
							stepSize : 10
						}
					} ],
					yAxes : [ {
						display : false,
						ticks : {
							max : 125,
							min : -125,
							stepSize : 10
						}
					} ]
				}
			};

			createChart();
			$interval(createChart, 2000);

			function createChart() {
				$scope.bubbleData = [];
				for (var i = 0; i < 50; i++) {
					$scope.bubbleData.push([ {
						x : randomScalingFactor(),
						y : randomScalingFactor(),
						r : randomRadius()
					} ]);
				}
			}

			function randomScalingFactor() {
				return (Math.random() > 0.5 ? 1.0 : -1.0)
						* Math.round(Math.random() * 100);
			}

			function randomRadius() {
				return Math.abs(randomScalingFactor()) / 4;
			}

			// horizontal bar chart
			$scope.hBarLabels = [ '2006', '2007', '2008', '2009', '2010',
					'2011', '2012' ];
			$scope.hBarSeries = [ 'Series A', 'Series B' ];

			$scope.hBarData = [ [ 65, 59, 80, 81, 56, 55, 40 ],
					[ 28, 48, 40, 19, 86, 27, 90 ] ];

			// polarChart
			$scope.polarLabels = [ "Download Sales", "In-Store Sales",
					"Mail-Order Sales", "Tele Sales", "Corporate Sales" ];
			$scope.polarData = [ 300, 500, 100, 40, 120 ];

			// pie chart
			$scope.pieLabels = [ "Download Sales", "In-Store Sales",
					"Mail-Order Sales" ];
			$scope.pieData = [ 300, 500, 100 ];

			
			// bar chart
			$scope.barLabels = [ '2006', '2007', '2008', '2009', '2010',
					'2011', '2012' ];
			$scope.barSeries = [ 'Supply', 'Demand' ];

			$scope.barData = [ [ 65, 59, 80, 81, 56, 55, 40 ],
					[ 28, 48, 40, 19, 86, 27, 90 ] ];

			// line chart
			$scope.lineLabels = [ "Java Core", "Servlet", "Spring",
					"Hibernate", "REST", "SOAP", "Angular" ];
			$scope.lineSeries = [ 'Batch-1245', 'Batch-1311', 'Batch-1567',
					'Batch-3411', 'Batch-6124', 'Batch-1413', 'Batch-1611' ];
			$scope.lineData = [ [ 80, 79, 80, 81, 56, 55, 40 ],
					[ 89, 89, 67, 19, 86, 56, 90 ],
					[ 76, 79, 98, 89, 70, 76, 50 ],
					[ 84, 60, 76, 81, 56, 55, 70 ],
					[ 70, 67, 80, 81, 56, 67, 80 ],
					[ 80, 54, 89, 81, 56, 87, 40 ],
					[ 100, 90, 88, 89, 94, 89, 90 ] ];

			$scope.lineDatasetOverride = [ {
				yAxisID : 'y-axis-1'
			}];
			$scope.lineOptions = {
				scales : {
					yAxes : [{
						id : 'y-axis-1',
						type : 'linear',
						display : true,
						position : 'left'
					}]
				}
			};