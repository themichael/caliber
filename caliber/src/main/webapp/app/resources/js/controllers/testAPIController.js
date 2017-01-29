/**
 * Created by kyle on 1/28/2017.
 */
angular.module("app")
    .controller("testAPIController", function($scope, $log, delegateFactory){
   $log.log("Log");
   $log.debug("Debug");
   $log.error("Error");

   $scope.allBatches = delegateFactory.trainer.getAllBatches();

});