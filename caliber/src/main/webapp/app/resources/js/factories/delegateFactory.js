/**
 * Delegates API calls to the appropriate API factory
 */

angular.module("delegate").factory("delegateFactory", function ($log, trainerFactory, vpFactory, qcFactory) {
    $log.debug("Booted Delegate Factory");
    var delegate = {};
    delegate.trainer = {};
    delegate.qc = {};
    delegate.testTrainerFactory = function () {
        return trainerFactory.test();
    };

    delegate.testVPFactory = function () {
        return vpFactory.test();
    };

    delegate.testQCFactory = function () {
        return qcFactory.test();
    };

    // Trainer API
    delegate.trainer.getAllBatches = function () {
        return trainerFactory.getAllBatches();
    };
  
    delegate.trainer.getCurrentBatch = function () {
        return trainerFactory.getCurrentBatch();
    };

    delegate.trainer.getBatch = function (id) {
        return trainerFactory.getBatch(id);
    };
  
    // QC API
    delegate.qc.getAllBatches = function () {
        return qcFactory.getAllBatches();
    };

    delegate.qc.getBatchById = function (id) {
        return qcFactory.getBatchById(id);
    };
  
    return delegate;
});