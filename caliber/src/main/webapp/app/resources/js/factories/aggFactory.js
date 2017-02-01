angular.module("api")
    .factory("aggFactory", function ($http, $log) {
        $log.debug("Booted Aggregate Factory");

        var agg = {};

        agg.techTrainee = function (traineeId) {
            var data = {};
            $http({
                url: "/caliber/agg/tech/trainee/" + traineeId,
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

        agg.weekTrainee = function (traineeId) {
            var data = {};
            $http({
                url: "/caliber/agg/week/trainee/" + traineeId,
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

        agg.techBatch = function (batchId) {
            var data = {};
            $http({
                url: "/caliber/agg/tech/batch/" + batchId,
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

        agg.weekBatch = function (batchId) {
            var data = {};
            $http({
                url: "/caliber/agg/week/batch/" + batchId,
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

        agg.techAllBatch = function () {
            var data = {};
            $http({
                url: "/caliber/agg/tech/batch/all",
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

        agg.batchTrainer = function (trainerId) {
            var data = {};
            $http({
                url: "/caliber/agg/batch/trainer/" + trainerId,
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

        return agg;
    });