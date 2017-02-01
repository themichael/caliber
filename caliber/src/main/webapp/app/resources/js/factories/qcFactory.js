/**
 * API that makes qc related AJAX calls to the backend
 */
angular.module("api").factory("qcFactory", function ($log, $http) {
    $log.debug("Booted QC API Factory");

    var qc = {};

    /*************************** Batch ************************/
    qc.getAllBatches = function() {
        return $http({
            url: "/caliber/qc/batch/all",
            method: "GET"
        }).then(function(response) {
            $log.debug(response);
            return response.data;
        }, function (response) {
            $log.error("There was an error: " + response.status);
            return null;
        });
    };

    /*************************** Grade ************************/
    // add a new grade
    qc.addGrade = function(gradeObj) {
        return $http({
            url: "/caliber/qc/grade/create",
            method: "POST",
            data: gradeObj
        }).then(function(response) {
            $log.debug(response);
            return true;
        }, function(response) {
            $log.error("There was an error: " + response.status);
            return false;
        });
    };

    // update grade
    qc.updateGrade = function(gradeObj) {
        return $http({
            url: "/caliber/qc/grade/update",
            method: "PUT",
            data: gradeObj
        }).then(function(response) {
            $log.debug(response);
            return true;
        }, function(response) {
            $log.error("There was an error: " + response.status);
            return false;
        });
    };

    /************************* Assessment ***********************/
    // create assessment
    qc.createAssessment = function (assessmentObj) {
        return $http({
            url: "/caliber/qc/assessment/create",
            method: "POST",
            data: assessmentObj
        }).then(function(response) {
            $log.debug(response);
            return true;
        }, function(response) {
            $log.error("There was an error: " + response.status);
            return false;
        });
    };

    // get all assessments
    qc.getAllAssessments = function(weekId) {
        return $http({
            url: "/caliber/qc/assessment/byWeek/" + weekId,
            method: "GET"
        }).then(function(response) {
            $log.debug(response);
            return response.data;
        }, function(response) {
            $log.error("There was an error: " + response.status);
            return null;
        });
    };

    // update assessment
    qc.updateAssessment = function(assessmentObj) {
        return $http({
            url: "/caliber/qc/assessment/update/",
            method: "PUT",
            data: assessmentObj
        }).then(function(response) {
            $log.debug(response);
            return true;
        }, function(response) {
            $log.error("There was an error: " + response.status);
            return false;
        });
    };

    // delete assessment
    qc.deleteAssessment = function(assessmentId) {
        return $http({
            url: "/caliber/qc/assessment/delete/" + assessmentId,
            method: "DELETE"
        }).then(function(response) {
            $log.debug(response);
            return true;
        }, function(response) {
            $log.error("There was an error: " + response.status);
            return false;
        });
    };

    /************************** Notes *************************/
    // create note
    qc.createNote = function(noteObj) {
        return $http({
            url: "/caliber/qc/assessment/note/create",
            method: "POST",
            data: noteObj
        }).then(function(response) {
            $log.debug(response);
            return true;
        }, function(response) {
            $log.error("There was an error: " + response.status);
            return false;
        });
    };

    // update note
    qc.updateNote = function(noteObj) {
        return $http({
            url: "/caliber/qc/assessment/note/update",
            method: "PUT",
            data: noteObj
        }).then(function(response) {
            $log.debug(response);
            return true;
        }, function(response) {
            $log.error("There was an error: " + response.status);
            return false;
        });
    };

    return qc;
});