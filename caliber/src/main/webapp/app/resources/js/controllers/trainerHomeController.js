angular.module("trainer").controller(
		"trainerHomeController",
		function($scope, $log, lineChartFactory, pieChartFactory) {
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

			// Sample Data representing trainee average over 12 weeks
			var sampleData = [{week: "Week 1", average: 79}, {week: "Week 2", average: 89}, 
				{week: "Week 3", average: 67}, {week: "Week 4", average: 79}, 
				{week: "Week 5", average: 86}, {week: "Week 6", average: 76},
				{week: "Week 7", average: 79}, {week: "Week 8", average: 89}, 
				{week: "Week 9", average: 72}, {week: "Week 10", average: 94}, 
				{week: "Week 11", average: 86}, {week: "Week 12", average: 65}]
			
			var samplePieData =[{skillCategory:"Core Java", average: 85},
			                    {skillCategory:"SQL", average: 75},
			                    {skillCategory:"Spring", average: 95},
			                    {skillCategory:"Hibernate", average: 75},
			                    {skillCategory:"AngularJS", average: 90},
			                    {skillCategory:"REST", average: 80}]
			
			// line chart function that retrieves 
			// Week by week progression for a trainee/ batch on a line chart
			var chartObject = lineChartFactory.getTraineeProgressChart(sampleData);
			$scope.lineLabels = chartObject.lineLabels;
			$scope.lineSeries = chartObject.lineSeries;
			$scope.lineData = chartObject.lineData;
			$scope.lineDatasetOverride = chartObject.lineDatasetOverride;
			$scope.lineOptions = chartObject.lineOptions;

			// pie chart function that retrieves 
			// data for batch/ trainee technology strengths
			var chartObject = pieChartFactory.getTraineeProgressChart(samplePieData);
			$scope.pieLabels = chartObject.pieLabels;
			$scope.pieData = chartObject.pieData;
			$scope.pieOptions = chartObject.pieOptions;
			
			// Horizontal bar chart for trainee averages per technology
			$scope.hbarLabels = [ 'Kyle', 'Osher', 'Rikki', 'Dan', 'Pickles' ];
			$scope.hbarData = [ 65, 85, 100, 75, 50 ];

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

			// Radar chart for batch rank comparison
			$scope.radarLabels = [ "Java", "Servlet", "Spring", "Hibernate",
					"REST", "SOAP", "Javascript", "Angular" ];

			$scope.radarData = [ [ 65, 59, 90, 81, 56, 55, 40, 89 ],
					[ 28, 48, 40, 19, 96, 27, 100, 78 ] ];

			$scope.radarSeries = [ "Average", "All Batches" ];
			$scope.radarOptions = {
				legend : {
					display : true,
					position : 'bottom'
				}
			}

		});