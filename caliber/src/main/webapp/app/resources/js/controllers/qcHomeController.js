angular.module("qc").controller("qcHomeController", function ($scope, $log, delegateFactory) {

    $scope.batches = ["Batch1311", "Batch1612", "Batch1512", "Batch1812", "Batch0910", "Batch0805", "Batch0408"];
    $scope.tech = ["Spring", "Hibernate", "JSP"];
    $scope.trainees = ["Osher", "Kyle", "Rikki"];

    $log.log(delegateFactory.qc.getAllBatches());

    $scope.currentBatch = $scope.batches[0];

    $scope.currentTech = "Technology";

    $scope.currentTrainee = "Trainee";

    $scope.selectCurrentBatch = function (index) {
        $scope.currentBatch = $scope.batches[index];
    };

    $scope.selectCurrentTech = function (index) {
        $scope.currentTech = $scope.tech[index];
    };

    $scope.selectCurrentTrainee = function (index) {
        $scope.currentTrainee = $scope.trainees[index];
    };
});