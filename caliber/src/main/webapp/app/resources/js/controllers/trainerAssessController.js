angular.module("trainer")
    .controller("trainerAssessController", function($log, $scope, chartsDelegate, caliberDelegate, allBatches){

        $log.debug("Booted Trainer Aesess Controller");

        /******************************TEST DATA***********************/
        $scope.skill_categories = [
            {skillCategory:"C# .NET"},
            {skillCategory:"NodeJS"},
            {skillCategory:"Java2EE"},
            {skillCategory:"PHP Laravel"}
        ];




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
            caliberDelegate.trainer.createWeek(weekObj).then(function (response) {
                $scope.currentBatch.weeks.push({
                    weekId:response,
                    weekNumber: weekNumber,
                    batch: null,
                    topics:null
                });
                console.log($scope.currentBatch.weeks);
            });

        };

        // select assessment from list
        $scope.selectAssessment = function (index) {
            $scope.currentAssessment = $scope.currentAssessments[index];
            $scope.currentView = false;
            /** replace with ajax call to get grades by assessmentId **/
        };
        
        $scope.addAssessment = function () {
            var assessment = {
                assessmentId: 1,
                title: $scope.trainingName,
                batch: $scope.currentBatch,
                type: $scope.trainingType,
                categories:  $scope.selectedCategories,
                weeklyStatus: null,
                rawScore: $scope.rawScore
            };
            console.log(assessment);
        };
        $scope.selectedCategories = [];
        $scope.toggleSelection = function (category) {
            var index = $scope.selectedCategories.indexOf(category);
            if(index > -1) $scope.selectedCategories.splice(index,1);
            else $scope.selectedCategories.push(category);
        }

    });
