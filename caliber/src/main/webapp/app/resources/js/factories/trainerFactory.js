/**
 * API for making trainer related AJAX calls to the backend
 */
angular.module("api").factory("trainerFactory", function($log){
	$log.info("Booted Trainer API");
	var trainer = {};
	
	// test connection to factory -- remove on release
	trainer.test = function(){
		return "Trainer factory test successful.";
	};
	
	return trainer;
});	