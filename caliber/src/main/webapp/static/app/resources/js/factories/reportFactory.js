angular.module("api").factory("reportFactory", function($http, $log) {
	$log.debug("Booted Report Factory");
	
	var report = {};
//	example
//	report.techTrainee = function(traineeId) {
//		return $http({
//			url : "/all/tech/trainee/" + traineeId,
//			method : "GET"
//		}).then(function(response) {
//			$log.debug("Agg - Tech - trainee -- success");
//			$log.debug(response);
//			return response.data;
//		}, function(response) {
//			$log.error("There was an error: " + response.status);
//		});
//
//	};
})