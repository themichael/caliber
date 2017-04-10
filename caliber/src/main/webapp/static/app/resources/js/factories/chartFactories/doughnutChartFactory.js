angular
		.module("reportApi")
		.factory(
				"doughnutChartFactory",
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
								.then(
										function(response) {
											$log
													.debug("Reports - BatchWeekPie -- success");
											$log.debug(response);
											return response.data;
										},
										function(response) {
											$log.error("There was an error: "
													+ response.status);
										});
					};
					
				})
