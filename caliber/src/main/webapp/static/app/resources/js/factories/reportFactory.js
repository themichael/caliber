angular.module("reportApi").factory("reportFactory", function($http, $log) {
	$log.debug("Booted Report Factory");
	
	var report = {};

	report.getBarChartBatchWeekAvg = function(batchId, week) {
		return $http({
			url : "/all/reports/batch/" + batchId + "/week/" + week + "/bar",
			method : "GET"
		}).then(function(response) {
			$log.debug("Batch - Week - batch avg Bar Chart");
			$log.debug(response);
			return response.data;
		}, function(response) {
			$log.error("There was an error: " + response.status);
		}); // end then
	}
	
	report.getBarChartBatchWeekAvg();
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
});