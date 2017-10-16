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
			
			$scope.techFeedback = [];
			$scope.counter=0;
			$scope.techId=0;
			
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
			$scope.techComment = {
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
						$scope.technologies.options = response;
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
				caliberDelegate.panel.reportTraineePanels(traineeId).then(
						function(response){
							$scope.traineePanels = response;
						});
				$log.debug($scope.traineePanels);
			};
			
			(function(){
				$log.debug("In search trainee");
				allBatches.forEach(function(batch){
					batch.trainees.forEach(function(trainee){
							$scope.employedTrainees.push(trainee);
					});
				});
				$log.debug($scope.employedTrainees);
			})();
			
			
			
			function createTechFeedback(id,tech,result,repanel,comment,counter){
				var theFeedback = {"id":id,"tech":tech,"result":result,"repanel":repanel,"comment":comment,"count":counter};
				return theFeedback;
			}
			
			$scope.addRow = function() {
				var theFeedback = createTechFeedback($scope.techId,$scope.technologies.model,$scope.panelResult.model,$scope.repanel.model,$scope.techComment.model,$scope.counter);
				$scope.techFeedback[$scope.counter]=theFeedback;
		        $scope.counter++;
		        $scope.techId++;
			}
			
			$scope.deleteRow = function(loc) {
				var newArr = [];
				var temp1 = [];
				var temp2 = [];
				var len = $scope.techFeedback.length;
				if(len==0){
					$log.debug(); // It should NEVER ENTER HERE
				}
				else if(len==1){
					$scope.techFeedback = newArr;
				}
				else{
					if(loc==0){
						$scope.techFeedback = $scope.techFeedback.slice(1);
						var num = 0;
						for (var idx in $scope.techFeedback){
							$scope.techFeedback[num].count = $scope.techFeedback[num].count-1;
							num++;
						}
					}
					else if(loc==len-1){
						$scope.techFeedback = $scope.techFeedback.slice(0,len-1);
					}
					else if(loc==len-2){
						temp1 = $scope.techFeedback.slice(0,loc);
						temp2 = [$scope.techFeedback[len-1]];
						newArr = temp1.concat(temp2);
						$scope.techFeedback = newArr;
						$scope.techFeedback[$scope.techFeedback.length-1].count = $scope.techFeedback[$scope.techFeedback.length-1].count-1;
					}
					else{
						temp1 = $scope.techFeedback.slice(0,loc);
						temp2 = $scope.techFeedback.slice(loc+1);
						var num = 0;
						for (var idx in temp2){
							temp2[num].count = temp2[num].count-1;
							num++;
						}
						newArr = temp1.concat(temp2);
						$scope.techFeedback = newArr;
					}
				}
				$scope.counter--;
				$scope.techId++;
			}
			
			$scope.savePanel = function(){
				// can get all the info here by $scope.[the ng-model name].model
			}
			
	});