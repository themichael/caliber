package com.revature.caliber.assessments.service;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.beans.Grade;
import com.revature.caliber.assessments.beans.QCNote;

import java.util.List;
import java.util.Set;

public interface BusinessDelegate {

//    Assessment
    Set<Assessment> getAllAssessments();

    Assessment getAssessmentById(int id);

    /*   
     * 	TODO reconsider how to approach this implementation.
     * 		 data resides in another service, so you cannot query this way
    Set<Assessment> getAssessmentsByTrainerId(int id);
    */

    Set<Assessment> getAssessmentsByWeekId(int id);

    Set<Assessment> getAssessmentsByBatchId(int id);

    void insertAssessment(Assessment assessment);

    void updateAssessment(Assessment assessment);

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
 	Grade getGradeByGradeId(int gradeId);

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
 	List<Grade> getGradesByAssesessment(int assessmentId);

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

	//QCNote
	void createQCNote(QCNote note);
	QCNote getQCNoteById(Integer QCNoteId);
	QCNote getQCNoteForTraineeWeek(Integer traineeId, Integer weekId);
	List<QCNote> getQCNotesByTrainee(Integer traineeId);
	List<QCNote> getQCNotesByWeek(Integer weekId);
	//QCNote end
}
