/**
 * API for making vp related AJAX calls to the backend
 * 
 * @param $log
 * @param $http
 * @returns {{}}
 */
angular.module("api").factory("vpFactory", function($log, $http) {
	$log.debug("Booted VP API Factory");
	var vp = {};

	// Get all batches
	vp.getAllBatches = function() {
		return $http({
			url : "/vp/batch/all/",
			method : "GET"
		}).then(function(response) {
			$log.debug("Batches successfully retrieved.");
			$log.debug(response);
			return response.data;
		}, function(response) {
			$log.error("There was an error: " + response.status);
		});
	};
	// Get all current batches
	vp.getAllCurrentBatches = function() {
		return $http({
			url : "/vp/batch/all/current",
			method : "GET"
		}).then(function(response) {
			$log.debug("Batches successfully retrieved");
			$log.debug(response);
		}, function(response) {
			$log.error("There was an error: " + response.status);
		});
	};
<<<<<<< HEAD

	//deactivate trainer needed to force content type to be JSON else 415
	vp.deactivateTrainer = function (trainerObj){
		return $http({
			url: "/vp/trainer/delete",
			method: "DELETE",
			data:trainerObj,
			headers: {
				"Content-Type" : "application/json" 
			}
		}).then(function(response){
			$log.debug("Trainer deleted");
			$log.debug(response);
		}, function(response){
			$log.error("There was an error: " + response.status);
		});
	};


	
	//Update selected trainer
	vp.updateTrainer = function(trainerObj) {
		return $http({
			url : "/vp/trainer/update",
			method : "PUT",
			data : trainerObj
		}).then(function(response) {
			$log.debug("Trainer successfully updated.");
			$log.debug(response);
		}, function(response) {
			$log.error("There was an error: " + response.status);
			return false;
		});
	};
	
	/**
	 * returns all titles
	 * 
	 * @returns {*}
	 */
	vp.getAllTrainersTitle = function() {
		return $http({
			url : "/vp/trainer/titles/",
			method : "GET"
		}).then(function(response) {
			$log.debug("Trainers Titles successfully retrieved");
			$log.debug(response);
			return response.data;
		}, function(response) {
			$log.error("There was an error: " + response.status);
		});
	};
	
	/**
	 * 
	 * @param trainerObj
	 * @returns {*}
	 */
	vp.createTrainer = function(trainerObj) {
		$log.debug(trainerObj);
		return $http({
			url : "/vp/trainer/create",
			method : "POST",
			data : trainerObj
		}).then(function(response) {
			$log.debug("Trainer successfully created.")
			$log.debug(response);
			return response;
		}, function(response) {
			$log.error("There was an error: " + response.status);
			return response.data;
		});
	};
	
	
	vp.getTrainerEmail = function(trainerEmail) {
		return $http({
			url : "/training/trainer/byemail/" + trainerEmail + "/",
			method : "GET",
		}).then(function(response) {
			$log.log(trainerEmail);
			$log.log(response);
			return response;
		}, function(response) {
			$log.log(trainerEmail);
			$log.error("There was an error: " + response.status);
			return response;
		});
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
=======
>>>>>>> ebc64b20e47976667ab4d1b3cf317f882e7f5e5e
	return vp;
});
