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
				var theFeedback = {"id":id,"technology":tech,"result":result,"status":repanel,"comment":comment};
				return theFeedback;
			}
			
			$scope.addRow = function() {
				var theFeedback = createTechFeedback($scope.techId,$scope.technologies.model,$scope.panelResult.model,$scope.repanel.model,$scope.techComment.model);
				$scope.techFeedback.push(theFeedback);
				console.log($scope.techFeedback);
			}
			
			$scope.deleteRow = function(loc) {
				$scope.techFeedback.splice(loc,1);
				console.log($scope.techFeedback);
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
			
			
			
/*			function formatTech(){
				var actualFeedback = [];
				var tJSON = "";
				var tObj = null;
				var tId = "";
				var tResult = "";
				var tStatus = "";
				var tTechnology = "";
				var tComment = "";
				for (var tech in $scope.techFeedback){
					myTech = $scope.techFeedback[tech];
					tId = myTech.id;
					tResult = myTech.result;
					tStatus = myTech.repanel;
					tTechnology = myTech.technology;
					tComment = myTech.comment;
					tJSON = {"id":tId,"technology":tTechnology,"status":tStatus,"result":tResult,"comment":tComment};
					tJSON = tJSON.toString();
					actualFeedback[tech]=tObj;
				}
				return actualFeedback;
			}
*/
			
			
			$scope.savePanel = function(){
				
				var panel = {
					//vvv---Probs not the way to go about it
					trainee : $scope.traineePanels[0].trainee,
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