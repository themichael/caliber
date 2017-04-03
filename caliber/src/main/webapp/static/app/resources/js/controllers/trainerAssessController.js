/**
 * Refactor to use week index instead of Week object
 */
angular.module("trainer")
    .controller("trainerAssessController", function($log, $scope, chartsDelegate, caliberDelegate, allBatches){

        $log.debug("Booted Trainer Aesess Controller");

        /******************************TEST DATA***********************/
        $scope.skill_categories = [
            {categoryId: 1, skillCategory:"Java"},
            {categoryId: 2, skillCategory:"SQL"},
            {categoryId: 3, skillCategory:"Servlet"},
            {categoryId: 4, skillCategory:"JSP"},
            {categoryId: 5, skillCategory:"XML"},
            {categoryId: 6, skillCategory:"Git"},
            {categoryId: 7, skillCategory:"JUnit"},
            {categoryId: 8, skillCategory:"Maven"},
            {categoryId: 9, skillCategory:"JDBC"},
            {categoryId: 10, skillCategory:"HTML"},
            {categoryId: 11, skillCategory:"CSS"},
            {categoryId: 12, skillCategory:"Javascript"},
            {categoryId: 13, skillCategory:"JQuery"},
            {categoryId: 14, skillCategory:"AJAX"},
            {categoryId: 15, skillCategory:"UNIX"},
            {categoryId: 16, skillCategory:"AWS"},
            {categoryId: 17, skillCategory:"Jenkins"},
            {categoryId: 18, skillCategory:"Hibernate"},
            {categoryId: 19, skillCategory:"Spring"},
            {categoryId: 20, skillCategory:"SOAP"},
            {categoryId: 21, skillCategory:"REST"},
            {categoryId: 22, skillCategory:"AngularJS"},
            {categoryId: 23, skillCategory:"Selenium"},
            {categoryId: 24, skillCategory:"Cucumber"},
            {categoryId: 25, skillCategory:"UFT"},
            {categoryId: 26, skillCategory:"Python"},
            {categoryId: 27, skillCategory:"Robot Framework"},
            {categoryId: 28, skillCategory:"ALM"},
            {categoryId: 29, skillCategory:"SDLC"},
            {categoryId: 30, skillCategory:"Agile"},
            {categoryId: 31, skillCategory:"Testing"},
            {categoryId: 32, skillCategory:"VBScript"},
            {categoryId: 33, skillCategory:"SOAP UI"},
            {categoryId: 34, skillCategory:"TestNG"},
            {categoryId: 35, skillCategory:"Chef"},
            {categoryId: 36, skillCategory:"Docker"},
            {categoryId: 37, skillCategory:"Kafka"},
            {categoryId: 38, skillCategory:"Microservices"},
            {categoryId: 39, skillCategory:"NoSQL"},
            {categoryId: 40, skillCategory:"C#"},
            {categoryId: 41, skillCategory:"ASP.NET"},
            {categoryId: 42, skillCategory:"ADO.NET"},
            {categoryId: 43, skillCategory:"Entity Framework"},
            {categoryId: 44, skillCategory:"MSBuild"},
            {categoryId: 45, skillCategory:"WPF"},
            {categoryId: 46, skillCategory:"WCF"}
        ];




        /******************************************* UI *********************************************/

        $log.debug("Batches " + allBatches);
        $log.debug(allBatches);

        (function start(allBatches){
            $scope.batches = allBatches;
            if(allBatches.length > 0){
                $scope.currentBatch = allBatches[0];
                // lazy, so I change all weeks to arrays
                for ( var b in allBatches) {
                	var totalNumberOfWeeks = b.weeks;
					b.weeks = new Array(totalNumberOfWeeks);
					$log.debug(b.batchId + ' has ' + b.weeks.length + ' weeks');
					for (var int = 0; int < totalNumberOfWeeks; int++) {
						b.weeks[int].weekNumber = int + 1;
					}
				}
                // I can stop being lazy now
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
            getAllAssessmentsForWeek();
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
            caliberDelegate.trainer.createWeek($scope.currentBatch.batchId).then(function (response) {
                pushUnique($scope.currentBatch.weeks, weekNumber);
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
            getAllAssessmentsForWeek();
            var assessment = {
                assessmentId: 1,
                title: $scope.trainingName,
                batch: $scope.currentBatch.batchId,
                type: $scope.trainingType,
                categories:  $scope.selectedCategories,
                week: $scope.currentWeek.weekNumber,
                weeklyStatus: null,
                rawScore: $scope.rawScore
            };
            $log.debug(assessment);
            caliberDelegate.trainer.createAssessment(assessment).then(function (response) {
                $log.debug(response);
                getAllAssessmentsForWeek();
                if($scope.currentAssessments > 0)
                    $scope.currentAssessments.unshift(assessment);
                else $scope.currentAssessments = assessment;
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
                score: document.getElementById((traineeId+"-"+assessment.assessmentId)).value
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
                    pushUnique($scope.grades, {
                        gradeId: newGradeId,
                        assessment: assessment,
                        trainee: traineeId,
                        dateReceived: new Date()
                    });
                    $log.debug(response);
                })
        }; // updateGrade

        $scope.findGrade = function (traineeId, assessmentId) {
            $log.debug("IN FIND GRADE");
            $log.debug("Finding grade for trainee "+traineeId + " " +
                "and assessment"+assessmentId +" in the  grades array:");
            $log.debug($scope.grades);
            for(var i in $scope.grades){
                if($scope.grades[i].trainee===traineeId && $scope.grades[i].assessment.assessmentId===assessmentId)
                {
                    $log.debug("FOUND GRADE " + $scope.grades[i].gradeId);
                    $log.debug($scope.grades[i]);
                    return $scope.grades[i];
                }

            }
        };

        function weekComparator(w1,w2) {
            return (w1.weekNumber>w2.weekNumber)? 1:
                (w2.weekNumber>w1.weekNumber)?-1 : 0;
        }

        function getAllAssessmentsForWeek(){
            $scope.grades = [];
            caliberDelegate.trainer.getAllAssessments($scope.currentWeek.weekNumber)
                .then(function(data){
                    $scope.currentAssessments = data;
                    $log.debug("These are the assessments");
                    $log.debug($scope.currentAssessments);
                    $scope.currentAssessments.forEach(function (assessment) {
                        caliberDelegate.all.getGrades(assessment.assessmentId).then(function (data) {
                            $log.debug("These are the grades");
                            $log.debug(data);
                            for(var i in data){
                                $log.debug("Fetching ");
                                $log.debug(data[i]);
                                pushUnique($scope.grades,data[i]);
                            }
                        });
                    });

                });
        }
        function pushUnique(arr, item){
        	if (!arr) return;
            if (arr.indexOf(item) === -1) {
                arr.push(item);
            }
        }

    });
