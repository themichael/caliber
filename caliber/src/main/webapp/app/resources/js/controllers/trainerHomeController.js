angular.module("trainer").controller(
		"trainerHomeController",
		function($scope, $log, $interval) {
			$log.info("Booted trainer home controller.");

			// Show week by week progression for a trainee on a line chart
			$scope.lineLabels = ["Week 1", "Week 2", "Week 3", "Week 4", "Week 5", 
			                       "Week 6", "Week 7", "Week 8", "Week 9", "Week 10", 
			                       "Week 11", "Week 12"];
			$scope.lineSeries = [ 'Average' ];
			$scope.lineData = [ [79, 89, 67, 79, 86, 76, 90, 51 ] ];
			$scope.lineOnClick = function(points, evt) {
				console.log(points, evt);
			};
			$scope.lineDatasetOverride = [ {
				yAxisID : 'y-axis-1',
				fill: false
			}];
			$scope.lineOptions = {
				scales : {
					yAxes : [{
						id : 'y-axis-1',
						type : 'linear',
						display : true,
						position : 'left',
						ticks: {
		                    min: 0,
		                    max: 100
		                }
					}]
				}
			};
		});