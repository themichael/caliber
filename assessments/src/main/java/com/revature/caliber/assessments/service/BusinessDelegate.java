package com.revature.caliber.assessments.service;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.beans.BatchNote;
import com.revature.caliber.assessments.beans.Grade;
import com.revature.caliber.assessments.beans.TrainerNote;
import com.revature.caliber.assessments.beans.QCNote;

import java.util.List;
import java.util.Set;

public interface BusinessDelegate {

//    Assessment
	//    Get
	/**
	 * Returns a Set of all Assessments
	 * @return a Set of Assessments
	 */
    Set<Assessment> getAllAssessments();

	/**
	 * Return Assessment with AssessmentId
	 * @return an Assessment
	 */
    Assessment getAssessmentById(long id);

	/**
	 * Returns HashSet of Assessments with WeekId
	 * @param id the Week ID
	 * @return a Set of Assessments
	 */
    Set<Assessment> getAssessmentsByWeekId(long id);

	//    Create
	/**
	 * Inserts Assessment
	 * @param assessment an Assessment to be inserted
	 */
    long insertAssessment(Assessment assessment);

	//    Update
	/**
	 * Updates Assessment
	 * @param assessment an Assessment to be updated
	 */
    void updateAssessment(Assessment assessment);

	//    Delete
	/**
	 * Deletes Assessment
	 * @param assessment and Assessment to delete
	 */
    void deleteAssessment(Assessment assessment);

//    Batch
    
 // Grade
 	/**
 	 * Returns a list of all grade entries
 	 */
 	List<Grade> getAllGrades();

 	/**
 	 * Returns a grade object given a specific gradeId
 	 */
 	Grade getGradeByGradeId(long gradeId);

 	/**
 	 * Returns a list of grades of a specific trainee based on traineeId as an
 	 * input
 	 * 
 	 * @param traineeId
 	 */
 	List<Grade> getGradesByTraineeId(int traineeId);

 	/**
 	 * Returns a list of grades of a specific assessment based on assessmentId
 	 * as an input
 	 * 
 	 * @param assessmentId
 	 */
 	List<Grade> getGradesByAssessment(long assessmentId);

 	// Insert
 	/**
 	 * Inserts a new Grade into database
 	 */
 	void insertGrade(Grade grade);

 	// Delete
 	/**
 	 * Deletes a grade
 	 */
 	void deleteGrade(Grade grade);

 	// Update
 	/**
 	 * Updates a grade
 	 */
 	void updateGrade(Grade grade);

 	//TrainerNotes
	void createTrainerNote(TrainerNote note);
	TrainerNote getTrainerNoteById(Integer trainerNoteId);
	TrainerNote getTrainerNoteForTrainerWeek(Integer trainerId, Integer weekId);
	Set<TrainerNote> getTrainerNotesByTrainer(Integer trainerId);
	Set<TrainerNote> getTrainerNotesByWeek(Integer weekId);
	void updateTrainerNote(TrainerNote note);
	void deleteTrainerNote(TrainerNote note);

	//QCNote
	void createQCNote(QCNote note);
	QCNote getQCNoteById(Integer QCNoteId);
	QCNote getQCNoteForTraineeWeek(Integer traineeId, Integer weekId);
	List<QCNote> getQCNotesByTrainee(Integer traineeId);
	List<QCNote> getQCNotesByWeek(Integer weekId);
	void updateQCNote(QCNote note);
	void deleteQCNote(QCNote note);
	//QCNote end
	
	//Batch Note
	
	/**
	 * Create a batchNote 
	 */
	void makeBatchNote(BatchNote batchNote);
	
	/**
	 * Get the batch note within a given week corresponding to a specific batch 
	 */
	BatchNote weeklyBatchNote(int batchId, int weekId);	
	
	/**
	 * Get a list of all BatchNotes within a given week
	 * provided that multiple batches are training simultaneously
	 */
	List<BatchNote> allBatchNotesInWeek(int weekId);
	

	/**
	 * Get a list of all BatchNotes for a particular Batch
	 */
	List<BatchNote> allBatchNotes(int batchId);
	
	/**
	 * Update a BatchNote  
	 */
	void updateBatchNote(BatchNote batchNote);
	
	/**
	 * Delete a BatchNote  
	 */
	void deleteBatchNote(BatchNote batchNote);
	
	
}
