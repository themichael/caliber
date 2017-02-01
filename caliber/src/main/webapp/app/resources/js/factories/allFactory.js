angular.module("api").factory("allFactory", function ($log, $http) {

    $log.debug("Booted all api factory");

    var all = {};

    /*************************** Batch ************************/
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
    all.createTrainee = function(traineeObj) {
        return $http({
            url: "/caliber/all/trainee/create",
            method: "PUT",
            data: traineeObj
        }).then(function(response) {
            $log.debug(response);
            return true;
        }, function(response) {
            $log.error("There was an error: " + response.status);
            return false;
        });
    };

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
