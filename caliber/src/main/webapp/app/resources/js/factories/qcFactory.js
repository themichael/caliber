/**
 * API that makes qc related AJAX calls to the backend
 */
angular.module("api").factory("qcFactory", function ($log, $http) {
    $log.debug("Booted QC API Factory");
    var qc = {};
    qc.getAllBatches = function () {
        var data = {};
        $http({
            url: "/qc/batch/all",
            method: "GET"
        }).then(function (response) {
            $log.debug(response);
            angular.copy(response.data, data);
        }, function (response) {
            data = null;
            $log.error("There was an error: " + response.status);
        });
        return data;
    };
    qc.getBatchById = function (id) {
        var data = {};
        $http({
            url: "/qc/batch/" + id,
            method: "GET"
        }).then(function (response) {
            $log.debug(response);
            angular.copy(response.data, data);
        }, function (response) {
            data = null;
            $log.error("There was an error: " + response.status);
        });
        return data;
    };

    // test connection to factory -- remove on release
    qc.test = function () {
        return "QC factory test successful.";
    };
    return qc;
});