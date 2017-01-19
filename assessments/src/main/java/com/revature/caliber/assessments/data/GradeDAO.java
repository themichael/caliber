package com.revature.caliber.assessments.data;

import java.util.List;
import com.revature.caliber.assessments.beans.Grade;

public interface GradeDAO {

	/**
	 * Returns a list of all grade entries
	 */
	List<Grade> getAllGrades();

	/**
	 * Returns a grade object given a specific gradeId
	 */
	Grade getGradeByGradeId(Integer gradeId);

	/**
	 * Returns a list of grades of a specific trainee based on traineeId as an
	 * input
	 * 
	 * @param traineeId
	 */
	List<Grade> getGradesByTraineeId(Integer traineeId);

	/**
	 * Returns a list of grades of a specific assessment based on assessmentId
	 * as an input
	 * 
	 * @param assessmentId
	 */
	List<Grade> getGradesByAssesessment(Integer assessmentId);

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