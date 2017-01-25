angular.module("vp").controller(
		"vpHomeController",
		function($scope, $log, radarChartFactory, hbarChartFactory, delegateFactory) {
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
			$scope.currentBatch = "Select";
			$scope.currentTech = "Select";
			$scope.currentTrainee = "Select";
			
			$scope.selectCurrentBatch = function(index){
                $scope.currentTech = "Select";
                $scope.currentTrainee = "Select";
                // turn of batches
                if(index == -1) $scope.currentBatch = "Select";
				else $scope.currentBatch = $scope.batches[index];
				createCharts();
			};
			
			$scope.selectCurrentTech = function(index) {
                if (index == -1) {
                	$scope.currentTrainee = "Select";
                	$scope.currentTech = "Select";
				}else{
					$scope.currentTech = $scope.tech[index];
					// select chart
				}
			};

			$scope.selectCurrentTrainee = function(index){
				if(index == -1)
					$scope.currentTrainee = "Select";
				else{
					$scope.currentTrainee = $scope.trainees[index];
					// select chart
				}
			};

			// hide trainee Tab
			$scope.hideTraineeTab = function(){
				if($scope.currentTech == "Select")
					return false;
				return true;
			};

			// hide tech tab
            $scope.hideTechTab = function(){
                if($scope.currentBatch == "Select")
                    return false;
                return true;
            };

			// hide default graphs
			$scope.hideDefault = function(){
			    if($scope.currentBatch == "Select")
			        return true;
                return false;
            }

			// create charts on dropdown selection
			function createCharts() {

                // batch rank comparison - radar chart
                var batchSampleDataStandard = [{tech: "Java", average: ranNum()},
                    {tech: "Servlet", average: ranNum()}, {tech: "Spring", average: ranNum()},
                    {tech: "Hibernate", average: ranNum()}, {tech: "REST", average: ranNum()},
                    {tech: "SOAP", average: ranNum()}, {tech: "Javascript", average: ranNum()},
                    {tech: "Angular", average: ranNum()}];

                var batchSampleDataBatch = [{tech: "Java", average: ranNum()},
                    {tech: "Servlet", average: ranNum()}, {tech: "Spring", average: ranNum()},
                    {tech: "Hibernate", average: ranNum()}, {tech: "REST", average: ranNum()},
                    {tech: "SOAP", average: ranNum()}, {tech: "Javascript", average: ranNum()},
                    {tech: "Angular", average: ranNum()}];

                if ($scope.currentTech == "Select" && $scope.currentTrainee == "Select") {
                    // create batch radar chart
                    var radarData = radarChartFactory.batchRankComparison(batchSampleDataStandard, batchSampleDataBatch);
                    $scope.batchRankLabels = radarData.labels;
                    $scope.batchRankData = radarData.data;
                    $scope.batchRankSeries = radarData.series;
                    $scope.batchRankOptions = radarData.options;

                    // create charts
                } else if ($scope.currentTrainee == "Select") {
                    // create charts
                } else {
                    // create charts
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