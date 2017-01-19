angular.module("vp").controller(
		"vpHomeController",
		function($scope, $log) {
			$log.info("Booted vp home controller.");

			// radar chart
			$scope.radarLabels = [ "Eating", "Drinking", "Sleeping", "Designing",
					"Coding", "Cycling", "Running" ];

			$scope.radarData = [ [ 65, 59, 90, 81, 56, 55, 40 ],
					[ 28, 48, 40, 19, 96, 27, 100 ] ];

			// bar chart
			$scope.barLabels = [ '2006', '2007', '2008', '2009', '2010',
					'2011', '2012' ];
			$scope.barSeries = [ 'Series A', 'Series B' ];

			$scope.barData = [ [ 65, 59, 80, 81, 56, 55, 40 ],
					[ 28, 48, 40, 19, 86, 27, 90 ] ];

			// line chart
			$scope.lineLabels = [ "January", "February", "March", "April",
					"May", "June", "July" ];
			$scope.lineSeries = [ 'Series A', 'Series B' ];
			$scope.lineData = [ [ 65, 59, 80, 81, 56, 55, 40 ],
					[ 28, 48, 40, 19, 86, 27, 90 ] ];
			$scope.lineOnClick = function(points, evt) {
				console.log(points, evt);
			};
			$scope.lineDatasetOverride = [ {
				yAxisID : 'y-axis-1'
			}, {
				yAxisID : 'y-axis-2'
			} ];
			$scope.lineOptions = {
				scales : {
					yAxes : [ {
						id : 'y-axis-1',
						type : 'linear',
						display : true,
						position : 'left'
					}, {
						id : 'y-axis-2',
						type : 'linear',
						display : true,
						position : 'right'
					} ]
				}
			};
		});