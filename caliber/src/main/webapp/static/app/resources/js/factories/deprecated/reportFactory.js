angular
		.module("api")
		.factory(
				"reportFactory",
				function($http, $log) {
					$log.debug("Booted Report Factory");

					var report = {};

					/**
					 * Yanilda
					 */
					report.reportLineChart = function(week, traineeId) {
						return $http(
								{
									url : "/all/reports/batch/week/" + week
											+ "/trainee/" + traineeId,
									method : "GET"
								})
								.then(
										function(response) {
											$log
													.debug("Agg - Batch - week-- trainee -- success");
											$log.debug(response);
											return response.data;
										},
										function(response) {
											$log.error("There was an error: "
													+ response.status);
										});
					};
					/**
					 * Yanilda
					 */
					report.reportAssesmentChart = function(batchId, week) {
						return $http(
								{
									url : "/all/reports/batch/" + batchId
											+ "/week/" + week + "/barAssesment",
									method : "GET"
								})
								.then(
										function(response) {
											$log
													.debug("Agg - Batch - batchId, Week -- success");
											$log.debug(response);
											return response.data;
										},
										function(response) {
											$log.error("There was an error: "
													+ response.status);
										});
					};

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

					report.batchWeekTraineeAssessBar = function(batchId,
							weekNum, traineeId) {
						return $http(
								{
									url : "all/reports/batch/" + batchId
											+ "/week/" + weekNum + "/trainee/"
											+ traineeId
											+ "/bar-batch-week-trainee",
									method : "GET"
								})
								.then(
										function(response) {
											$log
													.debug("Reports - batchWeekTraineeAssessBar -- success");
											$log.debug(response);
											return response.data;
										},
										function(response) {
											$log.error("There was an error: "
													+ response.status);
										});
					};

					report.batchOverallTraineeAssessBar = function(batchId,
							traineeId) {
						return $http(
								{
									url : "all/reports/batch/" + batchId
											+ "/overall/trainee/" + traineeId
											+ "/bar-batch-overall-trainee",
									method : "GET"
								})
								.then(
										function(response) {
											$log
													.debug("Reports - batchWeekTraineeAssessBar -- success");
											$log.debug(response);
											return response.data;
										},
										function(response) {
											$log.error("There was an error: "
													+ response.status);
										});
					};

					report.getBarChartBatchWeekAvg = function(batchId, week) {
						return $http(
								{
									url : "/all/reports/batch/" + batchId
											+ "/week/" + week + "/bar",
									method : "GET"
								})
								.then(
										function(response) {
											$log
													.debug("Batch - Week - batch avg Bar Chart");
											$log.debug(response);
											return response.data;
										},
										function(response) {
											$log.error("There was an error: "
													+ response.status);
										}); // end then
					};

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

					return report;

				})
