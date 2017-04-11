angular.module("reportApi").factory(
		"lineChartDataFactory",
		function($http, $log) {
			$log.debug("Booted Report Factory");

			var report = {};

			/**
			 * Yanilda
			 */
			report.getTraineeUpToWeekLineChart = function(week, traineeId) {
				return $http(
						{
							url : "/all/reports/batch/week/" + week
									+ "/trainee/" + traineeId
									+ "/line-trainee-up-to-week",
							method : "GET"
						}).then(function(response) {
					$log.debug("Agg - Batch - week-- trainee -- success");
					$log.debug(response);
					return response.data;
				}, function(response) {
					$log.error("There was an error: " + response.status);
				});
			};

			report.getTraineeOverallLineChart = function(batchId, traineeId) {

				return $http(

						{
							url : "/all/reports/batch/" + batchId
									+ "/overall/trainee/" + traineeId
									+ "/line-trainee-overall",
							method : "GET"

						}).then(

				function(response) {
					$log.debug("All-reports-Batch-overall-trainee-overall");
					$log.debug(response);
					return response.data;

				}, function(response) {

					$log.error("There was an error: " + response.status);

				});
			};
			return report;
		})
