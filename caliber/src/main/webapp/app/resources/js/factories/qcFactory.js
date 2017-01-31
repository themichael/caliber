/**
 * API that makes qc related AJAX calls to the backend
 */
angular.module("api").factory("qcFactory", function ($log, $http) {
    $log.debug("Booted QC API Factory");

    var qc = {};

    /*************************** Batch ************************/
    qc.getAllBatches = function () {
        var data = [];
        $http({
            url: "/caliber/qc/batch/all",
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

    /*************************** Grade ************************/
    // add a new grade
    qc.addGrade = function (gradeObj) {
        $http({
            url: "/caliber/qc/grade/create",
            method: "POST",
            data: gradeObj
        }).then(function (response) {
            $log.debug(response);
            // update ui
        }, function (response) {
            $log.error("There was an error: " + response.status);
        });
    };

    // update grade
    qc.updateGrade = function (gradeObj) {
        $http({
            url: "/caliber/qc/grade/update",
            method: "PUT",
            data: gradeObj
        }).then(function (response) {
            $log.debug(response);
            // update ui
        }, function (response) {
            $log.error("There was an error: " + response.status);
        });
    };

    /************************* Assessment ***********************/
    // create assessment
    qc.createAssessment = function (assessmentObj) {
        $http({
            url: "/caliber/qc/assessment/create",
            method: "POST",
            data: assessmentObj
        }).then(function (response) {
            $log.debug(response);
            // update ui
        }, function (response) {
            $log.error("There was an error: " + response.status);
        });
    };

    // get all assessments
    qc.getAllAssessments = function (weekId) {
        var data = [];
        $http({
            url: "/caliber/qc/assessment/byWeek/" + weekId,
            method: "GET"
        }).then(function (response) {
            $log.debug(response);
            // copy data
            angular.copy(response.data, data);
        }, function (response) {
            $log.error("There was an error: " + response.status);
        });
        return data;
    };

    // update assessment
    qc.updateAssessment = function (assessmentObj) {
        $http({
            url: "/caliber/qc/assessment/update/",
            method: "PUT",
            data: assessmentObj
        }).then(function (response) {
            $log.debug(response);
            // update ui
        }, function (response) {
            $log.error("There was an error: " + response.status);
        });
    };

    // delete assessment
    qc.deleteAssessment = function (assessmentId) {
        $http({
            url: "/caliber/qc/assessment/delete/" + assessmentId,
            method: "DELETE"
        }).then(function (response) {
            $log.debug(response);
            // update ui
        }, function (response) {
            $log.error("There was an error: " + response.status);
        });
    };

    /************************** Notes *************************/
    // create note
    qc.createNote = function (noteObj) {
        $http({
            url: "/caliber/qc/assessment/note/create",
            method: "POST",
            data: noteObj
        }).then(function (response) {
            $log.debug(response);
            // update ui
        }, function (response) {
            $log.error("There was an error: " + response.status);
        });
    };

    // update note
    qc.updateNote = function (noteObj) {
        $http({
            url: "/caliber/qc/assessment/note/update",
            method: "PUT",
            data: noteObj
        }).then(function (response) {
            $log.debug(response);
            // update ui
        }, function (response) {
            $log.error("There was an error: " + response.status);
        });
    };

    return qc;
});