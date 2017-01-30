angular.module("trainer").controller(
    "trainerManageController",
    function ($scope, $log) {
        $log.debug("Booted trainer manage controller.");



        $scope.years = addYears();
        function addYears(){
            var currentYear = new Date().getFullYear();
            $scope.selectedYear = currentYear;

            var data = [];
            for(var y=currentYear; y>=2014; y--) {
                data.push(y)
            }
            return data;
        }

        $scope.getBatches=[];
        $scope.selectYear= function(index) {
            $scope.selectedYear = $scope.years[index];
            for(var i=0; i < $scope.batches.length; i++){
                if($scope.batches[i].startDate.getFullYear() === $scope.selectedYear){
                    $scope.getBatches.push($scope.batches[i]);
                }
            }
        };

        /**
         * Add & View Batches
         */
        $scope.batches=[{trainingName: 'Batch123', trainingType: 'CUNY', skillType: 'Java', location: 'Queens, NY',
                trainer: 'Patrick', coTrainer: '', startDate: new Date(), endDate: new Date()},
            {trainingName: 'Batch456', trainingType: 'Corporate', skillType: 'Java', location: 'Reston, VA',
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

        /* Add Or Remove New Trainee Form */
        $scope.receivers=[{traineeName:"", email:""}];
        $scope.addTrainee = function() {
            $scope.receivers.push({traineeName: "", email: ""});
        }
        $scope.deleteTrainee = function(receiver) {
            for(var i=0; i<$scope.receivers.length; i++) {
                if($scope.receivers[i] === receiver) {
                    $scope.receivers.splice(i, 1);
                    break;
                }
            }
        }

        /* Save Trainee */
        $scope.addNewTrainee = function( ){
            for(var i=0; i<$scope.receivers.length; i++) {
                $scope.trainees.push({
                    traineeName: $scope.receivers[i].traineeName,
                    email: $scope.receivers[i].email
                })
            }
            $scope.receivers=[{traineeName:"", email:""}];
        };
    });