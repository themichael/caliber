angular.module("trainer").controller(
		"trainerHomeController",
		function($scope, $log, delegateFactory, lineChartFactory, pieChartFactory, hbarChartFactory) {
			$log.debug("Booted trainer home controller.");

			// Trainer API Test
			$log.log("Get All Batches: ");
			$log.log(delegateFactory.trainer.getAllBatches());
			
			$log.log("Get Current Batch: ");
			$log.log(delegateFactory.trainer.getCurrentBatch());
		
			$log.log("Get Batch with id");
			$log.log(delegateFactory.trainer.getBatch(6));

			// UI
			$scope.batches = [ "Batch1311", "Batch1612", "Batch1512",
					"Batch1812", "Batch0910", "Batch0805", "Batch0408" ];
			$scope.tech = [ "Spring", "Hibernate", "JSP" ];
			$scope.trainees = [ "Osher", "Kyle", "Rikki" ];

			$scope.currentBatch = $scope.batches[0];

			$scope.currentTech = "Technology";

			$scope.currentTrainee = "Trainee";

			$scope.selectCurrentBatch = function (index) {
				$scope.currentBatch = $scope.batches[index];
			};

			$scope.selectCurrentTech = function (index) {
				$scope.currentTech = $scope.tech[index];
			};

			$scope.selectCurrentTrainee = function (index) {
				$scope.currentTrainee = $scope.trainees[index];
			};

			// Sample Data representing trainee average over 12 weeks
			var sampleLineData = [{week: "Week 1", average: 79}, {week: "Week 2", average: 89}, 
				{week: "Week 3", average: 67}, {week: "Week 4", average: 79}, 
				{week: "Week 5", average: 86}, {week: "Week 6", average: 76},
				{week: "Week 7", average: 79}, {week: "Week 8", average: 89}, 
				{week: "Week 9", average: 72}, {week: "Week 10", average: 94}, 
				{week: "Week 11", average: 86}, {week: "Week 12", average: 65}];
			
			// Sample Data representing trainee strengths per technology
			var samplePieData =[{skillCategory:"Core Java", average: 85},
			                    {skillCategory:"SQL", average: 75},
			                    {skillCategory:"Spring", average: 95},
			                    {skillCategory:"Hibernate", average: 75},
			                    {skillCategory:"AngularJS", average: 90},
			                    {skillCategory:"REST", average: 80}];
			
			// Sample Data representing all trainee averages per technology
			var sampleHbarData = [{trainee: "Rikki", average: 100},
                {trainee: "Kyle", average: 50},
                {trainee: "Osher", average: 40},
                {trainee: "Danny P", average: 80},
                {trainee: "Bryan", average: 75},
                {trainee: "Brayn", average: 95}];

            // line chart function that retrieves
			// Week by week progression for a trainee/ batch on a line chart
			var lineChartObject = lineChartFactory.getTraineeProgressChart(sampleLineData);
			$scope.lineLabels = lineChartObject.labels;
			$scope.lineSeries = lineChartObject.series;
			$scope.lineData = lineChartObject.data;
			$scope.lineDatasetOverride = lineChartObject.datasetOverride;
			$scope.lineOptions = lineChartObject.options;

			// pie chart function that retrieves 
			// data for batch/ trainee technology strengths
			var pieChartObject = pieChartFactory.getTraineeTechProgressChart(samplePieData);
			$scope.pieLabels = pieChartObject.labels;
			$scope.pieData = pieChartObject.data;
			$scope.pieOptions = pieChartObject.options;
			
			// Horizontal bar chart for trainee averages per technology
			var hbarChartObject = hbarChartFactory.getBatchAvgChart(sampleHbarData);
			$scope.hbarLabels = hbarChartObject.labels;
			$scope.hbarData = hbarChartObject.data;
			$scope.hbarDatasetOverride = hbarChartObject.datasetOverride;
			$scope.hbarOptions = hbarChartObject.options;

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

