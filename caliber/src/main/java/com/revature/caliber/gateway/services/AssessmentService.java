package com.revature.caliber.gateway.services;

import com.revature.caliber.beans.*;

import java.util.List;
import java.util.Set;

public interface AssessmentService {

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

	/**
	 * Inserts Assessment
	 * @param assessment an Assessment to be inserted
	 */
	long insertAssessment(Assessment assessment);

	/**
	 * Updates Assessment
	 * 
	 * @param assessment an Assessment to be updated
	 */
	void updateAssessment(Assessment assessment);

	/**
	 * Deletes Assessment
	 * 
	 * @param assessment and Assessment to delete
	 */
	void deleteAssessment(Assessment assessment);

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


	/**
	 * Inserts a new Grade into database
	 */
	void insertGrade(Grade grade);

	/**
	 * Deletes a grade
	 */
	void deleteGrade(Grade grade);

	/**
	 * Updates a grade
	 */
	void updateGrade(Grade grade);

	/**
	 * Create a batchNote
	 */
	void makeBatchNote(BatchNote batchNote);

	/**
	 * Get the batch note within a given week corresponding to a specific batch
	 */
	BatchNote weeklyBatchNote(int batchId, int weekId);

	/**
	 * Get a list of all BatchNotes within a given week provided that multiple
	 * batches are training simultaneously
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

	void createQCNote(QCNote note);

	QCNote getQCNoteById(Integer qcNoteId);

	QCNote getQCNoteForTraineeWeek(Integer traineeId, Integer weekId);

	List<QCNote> getQCNotesByTrainee(Integer traineeId);

	List<QCNote> getQCNotesByWeek(Integer weekId);

	void updateQCNote(QCNote note);

	void deleteQCNote(QCNote note);

	void createTrainerNote(TrainerNote note);

	TrainerNote getTrainerNoteById(Integer trainerNoteId);

	TrainerNote getTrainerNoteForTrainerWeek(Integer trainerId, Integer weekId);

	Set<TrainerNote> getTrainerNotesByTrainer(Integer trainerId);

	Set<TrainerNote> getTrainerNotesByWeek(Integer weekId);

	void updateTrainerNote(TrainerNote note);

	void deleteTrainerNote(TrainerNote note);

	/**
	 * 
	 * Return a single note
	 * 
	 */
	public Note getNote(String note);

	/**
	 * 
	 * Return a list of all notes
	 * 
	 */
	public List<Note> getAllNotes();

	/**
	 * Returns Set of all Categories
	 * 
	 * @return Set of all Categories
	 */
	Set<Category> getAllCategories();

	/**
	 * Returns Category with provided ID
	 * 
	 * @param id
	 * @return Category
	 */
	Category getCategoryById(int id);
}
