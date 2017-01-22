angular.module("trainer").controller(
		"trainerHomeController",
		function($scope, $log) {
			$log.info("Booted trainer home controller.");

			$scope.batches = [ "Batch1311", "Batch1612", "Batch1512",
					"Batch1812", "Batch0910", "Batch0805", "Batch0408" ];
			$scope.tech = [ "Spring", "Hibernate", "JSP" ];
			$scope.trainees = [ "Osher", "Kyle", "Rikki" ];

			$scope.currentBatch = $scope.batches[0];

			$scope.currentTech = "Technology";

			$scope.currentTrainee = "Trainee";

			$scope.selectCurrentBatch = function(index) {
				$scope.currentBatch = $scope.batches[index];
			};

			$scope.selectCurrentTech = function(index) {
				$scope.currentTech = $scope.tech[index];
			};

			$scope.selectCurrentTrainee = function(index) {
				$scope.currentTrainee = $scope.trainees[index];
			};

			// Week by week progression for a trainee/ batch on a line chart
			$scope.lineLabels = [ "Week 1", "Week 2", "Week 3", "Week 4",
					"Week 5", "Week 6", "Week 7", "Week 8", "Week 9",
					"Week 10", "Week 11", "Week 12" ];
			$scope.lineSeries = [ 'Average', 'Fail Threshold' ];
			$scope.lineData = [ [ 79, 89, 67, 79, 86, 76, 90, 51 ],
					[ 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40 ] ];
			$scope.lineDatasetOverride = [ {
				yAxisID : 'y-axis-1',
				fill : false
			}, {
				yAxisID : 'y-axis-2',
				backgroundColor : 'rgba(255, 0, 0, .5)',
				borderColor : 'rgba(255, 0, 0, .5)',
				pointBorderColor : false,
				pointBackgroundColor : false,
				pointRadius : 0,
				pointHoverRadius : 0
			} ];
			$scope.lineOptions = {
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

			// Horizontal bar chart for trainee averages per technology
			$scope.hbarLabels = [ 'Kyle', 'Osher', 'Rikki', 'Dan', 'Pickles' ];
			$scope.hbarData = [ [65, 85, 100, 75, 50] ];

			$scope.hbarDatasetOverride = [ {
				xAxisID : 'x-axis-1'
			} ];

			$scope.hbarOptions = {
				scales : {
					xAxes : [ {
						id : 'x-axis-1',
						position : 'bottom',
						ticks : {
							min : 30,
							max : 100
						}
					} ]
				}
			}

			// Radar chart
			
			// Pie chart for trainee's technology strength
			$scope.pieLabels = [ "Spring", "Hibernate", "AngularJS",
					"Java Core", "SQL" ];
			$scope.pieData = [ 50, 80, 90, 85, 75 ];
			$scope.pieOptions = {
				legend : {
					display : true,
					position : 'left'
				}
			}

		});