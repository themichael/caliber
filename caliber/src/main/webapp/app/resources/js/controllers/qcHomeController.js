angular.module("qc").controller("qcHomeController", function ($scope) {
    $scope.batches = ["first batch", "second batch", "third batch"];
    $scope.technologies =  ["Spring", "Hibernate", "JSP"];
    $scope.trainees = ["Osher", "Kyle", "Rikki"];
});