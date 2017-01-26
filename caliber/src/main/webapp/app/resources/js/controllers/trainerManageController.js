angular.module("trainer").controller(
    "trainerManageController",
    function ($scope, $log) {
        $log.debug("Booted trainer manage controller.");

        $scope.trainingTypeOptions = ['Option A', 'Option B', 'Option C'];

        $scope.trainingType = $scope.trainingTypeOptions[0];
        $scope.selectTrainingType = function (index) {
            $scope.trainingType = $scope.trainingTypeOptions[index];
        };

    });