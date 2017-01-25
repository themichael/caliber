angular.module("vp").controller(
		"vpHomeController",
		function($scope, $log, radarChartFactory, hbarChartFactory, lineChartFactory, delegateFactory) {
			$log.debug("Booted vp home controller.");

			// VP API Test
			$log.log("Get All Batches: ");
			$log.log(delegateFactory.vp.getAllBatches());
			$log.log("Get All Current Batches: ");
			$log.log(delegateFactory.vp.getAllCurrentBatches());
			$log.log("Get Batch With Id: ");
			$log.log(delegateFactory.vp.getBatch(7));
			$log.log("Get Current Batch with Id: ");
			$log.log(delegateFactory.vp.getCurrentBatch(5));
			
			// UI - Dropdown menu selection
			$scope.batches = [ "Batch1311", "Batch1612", "Batch1512", "Batch1812", "Batch0910", "Batch0805", "Batch0408" ];
			$scope.tech = [ "Spring", "Hibernate", "JSP" ];
			$scope.trainees = [ "Osher", "Kyle", "Rikki" ];

			// dropdown defaults
			$scope.currentBatch = "Batch";
			$scope.currentTech = "Tech";
			$scope.currentTrainee = "Trainee";
			
			$scope.selectCurrentBatch = function(index){
                $scope.currentTech = "Tech";
                $scope.currentTrainee = "Trainee";
                // turn of batches
                if(index === -1) $scope.currentBatch = "Batch";
				else $scope.currentBatch = $scope.batches[index];
				createCharts();
			};
			
			$scope.selectCurrentTech = function(index) {
                if (index === -1) {
                	$scope.currentTrainee = "Trainee";
                	$scope.currentTech = "Tech";
				}else{
					$scope.currentTech = $scope.tech[index];
					// select chart
				}
			};

			$scope.selectCurrentTrainee = function(index){
				if(index === -1)
					$scope.currentTrainee = "Trainee";
				else{
					$scope.currentTrainee = $scope.trainees[index];
					// select chart
				}
			};

			// hide trainee Tab
			$scope.hideTraineeTab = function(){
				if($scope.currentTech === "Tech")
					return false;
				return true;
			};

			// hide tech tab
            $scope.hideTechTab = function(){
                if($scope.currentBatch === "Batch")
                    return false;
                return true;
            };

			// hide default graphs
			$scope.hideDefault = function(){
			    if($scope.currentBatch === "Batch")
			        return true;
                return false;
            }

			// create charts on dropdown selection
			function createCharts() {

                // batch rank comparison - radar chart
                var batchSampleDataStandard = [{tech: "Java", average: ranNum()},
                    {tech: "Servlet", average: ranNum()}, {tech: "Spring", average: ranNum()},
                    {tech: "Hibernate", average: ranNum()}, {tech: "REST", average: ranNum( )},
                    {tech: "SOAP", average: ranNum()}, {tech: "Javascript", average: ranNum()},
                    {tech: "Angular", average: ranNum()}];

                var batchSampleDataBatch = [{tech: "Java", average: ranNum()},
                    {tech: "Servlet", average: ranNum()}, {tech: "Spring", average: ranNum()},
                    {tech: "Hibernate", average: ranNum()}, {tech: "REST", average: ranNum()},
                    {tech: "SOAP", average: ranNum()}, {tech: "Javascript", average: ranNum()},
                    {tech: "Angular", average: ranNum()}];

                // batch week by week sample data
                var batchWeekSampleData = [{week: "Week 1", average: ranNum()}, {week: "Week 2", average: ranNum()},
                    {week: "Week 3", average: ranNum()}, {week: "Week 4", average: ranNum()},
                    {week: "Week 5", average: ranNum()}, {week: "Week 6", average: ranNum()},
                    {week: "Week 7", average: ranNum()}, {week: "Week 8", average: ranNum()},
                    {week: "Week 9", average: ranNum()}, {week: "Week 10", average: ranNum()},
                    {week: "Week 11", average: ranNum()}, {week: "Week 12", average: ranNum()}];

                if ($scope.currentTech === "Tech" && $scope.currentTrainee === "Trainee") {

                    // create batch radar chart
                    var radarData = radarChartFactory.getBatchRankComparisonChart(batchSampleDataStandard, batchSampleDataBatch);
                    $scope.batchRankLabels = radarData.labels;
                    $scope.batchRankData = radarData.data;
                    $scope.batchRankSeries = radarData.series;
                    $scope.batchRankOptions = radarData.options;

                    // create other charts
                    var lineData = lineChartFactory.getBatchProgressChart(batchWeekSampleData);
                    $scope.batchProgressLabels = lineData.labels;
                    $scope.batchProgressData = lineData.data;
                    $scope.batchProgressSeries = lineData.series;
                    $scope.batchProgressOptions = lineData.options;
                    $scope.batchProgressDatasetOverride = lineData.datasetOverride;
                }
            }


            /********* Default Charts *********/
            // trainer rank comparison - sample data
            var trainerEvalSampledata = [{name: "Patrick", score: ranNum()}, {name: "Joe", score: ranNum()},
                    {name: "Brian", score: ranNum()}, {name: "Ryan", score: ranNum()},
                    {name: "Karan", score: ranNum()}, {name: "Steven", score: ranNum()},
                    {name: "Nick", score: ranNum()}, {name: "Richard", score: ranNum()},
                    {name: "Fred", score: ranNum()}, {name: "Geneses", score: ranNum()},
                    {name: "Emily", score: ranNum()}, {name: "Ankit", score: ranNum()},
                    {name: "Ankit", score: ranNum()}];

            // create trainer hbar chart
            var hbarTrainerData = hbarChartFactory.getTrainerEvalChart(trainerEvalSampledata);
            $scope.trainerRankLabels = hbarTrainerData.labels;
            $scope.trainerRankData = hbarTrainerData.data;
            $scope.trainerRankSeries = hbarTrainerData.series;

            // batch rank comparison - sample data
            var batchEvalSampledata = [{name: "Batch1342", score: ranNum()}, {name: "Batch1526", score: ranNum()},
                {name: "Batch0354", score: ranNum()}, {name: "Batch1822", score: ranNum()},
                {name: "Batch9355", score: ranNum()}, {name: "Batch1232", score: ranNum()},
                {name: "Batch7241", score: ranNum()}, {name: "Batch1782", score: ranNum()},
                {name: "Batch7341", score: ranNum()}, {name: "Batch2312", score: ranNum()},
                {name: "Batch8453", score: ranNum()}, {name: "Batch6345", score: ranNum()},
                {name: "Batch1431", score: ranNum()}];

            // batch rank comparison - hbar chart
            var hbarBatchData = hbarChartFactory.getAllBatchesEvalChart(batchEvalSampledata);
            $scope.allBatchesRankLabels = hbarBatchData.labels;
            $scope.allBatchesRankData = hbarBatchData.data;
            $scope.allBatchesRankSeries = hbarBatchData.series;

            // random number gen - sample data only!
		    function ranNum(){
		        var num = (Math.random() % 50) * 100;
		        return num.toFixed(2);
            }
		});