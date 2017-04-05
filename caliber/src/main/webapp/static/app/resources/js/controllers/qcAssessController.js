angular.module("qc")
    .controller("qcAssessController", function ($log, $scope, chartsDelegate, caliberDelegate, qcFactory, allBatches, batchNotes, traineeNotes) {
        $log.debug("Booted Trainer Assess Controller");

        /******************************** Sample Data *******************************/
        $scope.batches = allBatches;
        $scope.bnote = [];
        $scope.tnote = [];

        /******************************************* UI ***********************************************/
		// get status types
        $scope.qcStatusTypes = [];
        caliberDelegate.all.enumQCStatus().then(function(types) {
        	$log.debug(types);
        	$scope.qcStatusTypes = types;
        });
        
		///////////////////////// overall feedback////////////////////////// 
        $scope.finalQCBatchNote = null;
        $scope.pickOverallStatus = function(batch, pick) {
			$scope.qcBatchAssess = pick;
			$log.debug(batch.trainingName + " " + pick);
		};
		
		///////////////////////// individual feedback/////////////////////		

		// used to store which rows have what faces/value
        $scope.faces = []; 
		$scope.pickIndividualStatus = function(trainee, status, index){
			$log.debug(trainee);
			$log.debug(status);
			// do your logic to update this trainee feedback
			
			// update face
			$scope.faces[index] = status;
		};
		
        ///////////////////////////////////////////////////////////////////////////////////////////// load note types
        caliberDelegate.all.enumNoteType().then(function(noteTypes) {
        	$log.debug(noteTypes);
        	// do something with note type
        });
        
        // starting scope vars
/************************************************TODO REFACTOR: WEEK IS NOT OBJECT ANYMORE***************************************/
/************************************************TODO REFACTOR: QC FEEDBACK IS NOTE.. NOT ASSESSMENT***************************************/
        $scope.currentBatch = $scope.batches[0];
        // Set bnote to current batch qc notes
        angular.forEach(batchNotes, function(note, key) {
        	if(note.batch.batchId === $scope.currentBatch.batchId) {
        		$scope.bnote.push(note);
        	}
    	});
        // Set tnote to current batch trainee qc notes
        angular.forEach(traineeNotes, function(note, key) {
        	angular.forEach($scope.currentBatch.trainees, function(trainee, key) {
            	if(trainee.traineeId === note.trainee.traineeId) {
            		$scope.tnote.push(note);
            	}
        	})
        });
        
        // default -- view assessments table
        $scope.currentView = true;

        // back button
        $scope.back = function () {
            $scope.currentView = true;
        };

        // batch drop down select
        /************************************************TODO REFACTOR: WEEK IS NOT OBJECT ANYMORE***************************************/
        $scope.selectCurrentBatch = function (index) {
            $scope.currentBatch = $scope.batches[index];
            $scope.bnote = [];
            $scope.tnote = [];
            // Set bnote to current batch qc note
            angular.forEach(batchNotes, function(note, key) {
            	if(note.batch.batchId === $scope.currentBatch.batchId) {
            		$scope.bnote.push(note);
            	}
        	});
            // Set tnote to current batch trainee qc note
            angular.forEach(traineeNotes, function(note, key) {
            	angular.forEach($scope.currentBatch.trainees, function(trainee, key) {
                	if(trainee.traineeId === note.trainee.traineeId) {
                		$scope.tnote.push(note);
                	}
            	})
        	});
            $log.debug("Batch QC Notes");
            $log.debug($scope.bnote);
            $log.debug("Batch Trainee Notes");
            $log.debug($scope.tnote);
            wipeFaces(); 
        };

        
        /////// wipe faces ;)  and selections ///////      
        function wipeFaces(){
        	$scope.faces = [];
            $scope.qcBatchAssess = null;
            $scope.finalQCBatchNote = null;
        }

    });
