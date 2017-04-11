angular.module("reportApi").factory(
		"radarChartDataFactory",
		function($http, $log) {
			$log.debug("Booted radarChartDataFactory");

			var report = {};

			report.getTraineeUpToWeekRadarChartData = function(week, traineeId) {
				return $http(
						{
							url : "/all/reports/week/" + week + "/trainee/"
									+ traineeId + "/radar-trainee-up-to-week",
							method : "GET"
						}).then(function(response) {
					$log.debug("Week - Trainee Up To Week Radar Chart");
					$log.debug(response);
					return response.data;
				}, function(response) {
					$log.error("There was an error: " + response.status);
				}); // end then
			};

			report.getTraineeOverallRadarChartData = function(traineeId) {
				return $http(
						{
							url : "/all/reports/trainee/" + traineeId
									+ "/radar-trainee-overall",
							method : "GET"
						}).then(function(response) {
					$log.debug("Trainee Overall Radar Chart");
					$log.debug(response);
					return response.data;
				}, function(response) {
					$log.error("There was an error: " + response.status);
				}); // end then
			};

			report.getBatchOverallRadarChartData = function(batchId) {
				return $http(
						{
							url : "/all/reports/batch/" + batchId
									+ "/overall/radar-batch-overall",
							method : "GET"
						}).then(function(response) {
					$log.debug("Batch Overall Radar Chart");
					$log.debug(response);
					return response.data;
				}, function(response) {
					$log.error("There was an error: " + response.status);
				}); // end then
			};
			return report;

			
			return report;
			
		});
