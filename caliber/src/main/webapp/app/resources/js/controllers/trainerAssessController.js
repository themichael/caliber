angular.module("trainer")
    .controller("trainerAssessController", function($log, $scope, chartsDelegate, caliberDelegate, allBatches){

        $log.debug("Booted Trainer Aesess Controller");

        /******************************************* UI ***********************************************/

        $log.debug("Batches " + allBatches);
        console.log(allBatches);

        (function start(allBatches){
            $scope.batches = allBatches;
           if(allBatches.length > 0){
               $scope.currentBatch = allBatches[0];
               if(allBatches[0].weeks.length > 0)
                   $scope.currentWeek = allBatches[0].weeks;
               else $scope.currentWeek = null;
           }else{
               $scope.currentBatch = null;
               $scope.currentWeek = null;
           }
           $log.debug("Starting Values: currentBatch and currentWeek");
           $log.debug($scope.currentBatch);
           $log.debug($scope.currentWeek);
        })(allBatches);

        // default -- view assessments table
        $scope.currentView = true;

        // back button
        $scope.back = function () {
            $scope.currentView = true;
        };

        // batch drop down select
        $scope.selectCurrentBatch = function(index){
            $scope.currentBatch = $scope.batches[index];

            // check weeks null
            // set week
            $scope.currentWeek = $scope.currentBatch.weeks[0];

            /** replace with ajax call to get assessments by weekId **/
            caliberDelegate.all.getAllAssessments($scope.currentWeek.weekId)
                .then(function(data){
                    $scope.currentAssessments = data;
                });
        };


        // select week
        $scope.selectWeek = function (index) {
            $scope.currentWeek = $scope.currentBatch.weeks[index];
            /** ajax call to get assessments by weekId **/
        };

        // active week
        $scope.showActiveWeek = function (index) {
            if ($scope.currentWeek === $scope.currentBatch.weeks[index])
                return "active";
        };

        //create week
        $scope.createWeek = function () {
            var weekNumber;
            if(!$scope.currentBatch.weeks)
                weekNumber  = 1;
            else weekNumber = $scope.currentBatch.weeks.length+1;
            console.log(weekNumber);
            var weekObj = {
                weekId:1,
                weekNumber: weekNumber,
                batch: $scope.currentBatch,
                topics:null
            };
            $scope.weekId = caliberDelegate.trainer.createWeek(weekObj);
        };

        // select assessment from list
        $scope.selectAssessment = function (index) {
            $scope.currentAssessment = $scope.currentAssessments[index];
            $scope.currentView = false;
            /** replace with ajax call to get grades by assessmentId **/
        };


    });
