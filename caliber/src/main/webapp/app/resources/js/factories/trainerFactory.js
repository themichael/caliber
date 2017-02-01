/**
 * API for making trainer related AJAX calls caliber endpoints
 */
angular.module("api").factory("trainerFactory", function ($log, $http) {
    $log.debug("Booted Trainer API");
    var trainer = {};

    /*************************** Batch ************************/
	// grab all batches
	trainer.getAllBatches = function() {
		return $http({
			url : "/caliber/trainer/batch/all",
			method : "GET",
		}).then(function(response) {
			$log.debug(response);
			return response.data;
		}, function(response) {
			$log.error("There was an error: " + response.status);
			return null;
		});
	};

    /*************************** Week ************************/
    // create a new week
    trainer.createWeek = function(weekObj) {
        return $http({
            url: "/caliber/trainer/week/new",
            method: "POST",
            data: weekObj
        }).then(function(response) {
            $log.debug(response);
            return true;
        }, function(response) {
            $log.error("There was an error: " + response.status);
            return false;
        });
    };

    /*************************** Grade ************************/
    // add a new grade
    trainer.addGrade = function(gradeObj) {
        return $http({
            url: "/caliber/trainer/grade/create",
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

    // update trainer grade
    trainer.updateGrade = function(gradeObj) {
        return $http({
            url: "/caliber/trainer/grade/update",
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
    trainer.createAssessment = function(assessmentObj) {
        return $http({
            url: "/caliber/trainer/assessment/create",
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
    trainer.getAllAssessments = function(weekId) {
        return $http({
            url: "/caliber/trainer/assessment/byWeek/" + weekId,
            method: "GET",
        }).then(function(response) {
            $log.debug(response);
            return response.data;
        }, function(response) {
            $log.error("There was an error: " + response.status);
            return null;
        });
    };

    // update assessment
    trainer.updateAssessment = function(assessmentObj) {
        return $http({
            url: "/caliber/trainer/assessment/update/",
            method: "PUT",
            data: assessmentObj
        }).then(function (response) {
            $log.debug(response);
            return true;
        }, function (response) {
            $log.error("There was an error: " + response.status);
            return false;
        });
    };

    // delete assessment
    trainer.deleteAssessment = function(assessmentId) {
        return $http({
            url: "/caliber/trainer/assessment/delete/" + assessmentId,
            method: "DELETE",
        }).then(function(response) {
            $log.debug(response);
            return true;
        }, function (response) {
            $log.error("There was an error: " + response.status);
            return false;
        });
    };

    /************************** Notes *************************/
    trainer.createNote = function(noteObj) {
        return $http({
            url: "/caliber/trainer/assessment/note/create",
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

    trainer.updateNote = function(noteObj) {
        return $http({
            url: "/caliber/trainer/assessment/note/update",
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

    return trainer;
});