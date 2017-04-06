angular.module("api").factory(
		"reportFactory",
		function($http, $log) {
			$log.debug("Booted Report Factory");

			var report = {};
			// example
			// report.techTrainee = function(traineeId) {
			// return $http({
			// url : "/all/tech/trainee/" + traineeId,
			// method : "GET"
			// }).then(function(response) {
			// $log.debug("Agg - Tech - trainee -- success");
			// $log.debug(response);
			// return response.data;
			// }, function(response) {
			// $log.error("There was an error: " + response.status);
			// });
			//
			// };
			/**
			 * Yanilda
			 */
			report.reportLineChart = function(week, traineeId) {
				return $http(
						{
							url : "/all/reports/batch/week/" + week
									+ "/trainee/" + traineeId,
							method : "GET"
						}).then(function(response) {
					$log.debug("Agg - Batch - week-- trainee -- success");
					$log.debug(response);
					return response.data;
				}, function(response) {
					$log.error("There was an error: " + response.status);
				});
			};
			/**
			 * Yanilda
			 */
			report.reportAssesmentChart = function(batchId, week) {
				return $http(
						{
							url : "/all/reports/batch/" + batchId + "/week/"
									+ week + "/barAssesment",
							method : "GET"
						}).then(function(response) {
					$log.debug("Agg - Batch - batchId, Week -- success");
					$log.debug(response);
					return response.data;
				}, function(response) {
					$log.error("There was an error: " + response.status);
				});
			};

			return report;

		})