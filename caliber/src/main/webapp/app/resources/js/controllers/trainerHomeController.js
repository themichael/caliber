angular.module("trainer").controller(
		"trainerHomeController",
		function($scope, $log, lineChartFactory) {
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

			// Sample Data representing trainee average over 6 weeks
			var sampleData = [{week: "Week 1", average: 79}, {week: "Week 2", average: 89}, 
				{week: "Week 3", average: 67}, {week: "Week 4", average: 79}, 
				{week: "Week 5", average: 86}, {week: "Week 6", average: 76}]
			
			// line chart function that retrieves 
			// Week by week progression for a trainee/ batch on a line chart
			var chartObject = lineChartFactory.getTraineeProgressChart(sampleData);
			$scope.lineLabels = chartObject.lineLabels;
			$scope.lineSeries = chartObject.lineSeries;
			$scope.lineData = chartObject.lineData;
			$scope.lineDatasetOverride = chartObject.lineDatasetOverride;
			$scope.lineOptions = chartObject.lineOptions;

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