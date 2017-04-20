angular.module("vp").controller(
		"vpHomeController",
		function($scope, $log, caliberDelegate, chartsDelegate, allBatches) {
			$log.debug("Booted vp home controller.");
			(function start() {
				// Finishes any left over ajax animation from another
				// page
				NProgress.done();
				createDummyBarChart();
			})();

			// Yanilda dummy barchart
			function createDummyBarChart() {
				$log.debug("Started creating dummy data three");
				data=chartsDelegate.bar.data.getDummyBarData();
					$log.debug(data);
					NProgress.done();
					var barChartObject = chartsDelegate.bar
							.getDummyBarChartDelegate(data);
					$scope.barchartDLabels = barChartObject.data.labels;
					$log.debug("Hey 1");
					$log.debug($scope.barchartDLabels);
					$log.debug("Hey");
					$scope.barchartDData = barChartObject.data.datasets;
					$log.debug("Hey 2");
					$log.debug($scope.barchartDData);
					
				

			}

		});