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
            $log.debug("Object successfully created");
            $log.debug(response);
        }, function(response) {
            $log.error("There was an error: " + response.status);
        });
    };
    all.updateBatch = function(batchObj) {
        return $http({
            url: "/caliber/all/batch/update",
            method: "PUT",
            data: batchObj
        }).then(function(response) {
            $log.debug("Object successfully updated");
            $log.debug(response);
        }, function(response) {
            $log.error("There was an error: " + response.status);
        });
    };

    all.deleteBatch = function(batchId) {
        return $http({
            url: "/caliber/all/batch/delete" + batchId,
            method: "DELETE"
        }).then(function(response) {
            $log.debug("Batch successfully deleted");
            $log.debug(response);
        }, function (response) {
            $log.error("There was an error: " + response.status);
        });
    };

    /*************************** Trainee ************************/
    all.createTrainee = function(traineeObj) {
        return $http({
            url: "/caliber/all/trainee/create",
            method: "POST",
            data: traineeObj
        }).then(function(response) {
            $log.debug("Trainee successfully deleted.")
            $log.debug(response);
        }, function(response) {
            $log.error("There was an error: " + response.status);
        });
    };

    all.updateTrainee = function(traineeObj) {
        return $http({
            url: "/caliber/all/trainee/update",
            method: "PUT",
            data: traineeObj
        }).then(function (response) {
            $log.debug("Trainee successfully updated.");
            $log.debug(response);
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
            $log.debug("Trainee successfully deleted.");
            $log.debug(response);
        }, function (response) {
            $log.error("There was an error: " + response.status);
        });
    };

    /************************** Grades **************************/

    all.getGrades = function(assessmentId) {
        return $http({
            url: "/caliber/all/grades/assessment/" + assessmentId,
            method: "GET"
        }).then(function(response) {
            $log.debug("Grades successfully retrieved.");
            $log.debug(response);
            return response.data;
        }, function(response) {
            $log.error("There was an error: " + response.status);
        });
    };

    /************************* Trainer **********************/
    all.getAllTrainers = function () {
        return $http({
            url: "/caliber/all/trainer/all/",
            method: "GET"
        }).then(function (response) {
            $log.debug("Trainers successfully retrieved");
            $log.debug(response);
            return response.data;
        }, function (response) {
            $log.error("There was an error: " + response.status);
        });
    };
    return all;
});
