/**
 * API for making vp related AJAX calls to the backend
 * @param $log
 * @param $http
 * @returns {{}}
 */
angular.module("api").factory("vpFactory", function ($log, $http) {
    $log.debug("Booted VP API Factory");
    var vp = {};

    // Get all batches
    vp.getAllBatches = function () {
        var data = [];
        $http({
            url: "/caliber/vp/batch/all",
            method: "GET"
        }).then(function (response) {
            $log.debug(response);
            // copy array
            angular.copy(response.data, data);
        }, function (response) {
            data = null;
            $log.error("There was an error: " + response.status);
        });
        return data;
    };

    // Get all current batches
    vp.getAllCurrentBatches = function () {
        var data = [];
        $http({
            url: "/caliber/vp/batch/current/all",
            method: "GET"
        }).then(function (response) {
            $log.debug(response);
            // copy array
            angular.copy(response.data, data);
        }, function (response) {
            data = null;
            $log.error("There was an error: " + response.status);
        });
        return data;
    };

    return vp;
});