/**
 * Team Tyraformus Rex
 * @author Lauren Wallace
 */

angular
.module("panel")
.controller(
		"panelPanelController",
		function($rootScope, $scope, $state, $log, caliberDelegate) {
			
			$log.debug("Booted panel controller.");
			
			//What you see in the modal panel
			var interviewDate = new Date();
			var trainee = $scope;
			$scope.interviewDate = i;
			$scope.selectedTrainingType = OVERALL;
			$scope.selectedSkill = OVERALL;
			$scope.skillstack = [];
			$scope.trainingTypes = [];
			$scope.currentTraineeId = ALL;
			$scope.allTrainees = [];
			
			
			
	});