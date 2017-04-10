angular
		.module("reportApi")
		.factory(
				"radarChartFactory",
				function($http, $log) {
					$log.debug("Booted Report Factory");

					var report = {};

					report.getTraineeUpToWeekRadarChart = function(week,
							traineeId) {
						return $http(
								{
									url : "/all/reports/week/" + week
											+ "/trainee/" + traineeId
											+ "/radar-trainee-up-to-week",
									method : "GET"
								})
								.then(
										function(response) {
											$log
													.debug("Week - Trainee Up To Week Radar Chart");
											$log.debug(response);
											return response.data;
										},
										function(response) {
											$log.error("There was an error: "
													+ response.status);
										}); // end then
					};

					report.getTraineeOverallRadarChart = function(traineeId) {
						return $http(
								{
									url : "/all/reports/trainee/" + traineeId
											+ "/radar-trainee-overall",
									method : "GET"
								}).then(
								function(response) {
									$log.debug("Trainee Overall Radar Chart");
									$log.debug(response);
									return response.data;
								},
								function(response) {
									$log.error("There was an error: "
											+ response.status);
								}); // end then
					};

					report.getBatchOverallRadarChart = function(batchId) {
						return $http(
								{
									url : "/all/reports/batch/" + batchId
											+ "/overall/radar-batch-overall",
									method : "GET"
								}).then(
								function(response) {
									$log.debug("Batch Overall Radar Chart");
									$log.debug(response);
									return response.data;
								},
								function(response) {
									$log.error("There was an error: "
											+ response.status);
								}); // end then
					};
					
				})
