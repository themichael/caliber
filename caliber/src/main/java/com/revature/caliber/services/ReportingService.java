package com.revature.caliber.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.AssessmentType;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.QCStatus;
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
public class ReportingService {
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

	// Pie chart displaying number of trainees that recieved red, yellow, green,
	// or superstar
	// returns Map relating the number of trainees per QCStatus
	public Map<QCStatus, Integer> batchWeekPieChart(Integer batchId, Integer weekNumber) {

		List<Note> notes = noteDAO.findQCBatchNotes(batchId, weekNumber);
		Map<QCStatus, Integer> results = new HashMap<>();
		for (QCStatus s : QCStatus.values()) {
			results.put(s, 0);
		}
		for (Note n : notes) {
			QCStatus status = n.getQcStatus();
			Integer temp = results.get(status);
			temp++;
			results.remove(status);
			results.put(status, temp);
			}
		return results;
	}

	// public Map<Integer, Double> lineCharAVG(int batchId, int week, int
	// traineeId) {
	public Map<Integer, Double> lineCharAVG(int batchId, int week, int traineeId) {
		List<Trainee> trainees = traineeDAO.findAllByBatch(batchId);
		Map<Integer, Double> data = new HashMap<Integer, Double>();
		Trainee trainee = traineeDAO.findOne(traineeId);
		for (int w = 0; w <= week; w++) {
			data.put(w, getWeightedAverageGradesOfTraineeByWeek(traineeId, w)
					.get(trainee.getGrades().iterator().next().getAssessment().getTitle()));
		}

		return data;
	}

	public Map<Trainee, Double> barCharAVG(int batchId, int week) {
		List<Trainee> trainees = traineeDAO.findAllByBatch(batchId);
		Map<Trainee, Double> data = new HashMap<Trainee, Double>();
		for (Trainee t : trainees) {
			data.put(t, getWeightedAverageGradesOfTraineeByWeek(t.getTraineeId(), week)
					.get(t.getGrades().iterator().next().getAssessment().getTitle()));
		}

		return data;
	}

	public Map<Integer, Double> findAvgGradeByWeek(int traineeId) {

		Trainee trainee = traineeDAO.findOne(traineeId);

		Map<Integer, Double> data = new HashMap<Integer, Double>();
		int weeks = trainee.getBatch().getWeeks();

		for (int week = 0; week < weeks; week++) {

			data.put(week, getWeightedAverageGradesOfTraineeByWeek(traineeId, week)
					.get(trainee.getGrades().iterator().next().getAssessment().getTitle()));

		}

		return data;

	}

	/**
	 * Weighted Average of a Trainee's Grade Scores for a given week number
	 * 
	 * @param trainee
	 *            For which to get the Average Scores
	 * @param week
	 *            number to get the grades for
	 * @return A Map of String Names of Assessment Types, and
	 */
	public Map<String, Double[]> getWeightedAverageGradesOfTraineeByWeek2(Integer traineeId, Integer week) {
		Map<String, Double[]> results = new HashMap<>();
		List<Grade> allGrades = gradeDAO.findByTrainee(traineeId);
		List<Grade> gradesForTheWeek = allGrades.stream().filter(el -> el.getAssessment().getWeek() == week)
				.collect(Collectors.toList());
		Double totalRawScore = gradesForTheWeek.stream().mapToDouble(el -> el.getAssessment().getRawScore()).sum();
		for (Grade grade : gradesForTheWeek) {
			Double[] temp = new Double[2];
			temp[0] = grade.getAssessment().getRawScore() / totalRawScore;
			temp[1] = grade.getScore() * grade.getAssessment().getRawScore() / totalRawScore;
			results.put(grade.getAssessment().getType().name(), temp);
		}
		return results;
	}

	public Map<String, Double> getWeightedAverageGradesOfTraineeByWeek(Integer traineeId, Integer week) {
		Map<String, Double> results = new HashMap<>();
		List<Grade> allGrades = gradeDAO.findByTrainee(traineeId);
		List<Grade> gradesForTheWeek = allGrades.stream().filter(el -> el.getAssessment().getWeek() == week)
				.collect(Collectors.toList());
		Double totalRawScore = gradesForTheWeek.stream().mapToDouble(el -> el.getAssessment().getRawScore()).sum();
		for (Grade grade : gradesForTheWeek) {
			results.put(grade.getAssessment().getType().name(),
					grade.getScore() * grade.getAssessment().getRawScore() / totalRawScore);
		}
		return results;
	}

