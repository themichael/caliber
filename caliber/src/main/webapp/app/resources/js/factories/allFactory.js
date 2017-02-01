angular.module("api").factory("allFactory", function ($log, $http) {

    $log.debug("Booted all api factory");

    var all = {};

    /*************************** Batch ************************/

    /**
     *
     * @param batchObj
     */
    all.createBatch = function(batchObj) {
        return $http({
            url: "/caliber/all/batch/create",
            method: "POST",
            data: batchObj
        }).then(function(response) {
            $log.debug(response);
            return true;
        }, function(response) {
            $log.error("There was an error: " + response.status);
            return false;
        });
    };

    /**
     *
     * @param batchObj
     * @returns {*}
     */
    all.updateBatch = function(batchObj) {
        return $http({
            url: "/caliber/all/batch/update",
            method: "PUT",
            data: batchObj
        }).then(function(response) {
            $log.debug(response);
            return true;
        }, function(response) {
            $log.error("There was an error: " + response.status);
            return false;
        });
    };

    /**
     *
     * @param batchId
     * @returns {*}
     */
    all.deleteBatch = function(batchId) {
        return $http({
            url: "/caliber/all/batch/delete" + batchId,
            method: "DELETE"
        }).then(function(response) {
            $log.debug(response);
            return true;
        }, function (response) {
            $log.error("There was an error: " + response.status);
            return false;
        });
    };

    /*************************** Trainee ************************/

    /**
     *
     * @param traineeObj
     * @returns {*}
     */
    all.createTrainee = function(traineeObj) {
        return $http({
            url: "/caliber/all/trainee/create",
            method: "POST",
            data: traineeObj
        }).then(function(response) {
            $log.debug(response);
            return true;
        }, function(response) {
            $log.error("There was an error: " + response.status);
            return false;
        });
    };

    /**
     *
     * @param traineeObj
     * @returns {*}
     */
    all.updateTrainee = function(traineeObj) {
        return $http({
            url: "/caliber/all/trainee/update",
            method: "PUT",
            data: traineeObj
        }).then(function (response) {
            $log.debug(response);
            return true;
        }, function (response) {
            $log.error("There was an error: " + response.status);
            return false;
        });
    };

    /**
     *
     * @param traineeId
     */
    all.deleteTrainee = function (traineeId) {
        $http({
            url: "/caliber/all/trainee/delete/" + traineeId,
            method: "DELETE"
        }).then(function (response) {
            $log.debug(response);
            return true;
        }, function (response) {
            $log.error("There was an error: " + response.status);
            return false;
        });
    };

    /************************** Grades **************************/

    /**
     *
     * @param assessmentId
     * @returns {*}
     */
    all.getGrades = function(assessmentId) {
        return $http({
            url: "/caliber/all/grades/assessment/" + assessmentId,
            method: "GET"
        }).then(function(response) {
            $log.debug(response);
            return response.data;
        }, function(response) {
            $log.error("There was an error: " + response.status);
            return null;
        });
    };

    /************************* Trainer **********************/

    /**
     *
     * @returns {*}
     */
    all.getAllTrainers = function () {
        return $http({
            url: "/caliber/all/trainer/all/",
            method: "GET"
        }).then(function (response) {
            $log.debug(response);
            return response.data;
        }, function (response) {
            $log.error("There was an error: " + response.status);
            return null;
        });
    };
    return all;
});
