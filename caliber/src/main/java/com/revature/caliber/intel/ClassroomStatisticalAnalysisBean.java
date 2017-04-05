package com.revature.caliber.intel;

import java.util.Map;

import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.beans.Trainee;

/**
 * Defines the various algorithms required by the Reporting Service. Handles the
 * complexity of measuring grades and statistical analyses.
 * 
 * All methods must return grades as a percentage (%) of total raw score.
 * 
 * @author Patrick Walsh
 *
 */
public interface ClassroomStatisticalAnalysisBean {

	/**
	 * Radar Chart of the Independent Skills/Technologies of Trainees
	 * Batch > Week > Trainee
	 * @param batchId
	 * @param weekNumber
	 * @param traineeId
	 * @return
	 */
	public Map<String, Double> getRadar(Integer batchId, Integer weekNumber, Integer traineeId);
	
	/**
	 * Pie chart displaying number of trainees that recieved red, yellow, green, or superstar
	 * returns Map relating the number of trainees per QCStatus
	 * @param batchId
	 * @param weekNumber
	 * @return
	 */
	public Map<QCStatus, Integer> batchWeekPieChart(Integer batchId, Integer weekNumber);
	
	/**
	 * TODO comment
	 * @param traineeId
	 * @return
	 */
	public Map<Integer, Double> findAvgGradeByWeek(int traineeId);
	
	/**
	 * Weighted Average of a Trainee's Grade Scores for a given week number 
	 * @param trainee For which to get the Average Scores
	 * @param week number to get the grades for 
	 * @return A Map of String Names of Assessment Types, and 
	 */
	public  Map<String, Double> getWeightedAverageGradesOfTraineeByWeek(Trainee trainee, Integer week);
	
}
