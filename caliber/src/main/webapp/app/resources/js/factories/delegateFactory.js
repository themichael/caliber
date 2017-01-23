/**
 * Delegates API calls to the appropriate API factory
 */
angular.module("delegate").factory("delegateFactory",
		function($log, trainerFactory, vpFactory, qcFactory) {
			$log.debug("Booted Delegate Factory");
			var delegate = {};
			
			delegate.trainer = {};
			delegate.qc = {};
			delegate.vp = {};
			
			// Tests
			delegate.testTrainerFactory = function() {
				return trainerFactory.test();
			};

			delegate.testVPFactory = function() {
				return vpFactory.test();
			};

			delegate.testQCFactory = function() {
				return qcFactory.test();
			};

			// Trainer API
			delegate.trainer.getAllBatches = function() {
				return trainerFactory.getAllBatches();
			};

			delegate.trainer.getCurrentBatch = function() {
				return trainerFactory.getCurrentBatch();
			};

			delegate.trainer.getBatch = function(id) {
				return trainerFactory.getBatch(id);
			};

			// VP API
			delegate.vp.getAllBatches = function() {
				return vpFactory.getAllBatches();
			};

			delegate.vp.getAllCurrentBatches = function() {
				return vpFactory.getAllCurrentBatches();
			};

			delegate.vp.getBatch = function(id){
				return vpFactory.getBatch(id);
			};
			
			delegate.vp.getCurrentBatch = function(id){
				return vpFactory.getCurrentBatch(id);
			};
			
			return delegate;
		});