/*******************************************************************************
 * Team: SDAR Team Lead: Roderick Dye Authors: Roderick Dye, Stanley Chouloute,
 * Daniel Zorrilla, Adam Baker
 * 
 * Daniel worked on viewing all trainers, Stanley worked on adding trainers,
 * Roderick worked on editing trainers, Adam worked on delete functionality for
 * trainers.
 * ******************************************************************************
 */

angular
		.module("vp")
		.controller(
				"vpTrainerController",
				function($scope, $log, caliberDelegate) {
					$log.debug("Booted trainer manage controller.");
					$log.debug('test trainermanager controller -j');
					/**
					 * ************************** Batch
					 * ****************************
					 */

					/** On page start --> load all trainers * */

					$scope.loadAllTrainers = function() {
						caliberDelegate.all.getAllTrainers().then(
								function(trainers) {
									$log.debug(trainers);
									$scope.allTrainers = trainers;
								});
					};
					
					

					var submitTier = function(tier) {
						var pre = "ROLE_"
						return pre.concat(tier);
					};

					/**
					 * *********************************************** Code to
					 * create and update Trainer************
					 */

					// load training tiers
					caliberDelegate.all.enumTrainerTier().then(function(tiers) {
						$log.debug(tiers);
						var filteredTiers = tiers.filter(function(ary) {
							return ary !== 'ROLE_INACTIVE'
						});
						for (var i = 0; i < filteredTiers.length; i++) {
							filteredTiers[i] = filteredTiers[i].substr(5);
						}
						$scope.trainerTiers = filteredTiers;
					});

					// load trainers titles
					caliberDelegate.vp.trainersTitles().then(function(titles) {
						$log.debug(titles);
						$scope.trainersTitles = titles;
					});

					/** Save email verification modal* */
					$scope.checkTrainerEmail = function(trainerForm) {
						caliberDelegate.vp
								.getTrainerEmail(trainerForm.email)
								.then(
										function(response) {
											$log.debug(response)
											if (response.data === "") {
												$scope.saveTrainer(trainerForm);
											} else {
												$log.debug(response)
												angular
														.element(
																"#trainerEmailVerificationModal")
														.modal("show");
												return false;
											}
										})
					};

					/** Save New Trainer Input * */
					$scope.saveTrainer = function(trainerForm) {
						var newTrainer = trainerForm;
						createTrainerObject(newTrainer);
						newTrainer.tier = submitTier(newTrainer.tier);
						caliberDelegate.vp.createTrainer(newTrainer).then(
								function(response) {
									$log.debug("trainer added: " + response);
									$scope.loadAllTrainers();
								});
						angular.element("#createTrainerModal").modal("hide");
					};

					/** Create new Trainer Object * */
					function createTrainerObject(trainer) {
						trainer = $scope.trainerForm;
						$log.debug(trainer);
					}
					;

					/** Create scopes for trainer form* */
					$scope.trainerForm = {
						trainerId : null,
						name : null,
						email : null,
						title : null,
						tier : null
					};

					/** Resets trainer form* */
					$scope.resetTrainerForm = function() {
						$scope.trainerForm.trainerId = "";
						$scope.trainerForm.name = "";
						$scope.trainerForm.email = "";
						$scope.trainerForm.title = "";
						$scope.trainerForm.tier = "";
						$scope.Save = "Save";
					};

					/** Fill update form with trainer's previous data */
					$scope.populateTrainer = function(index) {
						$log.debug($scope.allTrainers[index]);
						$scope.trainerForm.trainerId = $scope.allTrainers[index].trainerId;
						$scope.trainerForm.name = $scope.allTrainers[index].name;
						$scope.trainerForm.email = $scope.allTrainers[index].email;
						$scope.trainerForm.title = $scope.allTrainers[index].title;
						$scope.trainerForm.tier = $scope.allTrainers[index].tier
								.substr(5);
						$scope.Save = "Update";
						
						// load tasks corresponding to trainer
						caliberDelegate.all.getAllTasksByTrainerId($scope.trainerForm.trainerId).then(
								function(t_tasks){
										caliberDelegate.all.getAllActiveTasks().then(
											function(tasks){
												$log.debug(tasks);
												for(var i = 0; i < t_tasks.length; i++){
													num = tasks.findIndex(j => j.id === t_tasks[i].taskCompleted.id);
													if(num > -1){
														tasks.splice(num, 1);
													}
												}
												$scope.allActiveTasks = tasks;
										});
								});
					};

					/** Update Trainer Input * */
					$scope.updateTrainer = function() {
						$scope.trainerForm.tier = submitTier($scope.trainerForm.tier);
						caliberDelegate.vp.updateTrainer($scope.trainerForm)
								.then(function(response) {
									$log.debug("trainer updated: " + response);
									$scope.loadAllTrainers();
								});
						angular.element("#editTrainerModal").modal("hide");
					};

					/**
					 * Adam Baker deactivation function
					 */
					$scope.makeInactive = function() {
						$log.debug($scope.trainerForm);
						$scope.trainerForm.tier = "ROLE_INACTIVE";
						caliberDelegate.vp
								.deactivateTrainer($scope.trainerForm).then(
										function() {
											$log.debug("trainer deactivated");
											$scope.loadAllTrainers();
										});
						angular.element("#deleteTrainerModal").modal("hide");
					};
					
					$scope.addTask = false;
					$scope.openAddTask = function(){
						$scope.addTask = true;
					}
					
					// function creates a new active task, and persists it to
					// the db
					$scope.saveTask = function(task, priority){
						var newTask = {"active":1, "description":task, "priority":priority};
						console.log(newTask);
						caliberDelegate.vp.saveOrUpdateTask(newTask)
						.then(
								function(response) {
									$log.debug("Task Created: "
											+ response);
								})
						//gets all of the tasks
						.then( caliberDelegate.all.getAllTasksByTrainerId($scope.trainerForm.trainerId).then(
								function(t_tasks){
										caliberDelegate.all.getAllActiveTasks().then(
											function(tasks){
												$log.debug(tasks);
												for(var i = 0; i < t_tasks.length; i++){
													num = tasks.findIndex(j => j.id === t_tasks[i].taskCompleted.id);
													if(num > -1){
														tasks.splice(num, 1);
													}
												}
												$scope.allActiveTasks = tasks;
										});
								})
						);
						$scope.addTask = false;

					}
					
					// function closes add task form if the user clicks on x
					$scope.closeAddTask = function(){
						$scope.addTask = false;
					}

				});
