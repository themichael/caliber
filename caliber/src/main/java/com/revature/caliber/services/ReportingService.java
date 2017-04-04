package com.revature.caliber.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.AssessmentDAO;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.data.NoteDAO;
import com.revature.caliber.data.TraineeDAO;

/**
 * Exclusively used to generate data for charts
 * 
 * Provides logic concerning grade and aggregated data sets. Application logic
 * has no business being in a DAO nor in a Controller. This is the ideal place
 * for calculations
 * 
 * @author Patrick Walsh
 *
 */
@Service
public class ReportingService
{
	private final static Logger log = Logger.getLogger(ReportingService.class);
	private GradeDAO gradeDAO;
	private BatchDAO batchDAO;
	private TraineeDAO traineeDAO;
	private NoteDAO noteDAO;
	private AssessmentDAO assessmentDAO;
	
	@Autowired
	public void setGradeDAO(GradeDAO gradeDAO) {
		this.gradeDAO = gradeDAO;
	}

	@Autowired
	public void setBatchDAO(BatchDAO batchDAO) {
		this.batchDAO = batchDAO;
	}

	@Autowired
	public void setTraineeDAO(TraineeDAO traineeDAO) {
		this.traineeDAO = traineeDAO;
	}

	@Autowired
	public void setNoteDAO(NoteDAO noteDAO) {
		this.noteDAO = noteDAO;
	}

	@Autowired
	public void setAssessmentDAO(AssessmentDAO assessmentDAO) {
		this.assessmentDAO = assessmentDAO;
	}

	// Radar Chart of the Independent Skills/Technologies of Trainees
	// Batch > Week > Trainee
	public Map<String, Double> getRadar(Integer batchId, Integer week, Integer traineeId){
		Map<String, Double> results = new HashMap<>();
		
		
		
		return null;
	}
	
	/**
	 * Weighted Average of a Trainee's Grade Scores for a given week number 
	 * @param trainee For which to get the Average Scores
	 * @param week number to get the grades for 
	 * @return A Map of String Names of Assessment Types, and 
	 */
	public  Map<String, Double[]> getWeightedAverageGradesOfTraineeByWeek(Integer traineeId, Integer week){
		Map<String, Double[]> results = new HashMap<>();
		List<Grade> allGrades = gradeDAO.findByTrainee(traineeId);
		List<Grade> gradesForTheWeek = allGrades.stream().filter(el -> el.getAssessment().getWeek() == week).collect(Collectors.toList());
		Double totalRawScore =  gradesForTheWeek.stream().mapToDouble(el -> el.getAssessment().getRawScore()).sum();
		for(Grade grade: gradesForTheWeek){
			Double[] temp = new Double[2];
			temp[0] = grade.getAssessment().getRawScore() / totalRawScore;
			temp[1] = grade.getScore() * grade.getAssessment().getRawScore() / totalRawScore;
			results.put(grade.getAssessment().getType().name(), temp);
		}
		return results;
	}

	/**
	 * Gets the average for a given Trainee ID for the entire week for one particular assessment.
	 * @param traineeId
	 * @param week
	 * @param assessmentId
	 * @return
	 */
	public Double getAvgTraineeWeek(Integer traineeId, Integer week, Integer assessmentId){
		
		return null;
	}
	
	/**
	 * Gets the average for a given Batch ID for the entire week for one particular assessment.
	 * @param batchId
	 * @param week
	 * @param assessmentId
	 * @return
	 */
	public Map<Trainee, Double[]> getAvgBatchWeek(Integer batchId, Integer week, Integer assessmentId){

		return null;
	}

	/**
	 * Get Weighted Average for a single assessment for a given Trainee ID, returning weeks as keys in the map and the
	 * corresponding values of weight(%) and scores.
	 * 
	 * @param traineeId
	 * @param assessmentId
	 * @return
	 */
	public Map<Integer, Double[]> getAvgTraineeOverall(Integer traineeId, Integer assessmentId) {

		return null;
	}
	
	/**
	 * 
	 * @param batchId
	 * @param assessmentId
	 * @return Trainee: The Trainee, Double[]: 0: Week#, 1: Average Score for that Assessment
	 */
	public Map<Trainee, Double[]> getAvgBatchOverall(Integer batchId, Integer assessmentId){
		
		return null;
	}
	
	
	
	
}