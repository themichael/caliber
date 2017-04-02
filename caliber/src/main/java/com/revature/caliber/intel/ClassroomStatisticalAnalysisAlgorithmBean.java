package com.revature.caliber.intel;

import java.util.Map;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.Trainee;

/**
 * Defines the various algorithms required by the Reporting Service.
 * Handles the complexity of measuring grades and statistical analyses.
 * 
 * All methods must return grades as a percentage (%) of total raw score.
 * 
 * @author Patrick Walsh
 *
 */
public interface ClassroomStatisticalAnalysisAlgorithmBean {

	
	/**
	 * Returns the average grade for every trainee.
	 * Great for ranking trainees for benchmarking.
	 * Returns grades as a percentage (%) of total raw score.
	 * @return Key: Trainee, Value: Average calculated score of all assessments
	 */
	public Map<Trainee, Double> findAvgGradesForEachTrainee();

	/**
	 * Returns the average grade for each assessment.
	 * Useful for displaying on reports and charts.
	 * Returns grades as a percentage (%) of total raw score.
	 * @return Key: Assessment, Value:  Average calculated score of each assessment
	 */
	public Map<Assessment, Double> findAvgGradesForEachAssessment();

	/**
	 * Returns the average grades for a trainee by category. 
	 * Used for the radar chart on trainee profile.
	 * Returns grades as a percentage (%) of total raw score.
	 * @param traineeId
	 * @return Key: Category, Value: Average calculated score of all assessments in that category
	 */
	public Map<Category, Double> findAvgGradeByCategory(int traineeId);

	/**
	 * Returns the average grade for a trainee each week.
	 * Returns grades as a percentage (%) of total raw score.
	 * @param traineeId
	 * @return
	 */
	public Map<Integer, Double> findAvgGradeByWeek(int traineeId);
	
}
