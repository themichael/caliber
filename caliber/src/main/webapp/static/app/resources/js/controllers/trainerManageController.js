angular.module("trainer").controller(
    "trainerManageController",
    function ($scope, $log, caliberDelegate, allBatches) {
        $log.debug("Booted trainer manage controller.");
        $log.debug('test trainermanager cntroller -j');
        /**************************** Batch *****************************/

        /**     On page start --> load all trainers     **/
        (function start(){
            caliberDelegate.all.getAllTrainers().then(
                function(trainers){
                    $scope.trainers = trainers;
                    $log.log("=========TRAINERS=========");
                    $log.debug(trainers);
                });
            $log.debug(allBatches);
            $scope.batches = allBatches;
            $scope.selectedBatches = [];
            sortByDate(new Date().getFullYear());
        })();

        /**     Filter batches by year      **/
        $scope.years = addYears();
        function addYears() {
            var currentYear = new Date().getFullYear();
            $scope.selectedYear = currentYear;

            var data = [];
            // List all years from 2014 --> current year
            for (var y = currentYear; y >= 2014; y--) {
                data.push(y)
            }
            return data;
        }

        $scope.selectYear = function (index) {
            $scope.selectedYear = $scope.years[index];
            sortByDate($scope.selectedYear);
        };

        function sortByDate(currentYear){
            $scope.selectedBatches = [];
            for (var i = 0; i < $scope.batches.length; i++) {
                var date = new Date($scope.batches[i].startDate);
                if (date.getFullYear() === currentYear) {
                    $scope.selectedBatches.push($scope.batches[i]);
                }
            }
        }

        /**     Add & View Batches     **/
        $scope.trainingName = {
            model: null
        };
        
        $scope.trainingType = {
            model: null,
            options: ['Revature']
        };
        // load training types
        caliberDelegate.all.enumTrainingType().then(function(trainingTypes) {
        	$log.debug(trainingTypes);
        	$scope.trainingType.options = trainingTypes;
        });
     
        $scope.skillType = {														
            model: null,
            options: ['J2EE','.NET','SDET']
        };
        // load skill types   
        caliberDelegate.all.enumSkillType().then(function(skillTypes) {
        	$log.debug(skillTypes);
        	$scope.skillType.options = skillTypes;
        });
        
        $scope.location = {
            model: null,
            options: ['Reston, VA']
        };
        // load common locations
        caliberDelegate.all.enumCommonLocations().then(function(locations) {
        	$log.debug(locations);
        	$scope.location.options = locations;
        });
        
        $scope.receivers = [{value: ""}];
        $scope.addRecipient = function () {
            $scope.receivers.push({value: ""});
        };
        $scope.startDate = {
            model: null
        };
        $scope.endDate = {
            model: null
        };
        $scope.goodGradeThreshold = {
            model: null
        };
        $scope.borderlineGradeThreshold = {
            model: null
        };

        /**     Get batches for user and trainees in each batch     **/
        $scope.selectCurrentBatch = function(index) {
            $scope.currentBatch = $scope.selectedBatches[index];
            $scope.trainees = $scope.selectedBatches[index].trainees;
        };

        /**      Save Batch     **/
        $scope.addNewBatch = function () {
            //  Ajax call check for 200 --> then assemble batch
            var newBatch = {
                trainingName: $scope.trainingName.model,
                trainingType: $scope.trainingType.model,
                skillType: $scope.skillType.model,
                location: $scope.location.model,
                trainer: null,
                coTrainer: null,
                startDate: $scope.startDate.model,
                endDate: $scope.endDate.model,
                goodGradeThreshold: $scope.goodGradeThreshold.model,
                borderlineGradeThreshold: $scope.borderlineGradeThreshold.model
            };
            var trainer_name = $scope.trainer.model;
            var cotrainer_name = $scope.coTrainer.model;

            for (var i = 0; i < $scope.trainers.length; i++) {

                if ($scope.trainers[i].name == trainer_name) {
                    newBatch.trainer = $scope.trainers[i];
                }
                else if ($scope.trainers[i].name == cotrainer_name) {
                    newBatch.coTrainer =  $scope.trainers[i];
                }
            }

            $log.debug(newBatch);

            result = caliberDelegate.all.createBatch(newBatch);

            result.then(function () {
                $scope.batches.push({
                    trainingName: $scope.trainingName.model,
                    trainingType: $scope.trainingType.model,
                    skillType: $scope.skillType.model,
                    location: $scope.location.model,
                    trainer: $scope.trainer.model,
                    coTrainer: $scope.coTrainer.model,
                    startDate: $scope.startDate.model,
                    endDate: $scope.endDate.model,
                    goodGradeThreshold: $scope.goodGradeThreshold.model,
                    borderlineGradeThreshold: $scope.borderlineGradeThreshold.model
                });
                sortByDate($scope.selectedYear);
            });

        };

        /**************************** Trainees *****************************/

         /**     Load trainees for batch    **/
        $scope.name = {
            model: null
        };
        $scope.email = {
            model: null
        };
        /* Set default training status for new trainee */
        $scope.trainingStatus = "Training";
        // load training status types
        caliberDelegate.all.enumTrainingStatus().then(function(statuses) {
        	$log.debug(statuses);
        	$scope.trainingStatues = statuses;
        });
        
        
        /**      Save New Trainee Input     **/
        $scope.addNewTrainee = function () {
            for (var i = 0; i < $scope.receivers.length; i++) {
                var newTrainee = {
                    name: $scope.receivers[i].name,
                    email: $scope.receivers[i].email,
                    trainingStatus: $scope.receivers[i].trainingStatus,
                    phoneNumber: receivers[i].phoneNumber,
                    skypeId :receivers[i].skypeId,
                    profileUrl :receivers[i].profileUrl,
                    batch: $scope.currentBatch
                };
                $log.debug(newTrainee);
                caliberDelegate.all.createTrainee(newTrainee).then(function () {
                    $scope.trainees.push({
                        name: newTrainee.name,
                        email: newTrainee.email,
                        trainingStatus: newTrainee.trainingStatus,
                        phoneNumber: newTrainee.phoneNumber,
                        skypeId: newTrainee.skypeId,
                        profileUrl: newTrainee.profileUrl,
                        batch: null
                    });
                });
            }
            $scope.receivers = [{name: "", email: "", phoneNumber: "", skypeId: "", profileUrl: ""}];
        };

        /**  Add Or Remove New Trainee Form */
        $scope.receivers = [{name: "", email: "", phoneNumber: "", skypeId: "", profileUrl: ""}];
        $scope.addTrainee = function () {
            $scope.receivers.push({name: "", email: "", phoneNumber: "", skypeId: "", profileUrl: ""});
        };
        $scope.deleteTrainee = function (receiver) {
            for (var i = 0; i < $scope.receivers.length; i++) {
                if ($scope.receivers[i] === receiver) {
                    $scope.receivers.splice(i, 1);
                    break;
                }
            }
        };

    });