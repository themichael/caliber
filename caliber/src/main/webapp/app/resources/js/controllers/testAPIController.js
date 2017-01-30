/**
 * Created by kyle on 1/28/2017.
 */
angular.module("app")
    .controller("testAPIController", function($scope, $log, caliberDelegate){
   $log.debug("Launched REST API");


   $scope.qcBatches = caliberDelegate.qc.getAllBatches();

});