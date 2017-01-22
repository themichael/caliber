/**
 * Delegates API calls to the appropriate API factory
 */
angular.module("delegate").factory("delegateFactory", function($log, trainerFactory, vpFactory, qcFactory) {
	$log.info("Booted Delegate Factory");
	var delegate = {};
	
	delegate.testTrainerFactory = function(){
		return trainerFactory.test();
	};
	
	delegate.testVPFactory = function(){
		return vpFactory.test();
	};
	
	delegate.testQCFactory = function(){
		return qcFactory.test();
	};
	
	return delegate;
});