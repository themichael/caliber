angular.module("qc")
    .controller("qcAssessController", function ($log, $scope, chartsDelegate, caliberDelegate, qcFactory, allBatches) {
        $log.debug("Booted Trainer Assess Controller");

        $scope.batches = allBatches;
        $scope.bnote = [];
        $scope.tnote = [];

        /******************** Weeks **************************************/
        function week(weekNum, note) {
        	this.week = weekNum;
        	this.note = note;
        }
        
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
        caliberDelegate.qc.batchNote($scope.currentBatch.batchId).then(function(notes){
    		$scope.bnote = notes;
    	});
        caliberDelegate.qc.traineeNote($scope.currentBatch.batchId).then(function(notes){
    		$scope.tnote = notes;
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
        	$log.debug("SELECTED DIFFERENT BATCH");
        	$scope.currentBatch = $scope.batches[index];
            caliberDelegate.qc.batchNote($scope.currentBatch.batchId).then(function(notes){
        		$scope.bnote = notes;
        	});
            caliberDelegate.qc.traineeNote($scope.currentBatch.batchId).then(function(notes){
        		$scope.tnote = notes;
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
        
        /************************************************** GETTING NOTES ON TRAINEE **********************************************/
        $scope.noteOnTrainee = function (traineeName) {
        	
        	$log.debug($scope.tnote);
        	/*for (i=0; i<$scope.currentBatch.trainees.length; i++)
        		*/
        	
        	return traineeName;
        };
    });
