/**
 * Team Tyraformus Rex
 * @author Lauren Wallace, Katie Bixby
 */

angular
.module("panel")
.controller(
		"panelModalController",
		function($rootScope, $scope, $state, $log,$cookies, caliberDelegate, allBatches) {
			
			console.log("in panel controller");
			$log.debug("Booted panel controller.");
			
			$scope.techFeedback = [];
			$scope.counter=0;
			$scope.techId=0;
		
			// Objects or list of objects that need to be created for this form
			$scope.traineePanels = [];
			$scope.employedTrainees = [];
			$scope.batchSkillType = {};
			
			// Create the form models & their options
			$scope.trainee = {
				name: ""
			};
			
			$scope.panelist = {
				name: ""
			};
			
			$scope.recordingConsent = {
				model: null,
				options: ['True', 'False']
			};
			
			$scope.panelStatus = {
				model: null,
				options: []
			};
			
			// Stores overall panelStatus
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
				options: ['Stable', 'Unstable']
			};
			
			$scope.panelResult = {
				model: null,
//				options: ['0 - Very Poor', '1 - Moderately Poor', '2 - Fairly Poor', '3 - Poor', '4 - Below Average', '5 - Average', '6 - Above Average', '7 - Good', '8 - Very Good', '9 - Great', '10 - Excellent']
				options:[0,1,2,3,4,5,6,7,8,9,10]
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
			};
			
			$scope.p1Expl = {
					model: null
			};
			
			$scope.p2Expl = {
					model: null
			};
			
			$scope.p3Expl = {
					model: null
			};
			
			$scope.communicationSkills = {
					model: null
			};
			
			$scope.interviewDuration = {
					model: null
			};
			
			$scope.panelRound = {
					model: null
			};
			
			$scope.overallPanel = {
					model: null
			};
			
			$scope.recordingLink = {
					model: null
			};
			
			$scope.interviewDuration = {
					model: null
			};
			
			$scope.interviewDate = {
					model: null
			};
			
			$scope.interviewTime = {
					model: null
			};
			
			$scope.techComment = {
					model: null
			};

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
							//FIX THIS
							$scope.trainee.name = traineeName;
							//$scope.panelist.name = $scope.traineePanels[0].panelist.name;
						});
				$log.debug($scope.traineePanels);
				$scope.currentBatch();
			};
			
			(function(){
				allBatches.forEach(function(batch){
					batch.trainees.forEach(function(trainee){
						$scope.employedTrainees.push(trainee);
					});
				});
				$log.debug($scope.employedTrainees);
			})();
			
			function createTechFeedback(id,tech,result,repanel,comment,counter){
				var theFeedback = {"id":id,"technology":tech,"result":result,"status":repanel,"comment":comment,"count":counter};
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

			// Sets the Training Type
			$scope.currentBatch = function() {
				$log.debug("In skillType funtion");
				allBatches.forEach(function(batch){
					batch.trainees.forEach(function(t){
						if(t.id === $scope.trainee.id) {
							$scope.batchSkillType = batch.skillType;
						}
					});
				});
				$log.debug($scope.batchSkillType);
			};
			
			$scope.savePanel = function(){
				
				var panel = {
					//vvv---Probs not the way to go about it
					trainee : $scope.trainee.name,
					panelist : {},
					interviewDate : $scope.interviewDate.model,
					duration : $scope.interviewDuration.model,
					format : $scope.interviewMode.model,
					internet : $scope.interviewConnectivity.model,
					panelRound : $scope.panelRound.model,
					recordingConsent: $scope.recordingConsent.model,
					recordingLink: $scope.recordingLink.model,
					status: $scope.overallStatus.model,
					feedback: $scope.techFeedback,
					associateIntro: $scope.associateIntro.model,
					projectOneDescription : $scope.p1Expl.model,
					projectTwoDescription : $scope.p2Expl.model,
					projectThreeDescription : $scope.p3Expl.model,
					communicationSkills : $scope.communicationSkills.model,
					overall : $scope.overallPanel.model
				};
				$log.debug(panel);
				caliberDelegate.panel.createPanel(panel);
				//$scope.resetPanelForm();
			};
			
			// Resets the Panel Feedback Form(needs fixing)
			$scope.resetPanelForm = function() {
				//$scope.trainee.name = "";
				//$scope.panelist.name = "";
				//$scope.batchSkillType = {};
				$scope.panelRound.model = null;
				$scope.recordingConsent.model = null;
				$scope.interviewDate.model = null;
				$scope.interviewTime.model = null;
				$scope.interviewConnectivity.model = null;
				$scope.associateIntro.model = null;
				$scope.p1Expl.model = null;
				$scope.p2Expl.model = null;
				$scope.p3Expl.model = null;
				$scope.communicationSkills.model = null;
				$scope.technologies.model = null;
				$scope.panelResult.model = null;
				$scope.repanel.model = null;
				$scope.techComment.model = null;
				$scope.overallPanel.model = null;
				$scope.overallStatus.model = null;
				$scope.recordingLink.model = null;
				$scope.interviewDuration.model = null;
			}
	});