angular.module("api").factory("allFactory", function ($log, $http) {

    $log.debug("Booted all api factory");

    var all = {};

    /*************************** Enum constants ************************/
    all.enumAssessmentType = function() {
		return $http.get("types/assessment/all").success(function(data, status, headers, config) {
			return data;
		});
	};
    all.enumNoteType = function() {
		return $http.get("types/note/all").success(function(data, status, headers, config) {
			return data;
		});
	};
    all.enumQCStatus = function() {
		return $http.get("types/qcstatus/all").success(function(data, status, headers, config) {
			return data;
		});
	};
    all.enumSkillType = function() {
		return $http.get("types/skill/all").success(function(data, status, headers, config) {
			return data;
		});
	};
    all.enumTrainingStatus = function() {
		return $http.get("types/trainingstatus/all").success(function(data, status, headers, config) {
			return data;
		});
	};
    all.enumTrainingType = function() {
		return $http.get("types/training/all").success(function(data, status, headers, config) {
			return data;
		});
	};
    
    
    /*************************** Batch ************************/

    /**
     *
     * @param batchObj
     */
    all.createBatch = function(batchObj) {
        return $http({
            url: "/all/batch/create",
            method: "POST",
            data: batchObj
        }).then(function(response) {
            $log.debug("Object successfully created");
            $log.debug(response);
            // return id
            return response.data;
        }, function(response) {
            $log.error("There was an error: " + response.status);
        });
    };

    /**
     *
     * @param batchObj
     * @returns {*}
     */
    all.updateBatch = function(batchObj) {
        return $http({
            url: "/all/batch/update",
            method: "PUT",
            data: batchObj
        }).then(function(response) {
            $log.debug("Object successfully updated");
            $log.debug(response);
        }, function(response) {
            $log.error("There was an error: " + response.status);
        });
    };

    /**
     *
     * @param batchId
     * @returns {*}
     */
    all.deleteBatch = function(batchId) {
        return $http({
            url: "/all/batch/delete" + batchId,
            method: "DELETE"
        }).then(function(response) {
            $log.debug("Batch successfully deleted");
            $log.debug(response);
        }, function (response) {
            $log.error("There was an error: " + response.status);
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
            url: "/all/trainee/create",
            method: "POST",
            data: traineeObj
        }).then(function(response) {
            $log.debug("Trainee successfully created.")
            $log.debug(response);
            // return id
            return response.data;
        }, function(response) {
            $log.error("There was an error: " + response.status);
        });
    };

    /**
     *
     * @param traineeObj
     * @returns {*}
     */
    all.updateTrainee = function(traineeObj) {
        return $http({
            url: "/all/trainee/update",
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

    /**
     *
     * @param traineeId
     */
    all.deleteTrainee = function (traineeId) {
        $http({
            url: "/all/trainee/delete/" + traineeId,
            method: "DELETE"
        }).then(function (response) {
            $log.debug("Trainee successfully deleted.");
            $log.debug(response);
        }, function (response) {
            $log.error("There was an error: " + response.status);
        });
    };

    /************************** Grades **************************/

    /**
     * Gets all grades for a given Assessment
     * @param assessmentId
     * @returns {*}
     */
    all.getGrades = function(assessmentId) {
        return $http({
            url: "/all/grades/assessment/" + assessmentId,
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

    /**
     *
     * @returns {*}
     */
    all.getAllTrainers = function () {
        return $http({
            url: "/all/trainer/all/",
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
