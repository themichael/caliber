angular.module("trainer").controller(
    "trainerManageController",
    function ($scope, $log) {
        $log.debug("Booted trainer manage controller.");

        /**
         * Add & View Batches
         */
        $scope.batches=[{batchId: 1, trainingName: 'Batch123', trainingType: 'CUNY', skillType: 'Java', location: 'Queens, NY',
                trainer: 'Patrick', coTrainer: '', startDate: new Date(), endDate: new Date()},
            {batchId: 2, trainingName: 'Batch456', trainingType: 'Corporate', skillType: 'Java', location: 'Reston, VA',
                trainer: 'Ryan', coTrainer: 'Brian', startDate: new Date(), endDate: new Date()}
        ];
        $scope.trainingName={
            model: null
        };
        $scope.trainingType = {
            model: null,
            options: ['Corporate', 'CUNY', 'Other']
        };
        $scope.skillType = {
            model: null,
            options: ['Java', '.Net', 'C#']
        };
        $scope.location = {
            model: null,
            options: ['Reston, VA', 'Queens, NY', 'Manhattan, NY']
        };
        $scope.trainer = {
            model: null,
            options: ['Patrick', 'Joe', 'Brian', 'Ryan']
        };
        $scope.coTrainer = {
            model: null,
            options: ['Karan', 'Steven', 'Nick']
        };
        $scope.receivers=[{value:""}];
        $scope.addRecipient = function(receiver) {
            $scope.receivers.push({value:""});
        };
        $scope.startDate={
            model: null
        };
        $scope.endDate={
            model: null
        };

        /* Save Batch */
        $scope.addNewBatch = function( ){
            $scope.batches.push({trainingName: $scope.trainingName.model,
                                trainingType: $scope.trainingType.model,
                                skillType: $scope.skillType.model,
                                location: $scope.location.model,
                                trainer: $scope.trainer.model,
                                coTrainer: $scope.coTrainer.model,
                                startDate: $scope.startDate.model,
                                endDate: $scope.endDate.model})
        };

        /**
         *  View/ Add Trainees in a Batch
         */
        $scope.trainees=[{traineeName: 'John Smith', email: 'jsmith@gmail.com'},
                        {traineeName: 'Jane Doe', email: 'jdoe@yahoo.com'}
        ];
        $scope.traineeName ={
            model: null
        };
        $scope.email ={
            model: null
        };

        /* Save Trainee */
        $scope.addNewTrainee = function( ){
            $scope.batches.push({traineeName: $scope.traineeName.model,
                                email: $scope.email.model})
        };

        /* Add Or Remove New Trainee Form */
        $scope.receivers=[{value:"Test"}];
        $scope.addTrainee = function(receiver) {
            $scope.receivers.push({value:"Test1"});
        }
        $scope.deleteTrainee = function(receiver) {
            for(var i=0; i<$scope.receivers.length; i++) {
                if($scope.receivers[i] === receiver) {
                    $scope.receivers.splice(i, 1);
                    break;
                }
            }
        }
    });