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
			
			// Dropdown menu selection
			$scope.batches = [ "Batch1311", "Batch1612", "Batch1512", "Batch1812", "Batch0910", "Batch0805", "Batch0408" ];
			$scope.tech = [ "Spring", "Hibernate", "JSP" ];
			$scope.trainees = [ "Osher", "Kyle", "Rikki" ];
			
			$scope.currentBatch = $scope.batches[0];
			
			$scope.currentTech = "Technology";
				
			$scope.currentTrainee = "Trainee";
			
			$scope.selectCurrentBatch = function(index){
				$scope.currentBatch = $scope.batches[index];
			};
			
			$scope.selectCurrentTech = function(index){
				$scope.currentTech = $scope.tech[index];
			};
			
			$scope.selectCurrentTrainee = function(index){
				$scope.currentTrainee = $scope.trainees[index];
			};

			// batch rank comparison - radar chart
			$scope.batchSampleDataStandard = [{tech:"Java", average: 89}, {tech: "Servlet", average: 75}, {tech: "Spring", average: 80}, 
											{tech: "Hibernate", average: 67}, {tech: "REST", average: 72}, {tech: "SOAP", average: 67},
											{tech: "Javascript", average: 78}, {tech: "Angular", average: 80}];
			
			$scope.batchSampleDataBatch = [{tech:"Java", average: 90}, {tech: "Servlet", average: 60}, {tech: "Spring", average: 70}, 
										{tech: "Hibernate", average: 83}, {tech: "REST", average: 76}, {tech: "SOAP", average: 80},
										{tech: "Javascript", average: 85}, {tech: "Angular", average: 90}];
			
			var radarBatchCompareData = radarChartFactory.batchRankComparison(batchSampleDataStandard, batchSampleDataBatch);
			$scope.batchRankLabels = radarBatchCompareData.labels; 
			$scope.batchRankData = radarBatchCompareData.data;
			$scope.batchRankSeries = radarBatchCompareData.series;
			
			// trainer qc eval chart
			$scope.labels = ["Patrick", "Joe", "Brian", "Karan",
				"Steven", "Nick", "Richard", "Fred", "Genesis", "Emily", "Ankit", "Ryan"];
		    $scope.series = ['QC Eval'];

		    $scope.data = [
		      [70, 78, 80, 81, 85, 90, 70, 66, 89, 100, 78, 89]
		    ];

		});