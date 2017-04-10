angular
		.module("reportApi")
		.factory(
				"doughnutChartDataFactory",
				function($http, $log) {
					$log.debug("Booted Report Factory");

					var report = {};
					
					report.batchWeekQCPie = function(batchId, weekNum) {
						return $http(
								{
									url : "all/reports/batch/" + batchId
											+ "/week/" + weekNum + "/pie",
									method : "GET"
								})
			
					};
					
					return report;
				})
