angular.module("vp").controller(
		"vpHomeController",
		function($scope, $log, radarChartFactory, delegateFactory) {
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
			
			$scope.currentBatch = $scope.batches[0];
			
			$scope.currentTech = "Select";
				
			$scope.currentTrainee = "Select";
			
			$scope.selectCurrentBatch = function(index){
                $scope.currentTech = "Select";
                $scope.currentTrainee = "Select";
				$scope.currentBatch = $scope.batches[index];
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
			
			$scope.hideTraineeTab = function(){
				if($scope.currentTech == "Select")
					return false;
				return true;
			};

			function createCharts(){

                // batch rank comparison - radar chart
                $scope.batchSampleDataStandard = [{tech:"Java", average: ranNum()}, {tech: "Servlet", average: ranNum()}, {tech: "Spring", average: ranNum()},
                    {tech: "Hibernate", average: ranNum()}, {tech: "REST", average: ranNum()}, {tech: "SOAP", average: ranNum()},
                    {tech: "Javascript", average: ranNum()}, {tech: "Angular", average: ranNum()}];

                $scope.batchSampleDataBatch = [{tech:"Java", average: ranNum() }, {tech: "Servlet", average: ranNum()}, {tech: "Spring", average: ranNum()},
                    {tech: "Hibernate", average: ranNum()}, {tech: "REST", average: ranNum()}, {tech: "SOAP", average: ranNum()},
                    {tech: "Javascript", average: ranNum()}, {tech: "Angular", average: ranNum()}];

				if($scope.currentTech == "Select" && $scope.currentTrainee == "Select"){
                    var radarBatchCompareData = radarChartFactory.batchRankComparison($scope.batchSampleDataStandard, $scope.batchSampleDataBatch);
                    $scope.batchRankLabels = radarBatchCompareData.labels;
                    $scope.batchRankData = radarBatchCompareData.data;
                    $scope.batchRankSeries = radarBatchCompareData.series;

                    // create the other chart
				}else if($scope.currentTrainee == "Select"){
                    // create charts
                }else{
				    // create charts
                }

			}

            // trainer qc eval chart
            $scope.labels = ["Patrick", "Joe", "Brian", "Karan",
				"Steven", "Nick", "Richard", "Fred", "Genesis", "Emily", "Ankit", "Ryan"];
            $scope.series = ['QC Eval'];

            $scope.data = [
		      [70, 78, 80, 81, 85, 90, 70, 66, 89, 100, 78, 89]
            ];

		    function ranNum(){
		        var num = (Math.random() % 50) * 100;
		        return num.toFixed(2);
            }
		});