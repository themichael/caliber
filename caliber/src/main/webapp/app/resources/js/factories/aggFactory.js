angular.module("api")
    .factory("aggFactory", function ($http, $log) {
        $log.debug("Booted Aggregate Factory");

        var agg = {};
        /**
         *
         * @param traineeId
         * @returns {*}
         */
        agg.techTrainee = function(traineeId) {
            return $http({
                url: "/caliber/all/agg/tech/trainee/" + traineeId,
                method: "GET"
            }).then(function(response) {
                $log.debug(response);
                return response.data;
            }, function(response) {
                $log.error("There was an error: " + response.status);
                return null;
            });
        };
        /**
         *
         * @param traineeId
         * @returns {*}
         */
        agg.weekTrainee = function(traineeId) {
            return $http({
                url: "/caliber/all/agg/week/trainee/" + traineeId,
                method: "GET"
            }).then(function(response) {
                $log.debug(response);
                return reponse.data;
            }, function(response) {
                $log.error("There was an error: " + response.status);
                return null;
            });
        };

        /**
         *
         * @param batchId
         * @returns {*}
         */
        agg.techBatch = function(batchId) {
            return $http({
                url: "/caliber/all/agg/tech/batch/" + batchId,
                method: "GET"
            }).then(function(response) {
                $log.debug(response);
                return response.data;
            }, function(response) {
                $log.error("There was an error: " + response.status);
                return null;
            });
        };

        /**
         *
         * @param batchId
         * @returns {*}
         */
        agg.weekBatch = function(batchId) {
            return $http({
                url: "/caliber/all/agg/week/batch/" + batchId,
                method: "GET"
            }).then(function(response) {
                $log.debug(response);
                return response.data;
            }, function(response) {
                $log.error("There was an error: " + response.status);
                return null;
            });
        };

        /**
         *
         * @returns {*}
         */
        agg.techAllBatch = function() {
            return $http({
                url: "/caliber/all/agg/tech/batch/all",
                method: "GET"
            }).then(function (response) {
                $log.debug(response);
                return response.data;
            }, function (response) {
                $log.error("There was an error: " + response.status);
                return null;
            });
        };

        /**
         *
         * @param trainerId
         * @returns {*}
         */
        agg.batchTrainer = function(trainerId) {
            return $http({
                url: "/caliber/vp/agg/batch/trainer/" + trainerId,
                method: "GET"
            }).then(function(response) {
                $log.debug(response);
                return response.data;
            }, function(response) {
                $log.error("There was an error: " + response.status);
                return null;
            });
        };

        return agg;
    });