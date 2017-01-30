/**
 * Created by kyle on 1/28/2017.
 */
angular.module("app")
    .controller("testAPIController", function($scope, $log, caliberDelegate){
   $log.debug("Launched REST API");

   $scope.all = {};
   $scope.vp = {};
   $scope.qc = {};
   $scope.trainer = {};

   /************************ Test All ***********************/
   // create batch
   $scope.createBatch = function(batchObj){
      return caliberDelegate.all.createBatch(batchObj);
   };
   // update batch
   $scope.all.updateBatch = function(batchObj){
      return caliberDelegate.all.updateBatch(batchObj);
   };

   // delete batch
   $scope.all.deleteBatch = function(batchId){
      return caliberDelegate.all.deleteBatch(batchId);
   };

   // create trainee
   $scope.all.createTrainee = function(traineeObj){
     return caliberDelegate.all.createTrainee(traineeObj);
   };

   // update trainee
   $scope.all.updateTrainee = function(traineeObj){
     return caliberDelegate.all.updateTrainee(traineeObj);
   };

   // delete trainee
   $scope.all.deleteTrainee = function(traineeId){
     return caliberDelegate.all.deleteTrainee(traineeId);
   };

   // get grades for assessments
   $scope.all.getGrades = function(assessmentId){
     return caliberDelegate.all.getGrades(assessmentId);
   };

   // all trainers
   $scope.all.getAllTrainers = function(){
     return caliberDelegate.all.getAllTrainers();
   };

   /************************ VP ************************/
   // all batches
   $scope.vp.getAllBatches = function(){
     return caliberDelegate.getAll
   };

   /********************** Test QC *********************/
   $scope.qcBatches = caliberDelegate.qc.getAllBatches();



});