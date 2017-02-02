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
        $log.debug(allBatches);

        (function start(allBatches){
            $scope.batches = allBatches;
           if(allBatches.length > 0){
               $scope.currentBatch = allBatches[0];
               if(allBatches[0].weeks.length > 0){
                   allBatches[0].weeks.sort(weekComparator);
                   $scope.currentWeek = allBatches[0].weeks[0];
                   getAllAssessmentsForWeek();
               }

               else $scope.currentWeek = null;
           }else{
               $scope.currentBatch = null;
               $scope.currentWeek = null;
           }
           $log.debug("Starting Values: currentBatch and currentWeek");
           $log.debug($scope.currentBatch);
           $log.debug($scope.currentWeek);
        })(allBatches);

        $scope.grades = [];

        // default -- view assessments table
        $scope.currentView = true;

        // back button
        $scope.back = function () {
            $scope.currentView = true;
        };

        // batch drop down select
        $scope.selectCurrentBatch = function(index){
            $scope.currentBatch = $scope.batches[index];

            if($scope.currentBatch.weeks.length > 0){
                $scope.currentBatch.weeks.sort(weekComparator);
                $scope.currentWeek = $scope.currentBatch.weeks[0];
            }
            else $scope.currentWeek = null;

            /** replace with ajax call to get assessments by weekId **/
            getAllAssessmentsForWeek()
        };


        // select week
        $scope.selectWeek = function (index) {
            $scope.currentWeek = $scope.currentBatch.weeks[index];
            $log.debug($scope.currentWeek);
            /** ajax call to get assessments by weekId **/
            getAllAssessmentsForWeek();
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
            $log.debug(weekNumber);
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
                $log.debug($scope.currentBatch.weeks);
            });

        };

        // select assessment from list
        $scope.selectAssessment = function (index) {
            $scope.currentAssessment = $scope.currentAssessment[index];
            $scope.currentView = false;
            /** replace with ajax call to get grades by assessmentId **/
        };
        
        $scope.addAssessment = function () {
            var assessment = {
                assessmentId: 1,
                title: $scope.trainingName,
                batch: $scope.currentBatch.batchId,
                type: $scope.trainingType,
                categories:  $scope.selectedCategories,
                week: $scope.currentWeek.weekId,
                weeklyStatus: null,
                rawScore: $scope.rawScore
            };
            $log.debug(assessment);
            caliberDelegate.trainer.createAssessment(assessment).then(function (response) {
              $log.debug(response);
              if($scope.currentAssessments > 0)
                    $scope.currentAssessments.push(assessment);
              else $scope.currentAssessments = assessment;
                getAllAssessmentsForWeek();
                $("#createAssessmentModal").modal('toggle');

            });
        };
        $scope.selectedCategories = [];

        $scope.toggleSelection = function (category) {
            var index = $scope.selectedCategories.indexOf(category);
            if(index > -1) $scope.selectedCategories.splice(index,1);
            else $scope.selectedCategories.push(category);
        };

        /**
         * Updates Grade if exists,
         *  else create new Grade,
         *  then saves to $scope
         * @param gradeId
         * @param traineeId
         * @param assessment
         */
        $scope.updateGrade = function (gradeId, traineeId, assessment) {
            $log.debug("Starting updateGrade for "
                        + "traineeId: " + traineeId + ", "
                        + "assessment: " + assessment + ", "
                        + "and gradeId: " + gradeId);

            // constructs Grade object from the data in table
            var grade = {
                gradeId: gradeId,
                trainee: traineeId,
                assessment: assessment,
                dateReceived: new Date(),
                score: document.getElementById((traineeId+""+assessment.assessmentId)).value
            };

            // adds new Grade if not exists, else update,
            //    response contains the ID of the created/updated Grade
            caliberDelegate.trainer.addGrade(grade)
            .then(function (response) {
                $log.debug("Adding grade to $scope");
                /**
                 * checks if new Grade was created or updated
                 *  assigns newGradeId = created Grade id
                 *  else takes the id of previously fetched Grade ID from view
                 */
                var newGradeId;
                if(response != null)
                    newGradeId = response;
                else
                    newGradeId = gradeId;
                $scope.grade.push(
                    {
                        gradeId: newGradeId,
                        assessment: assessment,
                        trainee: traineeId,
                        dateReceived: new Date()
                    }
                );
                $log.debug(response);
            })
        }; // updateGrade

        function weekComparator(w1,w2) {
            return (w1.weekNumber>w2.weekNumber)? 1:
                (w2.weekNumber>w1.weekNumber)?-1 : 0;
        }

        function getAllAssessmentsForWeek(){
            caliberDelegate.trainer.getAllAssessments($scope.currentWeek.weekId)
                .then(function(data){
                    $scope.currentAssessments = data;
                    $log.debug($scope.currentAssessments);
                    $scope.currentAssessments.forEach(function (assessment) {
                        caliberDelegate.all.getGrades(assessment.assessmentId).then(function (response) {
                            $scope.grades.push(response.data);
                            $log.debug("====== GRADES ======");
                            $log.debug($scope.grades);
                        });
                    });

                });
        }

    });
