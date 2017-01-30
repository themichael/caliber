/**
 * Created by kyle on 1/28/2017.
 */
angular.module("app")
    .controller("testAPIController", function($scope, $log, caliberDelegate){
   $log.log("Log");
   $log.debug("Debug");
   $log.error("Error");

   $scope.allBatches = caliberDelegate.trainer.getAllBatches();

});