package com.revature.caliber.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.NoteType;
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

	// Radar Chart of the Independent Skills/Technologies of Trainees
	// Batch > Week > Trainee
	public Map<String, Double> getRadar(Integer batchId, Integer weekNumber, Integer traineeId) {
		Map<String, Double> results = new HashMap<>();
		Batch batch = batchDAO.findOne(batchId);

		return null;
	}

	// Pie chart displaying number of trainees that recieved red, yellow, green,
	// or superstar
	// returns Map relating the number of trainees per QCStatus
	public Map<QCStatus, Integer> batchWeekPieChart(Integer batchId, Integer weekNumber) {

		List<Trainee> trainees = traineeDAO.findAllByBatch(batchId);
		Map<QCStatus, Integer> results = new HashMap<>();
		for (QCStatus s : QCStatus.values()) {
			results.put(s, 0);
		}
		for (Trainee t : trainees) {
			for (Note n : t.getNotes()) {
				if (n.getWeek() == weekNumber) {
					if (n.getType() == NoteType.QC_TRAINEE) {
						QCStatus status = n.getQcStatus();
						Integer temp = results.get(status);
						temp++;
						results.remove(status);
						results.put(status, temp);
					}
				}
			}
		}

		return results;
	}

	//public Map<Integer, Double> lineCharAVG(int batchId, int week, int traineeId) {
	public Map<Integer, Double> lineCharAVG(int batchId, int week, int traineeId) {
		List<Trainee> trainees = traineeDAO.findAllByBatch(batchId);
		Map<Integer, Double> data = new HashMap<Integer, Double>();
		Trainee trainee = traineeDAO.findOne(traineeId);
		for(int  w= 0;w<=week;w++){
			data.put(w,getWeightedAverageGradesOfTraineeByWeek(trainee, w)
					.get(trainee.getGrades().iterator().next().getAssessment().getTitle()));
		}

		return data;
	}

	public Map<Trainee, Double> barCharAVG(int batchId, int week) {
		List<Trainee> trainees = traineeDAO.findAllByBatch(batchId);
		Map<Trainee, Double> data = new HashMap<Trainee, Double>();
		for (Trainee t : trainees) {
			data.put(t, getWeightedAverageGradesOfTraineeByWeek(t, week)
					.get(t.getGrades().iterator().next().getAssessment().getTitle()));
		}

		return data;
	}

	public Map<Integer, Double> findAvgGradeByWeek(int traineeId) {

		Trainee trainee = traineeDAO.findOne(traineeId);

		Map<Integer, Double> data = new HashMap<Integer, Double>();
		int weeks = trainee.getBatch().getWeeks();

		for (int week = 0; week < weeks; week++) {

			data.put(week, getWeightedAverageGradesOfTraineeByWeek(trainee, week)
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
	public Map<String, Double> getWeightedAverageGradesOfTraineeByWeek(Trainee trainee, Integer week) {
		Map<String, Double> results = new HashMap<>();
		Set<Grade> gradesForTheWeek = trainee.getGrades().stream().filter(el -> el.getAssessment().getWeek() == week)
				.collect(Collectors.toSet());
		Double totalRawScore = gradesForTheWeek.stream().mapToDouble(el -> el.getAssessment().getRawScore()).sum();
		for (Grade grade : gradesForTheWeek) {
			results.put(grade.getAssessment().getTitle(),
					grade.getScore() * grade.getAssessment().getRawScore() / totalRawScore);
		}
		return results;
	}

}