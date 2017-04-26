/**
 * Refactor to use week index instead of Week object
 */
angular
		.module("trainer")
		.controller(
				"trainerAssessController",
				function($rootScope, $timeout,$log, $scope, $state, chartsDelegate, caliberDelegate,
						allBatches) {
					// Week object
					function Week(weekNumb, assessments) {
						this.weekNumb = weekNumb;
						this.assessments = assessments;
					}
					
					$scope.noTrainees = false;
					
					// array of weeks to parse through and display tabs
					
					$log.debug("Booted Trainer Aesess Controller");
					
					
					$scope.trainerBatchNote = null;
					// Note object This is needed to create notes for batch. 
					function Note(noteId, content, status, week, batch,
							trainee, maxVisibility, type, qcFeedback) {
						this.noteId = noteId;
						this.content = content;
						this.week = week;
						this.batch = batch;
						this.trainee = trainee;
						this.maxVisibility = maxVisibility;
						this.type = type;
						this.qcFeedback = qcFeedback;
						this.qcStatus = status;
					}
					
					// load categories
					$scope.skill_categories = function() {
						caliberDelegate.all.getAllCategories().then(
								function(categories) {
									$scope.categories = categories;
									$log.debug("all Categories");
									// $log.debug(categories);
								});
					};
					/**
					 * ***************************************** UI
					 * ********************************************
					 */
					
					
					$scope.grades={};
					 $scope.getAllGradesForWeek=function(batchId,weekId) {							
							caliberDelegate.all
									.getGradesForWeek(batchId,
											weekId).then(
											function(data) {
												if(Object.keys(data).length === 0){
													$scope.grades = false;
												}else{
													$scope.grades = data;
													$scope.doGetAllAssessmentsAvgForWeek($scope.currentBatch.batchId,$scope.currentWeek);
												}
											});
					 }
						$scope.assignTraineeScope = function(traineeId){
							if($scope.trainees[traineeId] === undefined){
								$scope.trainees[traineeId] = {};
								$scope.trainees[traineeId].assessments=[];
								$scope.trainees[traineeId].note={};
								$scope.trainees[traineeId].burrito=false;
							}								
							return $scope.trainees[traineeId];							
						}
						$scope.updateTraineeScopeWithCurrentAssessment = function(assessments){
							angular.forEach($scope.trainees,function(key,value){
								for(a in assessments){
									if($scope.trainees[value].assessments[assessments[a].assessmentId] === undefined){
										$scope.trainees[value].assessments[assessments[a].assessmentId] = {};
										$scope.trainees[value].assessments[assessments[a].assessmentId].score='';
									}
								}
							});
						}
					
						// get trainee notes and put into
						// $scope.trainees[traineeId].note
						$scope.getTraineeBatchNotesForWeek=function(batchId,weekId){
							caliberDelegate.trainer.getTraineeBatchNotesForWeek(batchId,weekId).then(function(data){
								angular.forEach($scope.trainees,function(key,value){
									$scope.trainees[value].note = {};
								});
								if(data && data.length > 0){
									$scope.notes = data;
									for(note of $scope.notes){
										if($scope.trainees[note.trainee.traineeId] !== undefined && $scope.trainees[note.trainee.traineeId].note.hasOwnProperty('noteId')){
											$scope.trainees[note.trainee.traineeId].note = {};
										}
										if($scope.trainees[note.trainee.traineeId] !== undefined){
											$scope.trainees[note.trainee.traineeId].note = note;
										}
									}
								}
							});
						}
						
					// ////////////////////////////////////////////////////////////////////////
					// load note types
					caliberDelegate.all.enumNoteType().then(
							function(noteTypes) {
								$log.debug(noteTypes);
								// do something with note type
							});
					$scope.assessmentType = {
						model : null,
						options : []
					};
					caliberDelegate.all
							.enumAssessmentType()
							.then(
									function(assessmentTypes) {
										$log.debug(assessmentTypes);
										$scope.assessmentType.options = assessmentTypes;
									});
					$log.debug("Batches " + allBatches);
					$log.debug(allBatches);
					
					(function start(allBatches) {
						/*set children of modal to false on click to prevent modal from fading out when clicking
						 * on child elements*/
						$(".editAssessModal .modal-body, .editAssModal .modal-footer").on("click", false);
						/*Implemented due to modal-backdrop class duplicating itself and not going away
						 * when clicking area outside of modal document*/
						$('.editAssessModal').on("click",function(e) {
							e.stopPropagation();
						    $(".modal").modal("hide");
						});
						$scope.batches = allBatches;
						if (!allBatches) return;
						if (allBatches.length > 0) { 								// shows
																					// the
																					// latest
																					// batches
							
							if(!$scope.currentBatch){ // if currentBatch is not yet in the scope, run for assess batch
								$scope.currentBatch = allBatches[0];
							}
							
							$scope.currentBatch.trainees.sort(compare);
							
							$log.debug("This is the current batch "
									+ $scope.currentBatch);
							
							if (allBatches[0].weeks > 0) {
                                $scope.category = {
									model : null,
									options : []
								};
								caliberDelegate.all
										.getAllCategories()
										.then(
												function(categories) {
													$log.debug(categories);
													$scope.category.options = categories;
												});


								caliberDelegate.all.enumNoteType().then(
										function(noteTypes) {
											$log.debug(noteTypes);
											// do something with note type
										});
								$scope.assessmentType = {
									model : null,
									options : []
								};
								caliberDelegate.all
										.enumAssessmentType()
										.then(
												function(assessmentTypes) {
													$log.debug(assessmentTypes);
													$scope.assessmentType.options = assessmentTypes;
												});
								$log.debug("Batches " + allBatches);
								$log.debug(allBatches);
								
								if(!$scope.currentWeek){ // if currentWeek is not yet in the scope, run for assess batch
								var totalWeeks = allBatches[allBatches.length-1].weeks; // the
								// number
								// of
								// weeks
								// for
								// that
								// batch
								$log
										.debug("this is the total week for this batch "
												+ allBatches[allBatches.length-1].trainingName
												+ ": " + totalWeeks);

								$scope.currentWeek = totalWeeks; // change here***********************************************************************************
								}
								/**
								 * *****************************************
								 * getAllAssessmentsForWeek
								 * *********************************************************
								 */
								getAllAssessmentsForWeek(
										$scope.currentBatch.batchId,
										$scope.currentWeek);

							} else
								$scope.currentWeek = null;
						} 
						$log.debug("Starting Values: currentBatch and currentWeek");
						$log.debug($scope.currentBatch);
						$log.debug($scope.currentWeek);
						
						$scope.trainees={};						
						
						for(trainee of $scope.currentBatch.trainees){
							$scope.assignTraineeScope(trainee.traineeId);
						}
						
					})(allBatches);

					// default -- view assessments table
					$scope.currentView = true;

					// back button
					$scope.back = function() {
						$scope.currentView = true;
					};
					
					/******* Filter batches by year ************/
					$scope.years = addYears();
					function addYears() {
						var currentYear = new Date().getFullYear();
						$scope.selectedYear = currentYear;

						var data = [];
						// List all years from 2014 --> current year
						for (var y = currentYear + 1; y >= currentYear - 2; y--) {
							data.push(y)
						}
						return data;
					}

					$scope.selectYear = function(index) {
						$scope.selectedYear = $scope.years[index];
						$scope.selectedYear= parseInt($scope.currentBatch.startDate.substring(0,4));
						sortByDate($scope.selectedYear);
						batchYears();
						$scope.currentBatch = $scope.batchesByYear[0];
						$log.debug(batchYears());
						$log.debug($scope.currentBatch);
						if ($scope.batchesByYear.length === 0) {
							$scope.noBatches = true;
							$scope.noBatchesMessage = "No Batches were found for this year.";
						} else {
							$scope.noBatches = false;
							//createDefaultCharts();
							$scope.selectedYear = $scope.years[index];
							sortByDate($scope.selectedYear);
						
						
						$scope.trainees={};						
						for(trainee of $scope.currentBatch.trainees){
							$scope.assignTraineeScope(trainee.traineeId);}
						if ($scope.currentBatch.weeks > 0) {
							$scope.currentWeek = $scope.currentBatch.weeks;
							getAllAssessmentsForWeek(
									$scope.currentBatch.batchId,
									$scope.currentWeek);
						} else
							$scope.currentWeek = null;

						getAllAssessmentsForWeek($scope.currentBatch.batchId,
								$scope.currentWeek);
						
					} };

					function sortByDate(currentYear) {
						$scope.selectedBatches = [];
						for (var i = 0; i < $scope.batches.length; i++) {
							var date = new Date($scope.batches[i].startDate);
							if (date.getFullYear() === currentYear) {
								$scope.selectedBatches.push($scope.batches[i]);
							}
						}
					}
					
					/**
					 * Get batch according to year
					 */
					
					function batchYears() {
						$scope.batchesByYear = [];
						
						for (var i = 0; i < $scope.batches.length; i++) {
							//$log.debug("Current Year: " + $scope.selectedYear);
							if ($scope.selectedYear === parseInt($scope.batches[i].startDate)) {
								$scope.batchesByYear.push($scope.batches[i]);
								// $log.debug($scope.batches[i]);
							}
							
							// $log.debug($scope.selectedYear + " === " + parseInt($scope.batches[i].startDate))

						}
						$log.debug($scope.batches);
						$log.debug($scope.batchesByYear);
						
					}
					
					
					
					/******* Filter batches by year ************/
					

					// batch drop down select
					$scope.selectCurrentBatch = function(index) {
						$scope.currentBatch = $scope.batchesByYear[index];
						$log.debug("Selected batch " + index);
						$scope.currentBatch.trainees.sort(compare);

					// create new scope of trainees
						$scope.trainees={};						
						for(trainee of $scope.currentBatch.trainees){
							$scope.assignTraineeScope(trainee.traineeId);
						}
						if ($scope.currentBatch.weeks > 0) {
							$scope.currentWeek = $scope.currentBatch.weeks;
							getAllAssessmentsForWeek(
									$scope.currentBatch.batchId,
									$scope.currentWeek);
						} else
							$scope.currentWeek = null;
						
						getAllAssessmentsForWeek($scope.currentBatch.batchId,
								$scope.currentWeek);
					};

					// select week
					$scope.selectWeek = function(index) {
						
						$scope.currentWeek = $scope.currentBatch.arrayWeeks[index];
						$log.debug("[***********This is the week selected*************]:  "+$scope.currentWeek);
	
						getAllAssessmentsForWeek($scope.currentBatch.batchId,
								$scope.currentWeek);
					};
					
					

					// active week
					$scope.showActiveWeek = function(index) {
						if ($scope.currentWeek === $scope.currentBatch.arrayWeeks[index])
							return "active";

					};
					
					// create week
					$scope.createWeek = function() {
						if($scope.currentBatch.trainees.length === 0){
							$scope.noTrainees = true;
							$scope.noTraineesMessage ="No trainnees were found, weeks cannot be created.";
							$log.debug("NO Trainees");
							$log.debug($scope.noTraineesMessage);
							$log.debug($scope.noTrainees);
						}else{
						caliberDelegate.trainer.createWeek($scope.currentBatch.batchId).then(
								function(response) {
									$scope.currentBatch.weeks += 1;
									$scope.currentBatch.arrayWeeks.push($scope.currentBatch.weeks);
									$scope.showActiveWeek($scope.currentBatch.weeks);
									$scope.selectWeek($scope.currentBatch.weeks-1); // the
																					// new
																					// index
																					// of
																					// the
																					// week
																					// selected
								});
					} };
					// select assessment from list
					$scope.selectAssessment = function(index) {
						$scope.currentAssessment = $scope.currentAssessment[index];
						$scope.currentView = false;
						/** replace with ajax call to get grades by assessmentId * */
					};

					$scope.addAssessment = function() {
						getAllAssessmentsForWeek($scope.currentBatch.batchId,
								$scope.currentWeek);
						var assessment = {
								batch : $scope.currentBatch,
								type : $scope.assessmentType.model,
								category : angular.fromJson($scope.category.model),
								week : $scope.currentWeek,
								rawScore : $scope.rawScore
						};
						$log.debug(assessment);
						caliberDelegate.trainer
								.createAssessment(assessment)
								.then(
										function(response) {
											$log.debug(response);
											getAllAssessmentsForWeek(
													$scope.currentBatch.batchId,
													$scope.currentWeek);
											if ($scope.currentAssessments > 0)
												$scope.currentAssessments
														.unshift(assessment);
											else
												$scope.currentAssessments = assessment;
											angular.element(
													"#createAssessmentModal")
													.modal("hide");
										});
					};
					$scope.selectedCategories = [];

					$scope.toggleSelection = function(category) {
						var index = $scope.selectedCategories.indexOf(category);
						if (index > -1)
							$scope.selectedCategories.splice(index, 1);
						else
							$scope.selectedCategories.push(category);
					};
					
					/** *******TrainerBatch Notes********** */	
					$scope.getTBatchNote = function (batchId, week){	
								caliberDelegate.trainer
										.getTrainerBatchNote(batchId, week)
										.then(
												function(trainerBatchNotes) {
													if (trainerBatchNotes === undefined) {
														$log.debug("EMPTY!");												
													}else{																								
													$scope.trainerBatchNote = trainerBatchNotes[0];
													$log.debug(trainerBatchNotes);}
												});
						};

					// get all assesments
					// **********************************************************8888888888***********************************************************
					function getAllAssessmentsForWeek(batchId, weekNumb) {
						if (!weekNumb)
							return;
						caliberDelegate.trainer
								.getAllAssessmentsForWeek(batchId, weekNumb)
								.then(
										function(data) {
											$log.debug(data);
											$scope.currentAssessments = data;
											$scope.getAllGradesForWeek($scope.currentBatch.batchId,$scope.currentWeek);
											var week = new Week(
													$scope.currentWeek,
													$scope.currentAssessments);
											$scope.currentBatch.displayWeek = week;
											$scope.currentBatch.arrayWeeks = [];
											// create array of assessments
											// mapped by assessment Id;
											$scope.assessmentsById=[]
											
											$scope.generateArrAssessmentById(data);
											$scope.updateTraineeScopeWithCurrentAssessment($scope.currentAssessments);
											for(i = 1; i <= $scope.currentBatch.weeks; i++){
												$scope.currentBatch.arrayWeeks.push(i);
											}
											//$scope.selectedYear = parseInt($scope.currentBatch.startDate.substring(0,4));
											batchYears();
											$scope.getTBatchNote($scope.currentBatch.batchId, $scope.currentWeek);
											$scope.allAssessmentsAvgForWeek = false;
											$scope.getTraineeBatchNotesForWeek($scope.currentBatch.batchId, $scope.currentWeek);
//											caliberDelegate.all.getAssessmentsAverageForWeek(
//													$scope.currentBatch.batchId
//													, $scope.currentWeek
//													).then(function(response){
//														$timeout(function(){
//															if(response){
//																$scope.allAssessmentsAvgForWeek = response.toFixed(2).toString() + '%';
//															}else{
//																return;
//															}
//															},4000);															
//													});
										});
										
					};
					
					$scope.doGetAllAssessmentsAvgForWeek = function(batchId, week){

						caliberDelegate.all.getAssessmentsAverageForWeek(batchId, week)
							.then(function(response){
										$timeout(function(){
											if(response){
												$scope.allAssessmentsAvgForWeek = response.toFixed(2).toString() + '%';
											}else{
												return;
											}
										},4000);															

							});
					}
					
					/** *******Save TrainerBatch Notes********** */	
					$scope.saveTrainerNotes = function(batchNoteId) {
						$log.debug("Saving note: " + $scope.trainerBatchNote);
						// Create note
						if ($scope.trainerBatchNote.noteId === undefined) {
							$scope.trainerBatchNote = new Note(
									null,
									$scope.trainerBatchNote.content,
									null,
									$scope.currentWeek,
									$scope.currentBatch,
									null, "ROLE_TRAINER",
									"BATCH", false);	
							caliberDelegate.trainer.createNote($scope.trainerBatchNote).then(
							// Set id to created notes id
							function(id) {
								$scope.trainerBatchNote.noteId = id;
							});
						}  
						// Update existing note
						else {
							$scope.trainerBatchNote = new Note(batchNoteId, $scope.trainerBatchNote.content,
									null, $scope.currentWeek, $scope.currentBatch, null, "ROLE_TRAINER", "BATCH", false);
							caliberDelegate.trainer.updateNote($scope.trainerBatchNote);
						}
					}
								
					$scope.generateArrAssessmentById = function(assessments){
						var totalRawScore = 0;
						for(a of assessments){
							$scope.assessmentsById[a.assessmentId] = {};
							$scope.assessmentsById[a.assessmentId].total = 0;
							$scope.assessmentsById[a.assessmentId].rawScore = a.rawScore; 
							totalRawScore += a.rawScore;
						}						
						for(a of assessments){
							$scope.assessmentsById[a.assessmentId]
							.weightedScore = $scope.getWeightedScore(
									$scope.assessmentsById[a.assessmentId].rawScore
									,totalRawScore
									).toFixed(0).toString() + '%';;
						}
					}
					$scope.getWeightedScore = function(rawScore,totalRawScore){
						return (rawScore/totalRawScore) * 100;
					}

					/**
					 * Updates Grade if exists, else create new Grade, then
					 * saves to $scope
					 * 
					 * @param gradeId
					 * @param traineeId
					 * @param assessment
					 */

					$scope.updateGrade = function(trainee,assessment) {
						if($scope.trainees[trainee.traineeId].assessments[assessment.assessmentId] === undefined){
							$scope.trainees[trainee.traineeId].assessments[assessment.assessmentId] = {};
						}
						// constructs Grade object from the data in table
						var grade = {
							trainee : trainee,
							assessment : assessment,
							dateReceived : new Date(),
							score :$scope.trainees[trainee.traineeId].assessments[assessment.assessmentId].score,
						};
						/*
						 * if assessment object has gradeId, define it in grade
						 * object
						 */
						if($scope.trainees[trainee.traineeId].assessments[assessment.assessmentId].gradeId){
							grade.gradeId = $scope.trainees[trainee.traineeId].assessments[assessment.assessmentId].gradeId;
						}
						// adds new Grade if not exists, else update,
						// response contains the ID of the created/updated Grade
						caliberDelegate.trainer.addGrade(grade).then(
								function(response) {
									$log.debug("Adding grade to $scope");
									$log.debug(response);
									return response;
								}).then(function(response){
									$scope.trainees[response.data.trainee.traineeId].assessments[response.data.assessment.assessmentId].gradeId = response.data.gradeId;
									$scope.allAssessmentsAvgForWeek = false;
									return response;
								}).then(function(){
									$scope.doGetAllAssessmentsAvgForWeek($scope.currentBatch.batchId,$scope.currentWeek);									
								});
					}; 

					$scope.findGrade = function(traineeId, assessmentId) {
							if(!$scope || !$scope.grades || !traineeId || ($scope.grades[traineeId] === undefined)){ 
								return;
							}else{
								for(var grade of $scope.grades[traineeId]){
									/*
									 * create a assessment object that contains
									 * gradeId for each $scope.trainees[trainee]
									 */
									if(grade.assessment.assessmentId === assessmentId){
										if($scope.trainees[traineeId].assessments[grade.assessment.assessmentId] === undefined){
											$scope.trainees[traineeId].assessments[grade.assessment.assessmentId] = {};
										}
										if($scope.trainees[traineeId].assessments[grade.assessment.assessmentId].gradeId === undefined){
											$scope.trainees[traineeId].assessments[grade.assessment.assessmentId].gradeId = grade.gradeId;
											$scope.trainees[traineeId].assessments[grade.assessment.assessmentId].score = grade.score;										
										}
										return grade.score;
									}
								}
							}
					};
					
					/* - save trainee note
					   - send to "/note/update"
					   By Jack	
					  */					 
					$scope.saveOrUpdateTraineeNote=function(traineeId){
						var traineeNote = $scope.trainees[traineeId].note
						var trainee = $scope.currentBatch.trainees.filter(function(trainee) {
							  return trainee.traineeId === traineeId;
							});
						//create noteObj to send to controller
						var noteObj={
								content:traineeNote.content,
								week:$scope.currentWeek,
								trainee:trainee[0],
								type:"TRAINEE",
								batch:$scope.currentBatch
						}
						//if noteId exists, add it to noteObj to get noteObj in db to update
						if($scope.trainees[traineeId].note.noteId !== undefined){
							noteObj.noteId = traineeNote.noteId;
						}
						caliberDelegate.trainer.saveOrUpdateNote(noteObj)
						.then(function(response){
							return response;
						}).then(function(response){
							//set persisted note object into trainee.note
							$log.debug("setting response note obj to trainee scope note obj")
							$scope.trainees[response.data.trainee.traineeId].note = response.data
						});
					
					}

					/**
					 * **********************************************TODO
					 * REFACTOR**************************************
					 */

					/**
					 * **********************************************TODO
					 * POSSIBLE REFACTOR FOR WEEK
					 * PROBLEM**************************************
					 */
					function pushUnique(arr, item) {
						if (arr.indexOf(item) === -1) {
							arr.push(item);
						}
					}

					/**
					 * **********************************************TODO
					 * POSSIBLE REFACTOR**************************************
					 */
					
					$scope.getTotalAssessmentAvgForWeek = function(assessment,trainees){
						//assessmentTotals will assessment objects, each with properties
						// - total(for total score)
						// - count (for total number of trainees to divide by)
						if($scope.assessmentTotals === undefined) $scope.assessmentTotals=[];
						if($scope.assessmentTotals[assessment.assessmentId] === undefined) $scope.assessmentTotals[assessment.assessmentId] = {};
							
						$scope.assessmentTotals[assessment.assessmentId].total = 0;
						$scope.assessmentTotals[assessment.assessmentId].count = 0;
							for(var traineeKey in trainees){
						//checks if trainee has assessment
								if(trainees[traineeKey].assessments[assessment.assessmentId]){
						//Only increment count and add to total if score is not 0;
									var score = trainees[traineeKey].assessments[assessment.assessmentId].score;
									if(score && score !== 0){ //
										$scope.assessmentTotals[assessment.assessmentId].total+= Number(trainees[traineeKey].assessments[assessment.assessmentId].score);								
										$scope.assessmentTotals[assessment.assessmentId].count +=1;
									}
								}
							}
						return $scope.assessmentTotals[assessment.assessmentId].total / $scope.assessmentTotals[assessment.assessmentId].count ;
					}

					/****************************************************
					 *Save Button **
					 **************************************************/
          
					$scope.showSaving = false;
					$scope.showCheck = false;
					$scope.showFloppy = true;
					$scope.doBurrito =function(){
							$scope.showFloppy = false
							$timeout(function(){
								$scope.showSaving=true;								
							},480).then(function(){
								$timeout(function(){
									$scope.showSaving=false;
								}, 1000).then(function(){
									$scope.showCheck = true;
									$timeout(function(){
										$scope.showCheck = false;
									},2000).then(function(){
										$scope.showFloppy = true;
									});								
								});
							});
					}
					
					$scope.stopBurrito = function(traineeId){
						$scope.trainees[traineeId].burrito=false;
					}
					
					$scope.reloadController = function() {
			            $state.reload();
			        };

			        $rootScope.$on('trainerasses',function(){
						$scope.trainees={};						
						
						for(trainee of $scope.currentBatch.trainees){
							$scope.assignTraineeScope(trainee.traineeId);
						}
						
						getAllAssessmentsForWeek(
								$scope.currentBatch.batchId,
								$scope.currentWeek);
					});
					
			        $rootScope.$on('GET_TRAINEE_OVERALL_CTRL',function(event,traineeId){
						$log.debug(traineeId);
					});
			        
					// Used to sort trainees in batch
					function compare(a, b) {
						if (a.name < b.name)
							return -1;
						if (a.name > b.name)
							return 1;
						return 0;
					}
				/******************
				 * UPDATE ASSESSMENT 
				 *****************/
					$scope.updateAssessment = function(assessment,event,modalId,index){
						event.stopPropagation();
						if($scope.updateAssessmentModel !==undefined){
							$log.debug(index);
							//$log.debug($scope.currentAssessments[$index] + "  ------ " + $index);
							if($scope.updateAssessmentModel.category){
								assessment.category=$scope.updateAssessmentModel.category;
							}
							if($scope.updateAssessmentModel.type){
								assessment.type=$scope.updateAssessmentModel.type;
							}
							if($scope.updateAssessmentModel.rawScore){
								assessment.rawScore=$scope.updateAssessmentModel.rawScore;
							}
							//call delegate if at least one field was changed
							if($scope.updateAssessmentModel.category || $scope.updateAssessmentModel.type || $scope.updateAssessmentModel.rawScore){
								caliberDelegate.trainer.updateAssessment(assessment)
								.then(function(response){
									$log.debug("the assessment has been updated")
									return response;
								}).then(function(){
										$('.modal').modal('hide');
										$scope.currentAssessments[index] = response;
										$log.debug($scope.currentBatch.batchId, $scope.currentWeek);
										getAllAssessmentsForWeek($scope.currentBatch.batchId, $scope.currentWeek);									
								});
							}
						}
						$('.modal').modal('hide');
					}
					
				
				
				$scope.closeModal = function(str){
				    $('#'+str).modal('toggle');
				}

//				$scope.updateAssessment={};
				
				$scope.deleteAssessment = function(assessment,event,modalId,index){
					$log.debug("im deleting an assessment" + $scope.currentAssessments);
					caliberDelegate.trainer.deleteAssessment($scope.currentAssessments[index].assessmentId)
					.then(function(response){
									$log.debug("im deleting assessment");
									if(response){
										$('.modal').modal('hide');
										//$scope.currentAssessments[index] = response;
										//$log.debug($scope.currentBatch.batchId, $scope.currentWeek);
										getAllAssessmentsForWeek($scope.currentBatch.batchId, $scope.currentWeek);									
									}
									
									return response;
					});
					
				};
				
				});
