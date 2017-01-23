/**
 * API for making vp related AJAX calls to the backend
 */
angular.module("api").factory("vpFactory", function($log){
	$log.debug("Booted VP API Factory");
	var vp = {};
	
	// test connection to factory -- remove on release
	vp.test = function(){
		return "VP factory test successful.";
	};
	
	return vp;
});