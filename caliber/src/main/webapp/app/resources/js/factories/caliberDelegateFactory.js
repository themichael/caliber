/**
 * Delegates API calls to the appropriate API factory
 */
// TODO: remove deleted ajax functions and add correct ajax functions
// TODO: change name of file to apiFactory
angular.module("delegate").factory("caliberDelegate",
		function($log, trainerFactory, vpFactory, qcFactory, allFactory) {
	$log.debug("Booted Delegate Factory");
	var delegate = {};

	delegate.all = {};
	delegate.trainer = {};
	delegate.qc = {};
	delegate.vp = {};

	/**************************** All *****************************/
	delegate.all.createBatch = function(batchObj){
		return allFactory.createBatch(batchObj);
	};

	delegate.all.updateBatch = function(batchObj){
		return allFactory.updateBatch(batchObj);
	};

	delegate.all.deleteBatch = function(batchId){
		return allFactory.deleteBatch(batchId);
	};

	delegate.all.createTrainee = function(traineeObj){
		return allFactory.createTrainee(traineeObj);
	};

	delegate.all.updateTrainee = function(traineeObj){
		return allFactory.updateTrainee(traineeObj);
	};

	delegate.all.deleteTrainee = function(traineeId){
		return allFactory.deleteTrainee(traineeId);
	};

	/************************* Trainer ****************************/
	delegate.trainer.getAllBatches = function() {
		return trainerFactory.getAllBatches();
	};

	delegate.trainer.createWeek = function(weekObj){
		return trainerFactory.createWeek(weekObj);
	};

	delegate.trainer.addGrade = function(gradeObj){
		return trainerFactory.addGrade(gradeObj);
	};

	delegate.trainer.updateGrade = function(gradeObj){
		return trainerFactory.updateGrade(gradeObj);
	};

	delegate.trainer.createAssessment = function(assessmentObj){
		return trainerFactory.createAssessment(assessmentObj);
	};

	delegate.trainer.getAllAssessments = function(weekId){
		return trainerFactory.getAllAssessments(weekId);
	};

	delegate.trainer.updateAssessment = function(assessmentObj){
		return trainerFactory.updateAssessment(assessmentObj);
	};

	delegate.trainer.deleteAssessment = function(assessmentId){
		return trainerFactory.deleteAssessment(assessmentId);
	};

	delegate.trainer.createNote = function(noteObj){
		return trainerFactory.createNote(noteObj);
	};

	delegate.trainer.updateNote = function(noteObj){
		return trainerFactory.updateNote(noteObj);
	};

	/*************************** Batch ***************************/
	delegate.vp.getAllBatches = function() {
		return vpFactory.getAllBatches();
	};

	delegate.vp.getAllCurrentBatches = function() {
		return vpFactory.getAllCurrentBatches();
	};

	delegate.vp.getBatch = function(id){
		return vpFactory.getBatch(id);
	};

	delegate.vp.getCurrentBatch = function(id){
		return vpFactory.getCurrentBatch(id);
	};

	// QC API
	delegate.qc.getAllBatches = function () {
		return qcFactory.getAllBatches();
	};

	delegate.qc.getBatchById = function (id) {
		return qcFactory.getBatchById(id);
	};

	return delegate;
});
