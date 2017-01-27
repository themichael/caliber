/**
 * API for making trainer related AJAX calls caliber endpoints
 */
angular.module("api").factory("trainerFactory", function($log, $http) {
	$log.debug("Booted Trainer API");
	var trainer = {};

    /*************************** Batch ************************/
	// grab all batches
	trainer.getAllBatches = function() {
		var data = [];
		$http({
			url : "/trainer/batch/all",
			method : "GET",
		}).then(function(response) {
			$log.debug(response);
			// copy array
			angular.copy(response.data, data);
		}, function(response) {
			data = null;
			$log.error("There was an error: " + response.status);
		});
		return data;
	};

	// grab current batch
	trainer.getCurrentBatch = function(){
		var data = {};
		$http({
			url: "/trainer/batch/current",
			method: "GET"
		}).then(function(response){
			$log.debug(response);
			// copy object
			angular.copy(response.data, data);
		}, function(response){
			data = null;
			$log.error("There was an error: " + response.status);
		});
		return data;
	};

	/*************************** Week ************************/
	// create a new week
	trainer.createWeek = function(weekObj){
		$http({
			url: "/trainer/week/new",
			method: "POST",
			data: weekObj
		}).then(function(response){
			$log.debug(response);
			// update ui
		}, function(response){
			$log.error("There was an error: " + response.status);
		});
	};

	/*************************** Grade ************************/
	// add a new grade
	trainer.addGrade = function(gradeObj){
        $http({
            url: "/trainer/grade/create",
            method: "POST",
            data: gradeObj
        }).then(function(response){
            $log.debug(response);
            // update ui
        }, function(response){
            $log.error("There was an error: " + response.status);
        });
	};

	// update trainer grade
	trainer.updateGrade = function(gradeObj){
        $http({
            url: "/trainer/grade/update",
            method: "PUT",
            data: gradeObj
        }).then(function(response){
            $log.debug(response);
            // update ui
        }, function(response){
            $log.error("There was an error: " + response.status);
        });
    };

	/************************* Assessment ***********************/
	// create assessment
    trainer.createAssessment = function(assessmentObj){
        $http({
            url: "/trainer/assessment/create",
            method: "POST",
            data: assessmentObj
        }).then(function(response){
            $log.debug(response);
            // update ui
        }, function(response){
            $log.error("There was an error: " + response.status);
        });
    };

    // get all assessments
    trainer.getAllAssessments = function(weekId){
        var data = [];
        $http({
            url: "/trainer/assessment/byWeek/" + weekId,
            method: "GET",
        }).then(function(response){
            $log.debug(response);
            // copy data
            angular.copy(response.data, data);
        }, function(response){
            $log.error("There was an error: " + response.status);
        });
        return data;
    };

	// update assessment
	trainer.updateAssessment = function(assessmentObj){
        $http({
            url: "/trainer/assessment/update/",
            method: "PUT",
			data: assessmentObj
        }).then(function(response){
            $log.debug(response);
            // update ui
        }, function(response){
            $log.error("There was an error: " + response.status);
        });
	};

    // delete assessment
    trainer.deleteAssessment = function(assessmentId){
        $http({
            url: "/trainer/assessment/delete/" + assessmentId,
            method: "DELETE",
        }).then(function(response){
            $log.debug(response);
            // update ui
        }, function(response){
            $log.error("There was an error: " + response.status);
        });
    };

    /************************** Notes *************************/
    trainer.createNote = function(noteObj){
        $http({
            url: "/trainer/assessment/note/create",
            method: "POST",
			data: noteObj
        }).then(function(response){
            $log.debug(response);
            // update ui
        }, function(response){
            $log.error("There was an error: " + response.status);
        });
	};

    trainer.updateNote = function(noteObj){
        $http({
            url: "/trainer/assessment/note/update",
            method: "PUT",
            data: noteObj
        }).then(function(response){
            $log.debug(response);
            // update ui
        }, function(response){
            $log.error("There was an error: " + response.status);
        });
	};

	return trainer;
});