/**
 * Team Tyraformus Rex
 * @author Lauren Wallace, Katie Bixby
 */

angular
.module("panel")
.controller(
		"panelModalController",
		function($rootScope, $scope, $state, $log, caliberDelegate, allBatches) {
			
			console.log("in panel controller");
			$log.debug("Booted panel controller.");
			
			$scope.employedTrainees = [];
			$scope.technologies = [];
			//getAllTraineePanelsTable();
			/*var table = document.getElementById("technicalFeedback");
			$scope.table = table;
			$scope.lastrow = table.rows.length;
			$scope.counter = 1;*/
			
			$scope.traineePanels = [];
			$scope.employeedTrainees = [];
			
			//Create the form models & their options
			$scope.recordingConsent = {
				model: null,
				options: ['yes', 'no']
			};
			
			$scope.panelStatus = {
				model: null,
				options: []
			};
			
			//overall panelStatus
			$scope.repanel = {
				model: null,
				options: []
			};
			
			$scope.interviewMode = {
				model: null,
				options: []
			};
			
			$scope.interviewConnectivity = {
				model: null,
				options: ['stable', 'unstable']
			};
			
			$scope.panelResult = {
				model: null,
				options: ['0 - Very Poor', '1 - Moderately Poor', '2 - Fairly Poor', '3 - Poor', '4 - Below Average', '5 - Average', '6 - Above Average', '7 - Good', '8 - Very Good', '9 - Great', '10 - Excellent']
			};
			
			$scope.technologies = {
				model: null,
				options: []
			};
			
			$scope.overallStatus = {
				model: null,
				options: []
			};
			
			$scope.associateIntro = {
					model: null
			}
			$scope.p1Expl = {
					model: null
			}
			$scope.p2Expl = {
					model: null
			}
			$scope.p3Expl = {
					model: null
			}
			$scope.communicationSkills = {
					model: null
			}
			$scope.interviewDuration = {
					model: null
			}
			$scope.panelRound = {
					model: null
			}
			$scope.overallPanel = {
					model: null
			}
			$scope.recordingLink = {
					model: null
			}
			$scope.interviewDuration = {
					model: null
			}
			$scope.interviewDate = {
					model: null
			}
			$scope.interviewTime = {
					model: null
			}

			// Get all panel status on load up
			caliberDelegate.all.enumPanelStatus().then(
					function(response) {
						$log.debug(response);
						$scope.panelStatus.options = response;
						$scope.repanel.options = response;
						$scope.overallStatus.options = response;
					});
			
			// Get all interview modes
			caliberDelegate.all.enumInterviewFormat().then(
					function(response) {
						$log.debug(response);
						$scope.interviewMode.options = response;
					});
			
			// Get all categories on load up
			caliberDelegate.all.getAllCategories().then(
					function(response) {
						$log.debug(response);
						$scope.technologies = response;
					});
			
			// *******************************************************************************
			// *** Tables
			// *******************************************************************************
		
			$scope.selectChosenTrainee = function(traineeName){
				let traineeId = -1;
				$scope.employedTrainees.forEach(function(trainee) {
					if (trainee.name === traineeName) {
						traineeId = trainee.traineeId;
					}
				});
				$scope.traineePanels = caliberDelegate.panel.reportTraineePanels(traineeId);
			};
			
			/*(function(){
				$log.debug("In search trainee");
				allBatches.forEach(function(batch){
					batch.trainees.forEach(function(trainee){
							$scope.employedTrainees.push(trainee);
							console.log(trainee.trainingStatus);
					});
				});
				console.log($scope.employedTrainees);
				$log.debug($scope.employedTrainees);
			})();*/
			
			function addRow() {
				var newRow = $("<tr>");
				var cols = "";
				
				cols += '<td><select ng-model="" class="form-control" name="technicalFeedback.technology"><option ng-repeat="">{{}}</option></select></td>';
		        cols += '<td><select ng-model="" class="form-control" name="technicalFeedback.result"><option ng-repeat="">{{}}</option></select></td>';
		        cols += '<td><select ng-model="" class="form-control" name="technicalFeedback.repanel"><option ng-repeat=""></option></select></td>';
		        cols += '<td><input type="text" class="form-control" name="technicalFeedback.comment"></td>';
		        cols += '<td><div ng-click="addRow()" ng-show="counter==lastrow"><span class="glyphicon glyphicon-plus" style="color: #F26925;" aria-hidden="true"></span></div></td>';
		        cols += '<td><div ng-click="deleteRow()" ng-hide="counter==1"><span class="glyphicon glyphicon-remove" style="color: #F26925;" aria-hidden="true"></span></div></td>';
		        newRow.append(cols);
		        $("technicalFeedback").append(newRow);
		        $scope.counter++;
			}
			
			function deleteRow() {
				$(this).closest("tr").remove();       
		        counter -= 1;
			}
			
			$scope.savePanel = function(){
				// can get all the info here by $scope.[the ng-model name].model
			}
			
	});