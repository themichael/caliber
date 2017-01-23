/**
 * API that makes qc related AJAX calls to the backend
 */
angular.module("api").factory("qcFactory", function($log){
	$log.debug("Booted QC API Factory");
	var qc = {};
	
	// test connection to factory -- remove on release
	qc.test = function(){
		return "QC factory test successful.";
	};
	
	return qc;
});