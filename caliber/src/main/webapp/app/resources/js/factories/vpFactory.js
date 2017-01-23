/**
 * API for making vp related AJAX calls to the backend
 */
angular.module("api").factory("vpFactory", function($log, $http) {
	$log.debug("Booted VP API Factory");
	var vp = {};

	// test connection to factory -- remove on release
	vp.test = function() {
		return "VP factory test successful.";
	};

	// Get all batches
	vp.getAllBatches = function() {
		var data = [];
		$http({
			url : "/vp/batch/all",
			method : "GET"
		}).then(function(response) {
			$log.debug(response);
			// copy array
			angular.copy(response.data, data);
		}, function(response) {
			data = null;
			$log.error("There was an error: " + response.status);
		});
		return data;
	};

	// Get all current batches
	vp.getCurrentBatches = function(){
		var data = [];
		$http({
			url: "/vp/batch/current/all",
			method: "GET"
		}).then(function(){
			$log.debug(response);
			// copy array
			angular.copy(response.data, data);
		}, function(){
			data = null;
			$log.error("There was an error: " + response.status);
		});
		return data;
	};
	
	// Get batch by id
	vp.getBatch = function(id){
		var data = {};
		$http({
			url: "/vp/batch/" + id,
			method: "GET"
		}).then(function(response){
			$log.debug(response);
			// copy object
			angular.copy(response.data, data);
		}, function(response){
			data = null;
			$log.error("There was an error: " + response.status);
		})
		return data;
	};
	
	// Get a current batch by id
	vp.getCurrentBatch = function(id){
		var data = {}
		$http({
			url: "/vp/current/batch/" + id,
			method: "GET"
		}).then(function(response){
			$log.debug(response);
			// copy object
			angular.copy(response.data, data);
		}, function(response){
			data = null;
			$log.error("There was an error: " + response.status);
		});
	};
	
	return vp;
});