	/**
	 * Gets the average for a given Trainee ID for the entire week for one
	 * particular assessment.
	 * 
	 * @param traineeId
	 * @param week
	 * @param assessmentType
	 * @return
	 */
	public Double[] getAvgTraineeWeek(Integer traineeId, Integer week, AssessmentType assessmentType) {
		List<Grade> allGrade = gradeDAO.findByTrainee(traineeId);
		List<Grade> gradesForTheWeek = allGrade.stream()
				.filter(el -> el.getAssessment().getWeek() == week
						&& el.getAssessment().getType().name() == assessmentType.toString())
				.collect(Collectors.toList());
		Double totalRawScore = gradesForTheWeek.stream().mapToDouble(el -> el.getAssessment().getRawScore()).sum();
		Double[] result = new Double[2];
		for (Grade grade : gradesForTheWeek) {
			result[0] += (grade.getScore() * grade.getAssessment().getRawScore() / totalRawScore);
			result[1] += grade.getAssessment().getRawScore();
		}
		result[1] = result[1] / totalRawScore;
		return result;
	}

	/**
	 * Gets the average for a given Batch ID for the entire week for one
	 * particular assessment.
	 * 
	 * @param batchId
	 * @param week
	 * @param assessmentType
	 * @return
	 */

	public Map<Trainee, Double[]> getAvgBatchWeek(Integer batchId, Integer week,  AssessmentType assessmentType) {
		Map<Trainee, Double[]> results = new HashMap<>(); 
		List<Trainee> trainees = traineeDAO.findAllByBatch(batchId);
	
		
		
		
		return null;
	}

	/**
	 * Get Weighted Average for a single assessment type for a given Trainee ID,
	 * returning weeks as keys in the map and the corresponding values of
	 * weight(%) and scores.
	 * 
	 * @param traineeId
	 * @param assessmentType
	 * @return Map<'week', {'score', 'weight'}>
	 */
	public Map<Integer, Double[]> getAvgTraineeOverall(Integer traineeId, AssessmentType assessmentType) {

		Map<Integer, Double[]> results = new HashMap<>();
		List<Grade> grades = gradeDAO.findByTrainee(traineeId);
		List<Grade> assessments = grades.stream()
										.filter(g -> g.getAssessment().getType() == assessmentType)
										.collect(Collectors.toList());
		Double totalRawScore = assessments.stream().mapToDouble(g -> g.getAssessment().getRawScore()).sum();
		for (Grade assessment : assessments){
			Double weight = assessment.getAssessment().getRawScore() / totalRawScore;
			results.put(Integer.valueOf(assessment.getAssessment().getWeek()), new Double[]{(assessment.getScore()*weight), weight} );
		}
		
		return results;
	}

	/**
	 * Get Weighted Average for a the whole batch for all the weeks per an assessment
	 * @param batchId
	 * @param assessmentType
	 * @return Trainee: The Trainee, Double[]: 0: Score, 1: Weight, 2: Week
	*/
	public Map<Trainee, Double[]> getAvgBatchOverall(Integer batchId, AssessmentType assessmentType) {
		Map<Trainee, Double[]> results = new HashMap<>();
		List<Grade> grades = gradeDAO.findByBatch(batchId);
		List<Grade> assessments = grades.stream()
										.filter(g -> g.getAssessment().getType() == assessmentType)
										.collect(Collectors.toList());
		Double totalRawScore = assessments.stream().mapToDouble(g -> g.getAssessment().getRawScore()).sum();
		for (Grade assessment : assessments){
			Double weight = assessment.getAssessment().getRawScore() / totalRawScore;
			results.put(assessment.getTrainee(), new Double[]{(assessment.getScore()*weight), weight, Double.valueOf(assessment.getAssessment().getWeek())} );
		}
		return results;
	}
  
}
