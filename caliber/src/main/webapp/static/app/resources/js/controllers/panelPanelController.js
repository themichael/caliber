/**
 * Team Tyraformus Rex
 * @author Lauren Wallace
 */

angular
.module("panel")
.controller(
		"panelPanelController",
		function($rootScope, $scope, $state, $log, caliberDelegate, allBatches) {
			
			$log.debug("Booted panel controller.");
			
			//
			$scope.currentBatch = allBatches[0];
			
			//For the table
			getAllTraineePanelsTable();
			var table = document.getElementById("technicalFeedback");
			$scope.table = table;
			$scope.lastrow = table.rows.length;
			$scope.counter = 1;
			$scope.consent = [{type: 'yes', value: true},{type: 'no', value: false}];
			$scope.recordingConsent = $scope.consent;
			
			//load $scope.trainees list for search results
			function getTrainees(){
				//for each batch, add the trainees to an overall list of trainees
				allBatches.forEach(function(batch){
					batch.traineesforEach(function(trainee){
						$scope.allTrainees.push(trainee);
					});
				});
			}
			
			// Get all panelists on load up
			
			// Get all panel status on load up
			caliberDelegate.all.enumPanelStatus().then(
					function(panelStatus) {
						$log.debug(panelStatus);
						$scope.panelStats = panelStatus;
					});
			
			//Get all interview modes 
			caliberDelegate.all.enumInterviewFormat().then(
					function(interviewFormat) {
						$log.debug(interviewFormat);
						$scope.interviewModes = interviewFormat;
					})
			
			// Get all categories on load up
			caliberDelegate.all.getAllCategories().then(
					function(categories) {
						$log.debug(categories);
						$scope.technologies = categories;
					});
			
			// *******************************************************************************
			// *** Tables
			// *******************************************************************************
			/**
			 * Generates the Panel table.
			 * @author Emma Bownes
			 */
			function getAllTraineePanelsTable(){
				caliberDelegate.panel.getBatchPanelTable(
						$scope.currentBatch.batchId)
						.then(
								function(response){
									NProgress.done();
									console.log(response);
									$scope.allTraineesPanelData = response;
								},
								function(response){
									NProgress.done();
									
								})
			}
			
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
			
			/*savePanel() {
				
			}*/
			
	});