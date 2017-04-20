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
				chartsDelegate.bar.data.getDummyBarData(function(data) {
					NProgress.done();
					var barChartObject = chartsDelegate.bar
							.getDummyBarChart(data);
					$scope.barchartDLabels = barChartObject.data.labels;
					$scope.barchartDData = barChartObject.data.datasets;
					$scope.barchartDOptions = barChartObject.options;
					$scope.barchartDType = barChartObject.type;
				}, function() {
					NProgress.done();
				});

			}

		});