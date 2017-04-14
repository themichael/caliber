angular
		.module("charts")
		.controller(
				"allReportController",
				function($scope, $log, caliberDelegate, chartsDelegate,
						allBatches) {

					$log.debug("Booted Report Controller");
					$log.debug("Peacepapi is here!!!!!");

					(function start() {
						// Finishes any left over ajax animation from another
						// page
						NProgress.done();
						
						// batch null check
						if (!allBatches || allBatches.length === 0) {
							$scope.noBatches = true;
							$scope.noBatchesMessage = "No Batches belonging to you were found.";
						} else {
							$scope.noBatches = false;
							$log.debug("Here AGAIN!!!!!!");
							createDefaultCharts();
						}
						
					})();

					/**
					 * ********************************************* UI
					 * **************************************************
					 */
					var viewCharts = 0;
					
					$scope.currentWeek = 1;					// denise hard coded

					$scope.batches = allBatches;
					$scope.currentBatch = {
						trainingName : "Batch",
						batchId : 1050		// denise hard coded
					};
					$scope.currentBatch = allBatches[0]; // denise hard code/core
					$scope.currentTrainee = {
						name : "Trainee"
					};

					// hide filter tabs
					$scope.hideOtherTabs = function() {
						return $scope.currentBatch.trainingName !== "Batch";
					};

					// show charts
					$scope.showCharts = function(charts) {
						return charts === viewCharts;
					};

					function createDefaultCharts() {
						// Finishes any left over ajax animation from another
						// page
						NProgress.done();
						NProgress.start();

						createQCStatus();
						createAverageTraineeScoresWeekly();
						createAverageTraineeScoresOverall();
						createAssessmentAveragesBatchWeekly();
						createAssessmentAveragesTraineeWeekly();
						createAssessmentAveragesTraineeOverall();
						createTechnicalSkillsBatchOverall();
						createTechnicalSkillsTraineeWeekly();
						createTechnicalSkillsTraineeOverall();
						createWeeklyProgressBatchOverall();
						createWeeklyProgressTraineeWeekly();
						createWeeklyProgressTraineeOverall();

					}

					// ********************* Doughnut
					// **************************************

					function createQCStatus() {
						chartsDelegate.doughnut.data
								.getQCStatsData(1050, 1)
								.then(
										function(data) {
											$log.debug(data);
											NProgress.done();
											var doughnutChartObject = chartsDelegate.doughnut
													.getQCStats(data);
											console
													.log("here we are, in the pie method");
											console.log(doughnutChartObject);
											$scope.qcStatsLabels = doughnutChartObject.labels;
											$scope.qcStatsData = doughnutChartObject.data;
											$scope.qcStatsOptions = doughnutChartObject.options;
										}, function() {
											NProgress.done();
										});

					}

					// ***************************** Bar
					// *********************************

					function createAverageTraineeScoresWeekly() {

					}

					function createAverageTraineeScoresOverall() {

					}

					function createAssessmentAveragesBatchWeekly() {

					}

					function createAssessmentAveragesTraineeWeekly() {

					}

					function createAssessmentAveragesTraineeOverall() {

					}

					// **************************** Radar
					// ***************************************

					function createTechnicalSkillsBatchOverall() {
						/*
						 * chartsDelegate.radar.getTechnicalSkillsBatchOverallData($scope.currentBatch.batchId).then(function(data){
						 * $log.debug(data); NProgress.done(); var
						 * batchOverallRadarChartObject =
						 * chartsDelegate.radar.getBatchRankComparisonChart(data);
						 * $log.debug("Radar Chart: Created Batch Overall Batch
						 * ID: " + $scope.currentBatch.batchId);
						 * 
						 * });
						 */
					}

					function createTechnicalSkillsTraineeWeekly() {

					}

					function createTechnicalSkillsTraineeOverall() {

					}

					// ***************************** Line
					// ***************************************

					function createWeeklyProgressBatchOverall() {

					}

					function createWeeklyProgressTraineeWeekly() {

					}

					function createWeeklyProgressTraineeOverall() {

					}

					// *******************************************************************************

					/**
					 * Generates a PDF by sending HTML to server. Downloads
					 * automatically in new tab.
					 */
					$scope.generatePDF = function() {
						// indicate to user the PDF is processing
						$scope.reticulatingSplines = true;
						
						// get html element #caliber-container
						var caliber = document.getElementById("caliber-container");
						$log.debug(caliber);
						
						// iterate over all childrens to convert <canvas> to <img src=base64>
						var html = $scope.generateImgFromCanvas(caliber).innerHTML;
						$log.debug(html);
						
						var title = "Progress for " + $scope.currentBatch.trainingName;
						// generate the title
						if($scope.currentWeek)
							title = "Week "+ $scope.currentWeek +" Progress for " + $scope.currentBatch.trainingName;
						else if ($scope.currentTrainee)
							title = "Progress for " + $scope.currentTrainee.name;
						
						// send to server and download generated PDF
						caliberDelegate.all.generatePDF(title, html).then(
								function(pdf) {
									// extract PDF bytes
									var file = new Blob([ pdf ], {
										type : "application/pdf"
									});
									// create temporary 'url' and download
									// automatically
									var fileURL = URL.createObjectURL(file);
									var a = document.createElement("a");
									a.href = fileURL;
									a.target = "_blank";
									a.download = "report.pdf";
									document.body.appendChild(a);
									a.click();
									$scope.reticulatingSplines = false;
								}, function(error) {
									$log.debug(reason);
								}, function(value) {
									$log.debug(value);
								});
					}
					
					$scope.generateImgFromCanvas = function(dom){
						for (var i = 0; i < dom.childNodes.length; i++) {
						      var child = dom.childNodes[i];
						      $scope.generateImgFromCanvas(child);
						      if(child.tagName === "CANVAS"){
						    	  // swap canvas for image with base64 src
						    	  var image = new Image();
						          image.src = child.toDataURL();
						          dom.replaceChild(image, child);
						          $log.debug(dom.childNodes[i]);
						      }
						}
						return dom;
					};
					
					$scope.selectCurrentBatch = function(index) {
						$scope.currentBatch = $scope.batches[index];
						$log.debug("Selected batch " + index);
					};
					/*scope function to display the table if a batch and week has been selected*/
					$scope.displayTable = function(){
						if($scope.currentBatch.batchId && $scope.currentWeek){ // checking to see if the scope variables are null
							return true; //change to false later
						}
						return true;
					}
				});
