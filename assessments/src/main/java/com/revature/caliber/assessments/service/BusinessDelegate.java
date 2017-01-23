package com.revature.caliber.assessments.service;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.beans.Grade;

import java.util.List;
import java.util.Set;

public interface BusinessDelegate {

//    Assessment
    Set<Assessment> getAllAssessments();

    Assessment getAssessmentById(int id);

    Set<Assessment> getAssessmentsByWeekId(int id);

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
 	List<Grade> getGradesByAssesessment(long assessmentId);

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
}
