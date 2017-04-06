angular.module("api").factory("reportFactory", function($http, $log) {
	$log.debug("Booted Report Factory");

	var report = {};

	report.batchWeekQCPie = function(batchId, weekNum) {
		return $http({
			url : "all/reports/batch/"+batchId+"/week/"+weekNum+"/pie",
			method : "GET"
		}).then(function(response) {
			$log.debug("Reports - BatchWeekPie -- success");
			$log.debug(response);
			return response.data;
		}, function(response) {
			$log.error("There was an error: " + response.status);
		});
	};
	
	report.batchWeekTraineeAssessBar = function(batchId, weekNum, traineeId) {
		return $http({
			url : "all/reports/batch/"+batchId+"/week/"+weekNum+"/trainee/"+traineeId+"/bar-batch-week-trainee",
			method : "GET"
		}).then(function(response) {
			$log.debug("Reports - batchWeekTraineeAssessBar -- success");
			$log.debug(response);
			return response.data;
		}, function(response) {
			$log.error("There was an error: " + response.status);
		});
	};
	
	report.batchOverallTraineeAssessBar = function(batchId, traineeId) {
		return $http({
			url : "all/reports/batch/"+batchId+"/overall/trainee/"+traineeId+"/bar-batch-overall-trainee",
			method : "GET"
		}).then(function(response) {
			$log.debug("Reports - batchWeekTraineeAssessBar -- success");
			$log.debug(response);
			return response.data;
		}, function(response) {
			$log.error("There was an error: " + response.status);
		});
	};

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
})