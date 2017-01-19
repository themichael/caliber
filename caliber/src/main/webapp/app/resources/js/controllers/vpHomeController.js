angular.module("vp").controller(
		"vpHomeController",
		function($scope, $log, $interval) {
			$log.info("Booted vp home controller.");

			// batch rank comparison - radar chart
			$scope.batchRankLabels = [ "Java", "Servlet", "Spring",
					"Hibernate", "REST", "SOAP", "Javascript", "Angular" ];

			$scope.batchRankData = [ [ 65, 59, 90, 81, 56, 55, 40, 89 ],
					[ 28, 48, 40, 19, 96, 27, 100, 78 ] ];

			$scope.batchRankSeries = [ "Average", "Batch" ];

			// trainer qc eval chart
			$scope.labels = ["Patrick", "Joe", "Brian", "Karan",
				"Steven", "Nick", "Richard", "Fred", "Genesis", "Emily", "Ankit", "Ryan"];
		    $scope.series = ['QC Eval'];

		    $scope.data = [
		      [70, 78, 80, 81, 85, 90, 70, 66, 89, 100, 78, 89]
		    ];

		});