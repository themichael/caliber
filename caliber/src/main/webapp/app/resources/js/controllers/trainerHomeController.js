angular.module("trainer").controller(
		"trainerHomeController",
		function($scope, $log, delegateFactory, lineChartFactory, pieChartFactory) {
			$log.debug("Booted trainer home controller.");

			// Trainer API Test
			$log.log("Get All Batches: ");
			$log.log(delegateFactory.trainer.getAllBatches());
			
			$log.log("Get Current Batch: ");
			$log.log(delegateFactory.trainer.getCurrentBatch());
		
			$log.log("Get Batch with id");
			$log.log(delegateFactory.trainer.getBatch(6));

            /*********************************************** UI ***************************************************/
			var viewCharts = 0;

			$scope.batches = [ "Batch1311", "Batch1612", "Batch1512",
					"Batch1812", "Batch0910", "Batch0805", "Batch0408" ];
			$scope.tech = [ "Spring", "Hibernate", "JSP" ];
			$scope.trainees = [ "Osher", "Kyle", "Rikki" ];

			$scope.currentBatch = "Batch";

			$scope.currentTech = "Technology";

			$scope.currentTrainee = "Trainee";

			// on batch selection
            $scope.selectCurrentBatch = function(index){
                $scope.currentTech = "Tech";
                $scope.currentTrainee = "Trainee";
                // turn of batches
                if(index === -1){
                    viewCharts = 0;
                    $scope.currentBatch = "Batch";
                }
                else {
                    $scope.currentBatch = $scope.batches[index];
                    viewCharts = 1;
                    createBatchCharts();
                }
            };

            // on tech selection
            $scope.selectCurrentTech = function(index) {
                if (index === -1) {
                    $scope.currentTrainee = "Trainee";
                    $scope.currentTech = "Tech";
                    viewCharts = 0;
                }else{
                    $scope.currentTrainee = "Trainee";
                    $scope.currentTech = $scope.tech[index];
                    viewCharts = 2;
                    createTechCharts();
                }
            };

            // on trainee selection
            $scope.selectCurrentTrainee = function(index) {
                if (index === -1) {
                    $scope.currentTrainee = "Trainee";
                    viewCharts = 2;
                }
                else{
                    $scope.currentTech = "Tech";
                    $scope.currentTrainee = $scope.trainees[index];
                    viewCharts = 3;
                    createTraineeCharts();
                }
            };

            // hide filter tabs
            $scope.hideOtherTabs = function(){
                if($scope.currentBatch === "Batch")
                    return false;
                return true;
            };

            // show charts
            $scope.showCharts = function(charts){
                if(charts === viewCharts)
                    return true;
                return false;
            };

            // create charts on batch selection
			function createBatchCharts(){

			}

			// create charts on tech selection
			function createTechCharts(){

			}

			// create charts on trainee selection
			function createTraineeCharts(){

			}

			// Sample Data representing trainee average over 12 weeks
			var sampleData = [{week: "Week 1", average: 79}, {week: "Week 2", average: 89}, 
				{week: "Week 3", average: 67}, {week: "Week 4", average: 79}, 
				{week: "Week 5", average: 86}, {week: "Week 6", average: 76},
				{week: "Week 7", average: 79}, {week: "Week 8", average: 89}, 
				{week: "Week 9", average: 72}, {week: "Week 10", average: 94}, 
				{week: "Week 11", average: 86}, {week: "Week 12", average: 65}]
			
			// Sample Data representing trainee strengths per technology
			var samplePieData =[{skillCategory:"Core Java", average: 85},
			                    {skillCategory:"SQL", average: 75},
			                    {skillCategory:"Spring", average: 95},
			                    {skillCategory:"Hibernate", average: 75},
			                    {skillCategory:"AngularJS", average: 90},
			                    {skillCategory:"REST", average: 80}]
			
			// line chart function that retrieves
			// Week by week progression for a trainee/ batch on a line chart
			var lineChartObject = lineChartFactory.getTraineeProgressChart(sampleData);
			$scope.lineLabels = lineChartObject.lineLabels;
			$scope.lineSeries = lineChartObject.lineSeries;
			$scope.lineData = lineChartObject.lineData;
			$scope.lineDatasetOverride = lineChartObject.lineDatasetOverride;
			$scope.lineOptions = lineChartObject.lineOptions;

			// pie chart function that retrieves 
			// data for batch/ trainee technology strengths
			var pieChartObject = pieChartFactory.getTraineeProgressChart(samplePieData);
			$scope.pieLabels = pieChartObject.pieLabels;
			$scope.pieData = pieChartObject.pieData;
			$scope.pieOptions = pieChartObject.pieOptions;
			
			// Horizontal bar chart for trainee averages per technology
			$scope.hbarLabels = [ 'Kyle', 'Osher', 'Rikki', 'Dan', 'Pickles' ];
			$scope.hbarData = [ 65, 85, 100, 75, 50 ];

			$scope.hbarDatasetOverride = [{
				xAxisID: 'x-axis-1'
			}];

			$scope.hbarOptions = {
					scales: {
						xAxes: [{
							id: 'x-axis-1',
							position: 'bottom',
							ticks: {
								min: 30,
								max: 100
							}
						}]
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

