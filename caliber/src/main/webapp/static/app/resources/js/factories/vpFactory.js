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
	//Get all Categories
	vp.getAllCategories = function(){
		return $http({
			url : "/vp/category",
			method : "GET"
		}).then(function(response){
			$log.debug("Categories successfully retrived");
			$log.debug(response);
			return response.data;
		},function(response){
			$log.error("There was an error in vpFactory -> getAllCategories" + response.status);
		});
	}
	//Update a category
	vp.updateCategory = function(category){
		return $http({
			url : "/vp/category/update",
			method : "PUT",
			data : category
		}).then(function(response){
			$log.debug(category + " Has been Updated");
			$log.debug(response);
		},function(response){
			$log.error("There was an error in vpFactory -> updateCategory " + response,status);
		});
	}
	// Save new category
	vp.saveCategory = function(category){
		return $http({
			url : "/vp/category",
			method : "POST",
			data : category
		}).then(function(response){
			$log.debug(category + " Has been Created");
			$log.debug(response);
		},function(response){
			$log.error("There was an error in vpFactory -> saveCategory " + response,status);
		});
	}
	return vp;
});
