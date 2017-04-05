package com.revature.caliber.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.AssessmentType;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Category;
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
		List<Trainee> trainees = traineeDAO.findAllByBatch(batchId);
		// List<Note> notes = noteDAO.findQCBatchNotes(batchId, weekNumber);
		Map<QCStatus, Integer> results = new HashMap<>();
		for (QCStatus s : QCStatus.values()) {
			results.put(s, 0);
		}
		for (Trainee t : trainees) {
			List<Note> notes = noteDAO.findAllIndividualNotes(t.getTraineeId(), weekNumber);
			for (Note n : notes) {
				if (n.isQcFeedback()) {
					QCStatus status = n.getQcStatus();
					Integer temp = results.get(status);
					temp++;
					results.remove(status);
					results.put(status, temp);
				}
			}
		}
		return results;
	}

	/**
	 * 
	 * @param batchId
	 * @param week
	 * @param traineeId
	 * @return Map<'week', 'avgScore'>
	 */
	public Map<Integer, Double> lineChartAvg(int week, int traineeId) {
		Map<Integer, Double> results = new HashMap<>();
		List<Grade> allGrades = gradeDAO.findByTrainee(traineeId);
		List<Grade> gradesForTheWeek = allGrades.stream().filter(el -> el.getAssessment().getWeek() == week)
				.collect(Collectors.toList());
		Double totalRawScore = gradesForTheWeek.stream().mapToDouble(el -> el.getAssessment().getRawScore()).sum();
		for (Grade grade : gradesForTheWeek) {
			results.put((int) grade.getAssessment().getWeek(),
					grade.getScore() * grade.getAssessment().getRawScore() / totalRawScore);
		}
		return results;
	}



	/**
	 * Gets the average for a given Trainee ID for the entire week for one
	 * particular assessment. One Week -> One Trainee -> Average Score -> One
	 * Assessment Type
	 * 
	 * @param traineeId
	 * @param week
	 * @param assessmentType
	 * @return Double {average trainee score for the week for assessmentType ,
	 *         weight of that assessment type}
	 */
	public Double[] getAvgTraineeWeek(Integer traineeId, Integer week, AssessmentType assessmentType) {
		List<Grade> allGrade = gradeDAO.findByTrainee(traineeId);
		List<Grade> gradesForTheWeek = allGrade.stream().filter(el -> el.getAssessment().getWeek() == week)
				.collect(Collectors.toList());
		Double totalRawScore = gradesForTheWeek.stream().mapToDouble(el -> el.getAssessment().getRawScore()).sum();
		List<Grade> gradesForAssessment = gradesForTheWeek.stream()
				.filter(e -> e.getAssessment().getType().name().equalsIgnoreCase(assessmentType.name()))
				.collect(Collectors.toList());
		Double[] result = { 0d, 0d };
		for (Grade grade : gradesForAssessment) {
			result[0] += (grade.getScore() * grade.getAssessment().getRawScore() / totalRawScore);
			result[1] += grade.getAssessment().getRawScore();
		}
		result[1] = result[1] / totalRawScore * 100;
		result[0] = result[0] / result[1] * 100;
		return result;
	}

	/**
	 * Gets the average for a given Batch ID for the entire week for one
	 * particular assessment. One Week -> All Trainees in Batch -> Average Score
	 * -> One Assessment Type
	 * 
	 * @param batchId
	 * @param week
	 * @param assessmentType
	 * @return Map<'trainee', Double{avg score for all trainees for the week for
	 *         assessmentType, weight for assessment}
	 */
	public Map<Trainee, Double[]> getAvgBatchWeek(Integer batchId, Integer week, AssessmentType assessmentType) {
		Map<Trainee, Double[]> results = new HashMap<>();
		List<Trainee> trainees = traineeDAO.findAllByBatch(batchId);
		for (Trainee trainee : trainees) {
			results.put(trainee, getAvgTraineeWeek(trainee.getTraineeId(), week, assessmentType));
		}
		return results;
	}

	/**
	 * Get Weighted Average for a single assessment type for a given Trainee ID,
	 * returning weeks as keys in the map and the corresponding values of
	 * weight(%) and scores. All Weeks -> One Trainee -> Average Score -> One
	 * Assessment Type
	 * 
	 * @param traineeId
	 * @param assessmentType
	 * @return Map<'week', {'score', 'weight'}>
	 */
	public Map<Integer, Double[]> getAvgTraineeOverall(Integer traineeId, AssessmentType assessmentType) {
		Map<Integer, Double[]> results = new HashMap<>();
		Trainee trainee = traineeDAO.findOne(traineeId);
		int weeks = trainee.getBatch().getWeeks();
		for (Integer i = 1; i <= weeks; i++) {
			results.put(i, getAvgTraineeWeek(traineeId, i, assessmentType));
		}
		return results;
	}

	/**
	 * Get Weighted Average for a the whole batch for all the weeks per an
	 * assessment All Weeks -> All Trainees In Batch -> Average Score -> One
	 * Assessment Type
	 * 
	 * @param batchId
	 * @param assessmentType
	 * @return Map<Week #s, Double[]: 0: Score, 1: Weight>
	 */
	public Map<Integer, Double[]> getAvgBatchOverall(Integer batchId, AssessmentType assessmentType) {
		Map<Integer, Double[]> results = new HashMap<>();
		Batch batch = batchDAO.findOne(batchId);
		int weeks = batch.getWeeks();
		for (Integer i = 1; i <= weeks; i++) {
			Map<Trainee, Double[]> temp = getAvgBatchWeek(batchId, i, assessmentType);
			Double[] avg = { 0d, 0d };
			avg[1] = temp.values().iterator().next()[1];
			for (Map.Entry<Trainee, Double[]> t : temp.entrySet()) {
				avg[0] += t.getValue()[0];
			}
			avg[0] = avg[0] / temp.size();
			results.put(i, avg);
		}
		return results;
	}

	/**
	 * 
	 * @param traineeId
	 * @param week
	 * @return
	 */
	public Double getAvgTraineeWeek(Integer traineeId, Integer week) {
		List<Grade> allGrade = gradeDAO.findByTrainee(traineeId);
		List<Grade> gradesForTheWeek = allGrade.stream().filter(el -> el.getAssessment().getWeek() == week)
				.collect(Collectors.toList());
		Double totalRawScore = gradesForTheWeek.stream().mapToDouble(el -> el.getAssessment().getRawScore()).sum();
		Double result = 0d;
		for (Grade grade : gradesForTheWeek) {
			result += (grade.getScore() * grade.getAssessment().getRawScore() / totalRawScore);
		}
		return result;
	}

	/**
	 * 
	 * @param batchId
	 * @param week
	 * @return
	 */
	public Map<Trainee, Double> getAvgBatchWeek(Integer batchId, Integer week) {
		Map<Trainee, Double> results = new HashMap<>();
		List<Trainee> trainees = traineeDAO.findAllByBatch(batchId);
		for (Trainee trainee : trainees) {
			results.put(trainee, getAvgTraineeWeek(trainee.getTraineeId(), week));
		}
		return results;
	}

	/**
	 * 
	 * @param traineeId
	 * @return
	 */
	public Map<Integer, Double> getAvgTraineeOverall(Integer traineeId) {
		Map<Integer, Double> results = new HashMap<>();
		Trainee trainee = traineeDAO.findOne(traineeId);
		int weeks = trainee.getBatch().getWeeks();
		for (Integer i = 1; i <= weeks; i++) {
			results.put(i, getAvgTraineeWeek(traineeId, i));
		}
		return results;
	}

	/**
	 * 
	 * @param batchId
	 * @return
	 */
	public Map<Integer, Double> getAvgBatchOverall(Integer batchId) {
		Map<Integer, Double> results = new HashMap<>();
		Batch batch = batchDAO.findOne(batchId);
		int weeks = batch.getWeeks();

		for (Integer i = 1; i <= weeks; i++) {
			Map<Trainee, Double> temp = getAvgBatchWeek(batchId, i);
			Double avg = 0d;
			for (Map.Entry<Trainee, Double> t : temp.entrySet()) {
				avg += t.getValue();
			}
			avg = avg / temp.size();
			results.put(i, avg);
		}
		return results;
	}
	/**
	 * Average Grades of All Assessments Over the week
	 * @param batchId
	 * @param week
	 * @return
	 */
	public Map<String, Double> barChartAvgBatchWeek(int batchId, int week){
		Map<Trainee, Double> avgBatchWeek = getAvgBatchWeek(batchId, week);
		Map<String, Double> result = new HashMap<>();
		
		for(Map.Entry<Trainee, Double> t : avgBatchWeek.entrySet()) {
			result.put(t.getKey().getName(), t.getValue());
		}
		return result;
	}
	
	public Map<Integer, Double> lineChartAvgBatchOverall(int batchId){
		return getAvgBatchOverall(batchId);
	}

	/**
	 * 
	 * @param traineeId
	 * @param weekNumber
	 * @return Map<'skill, Double{average, number of assessments for that
	 *         skill}>
	 */
	public Map<Category, Double[]> getAvgSkillsTraineeWeek(Integer traineeId, Integer weekNumber) {
		Map<Category, Double[]> results = new HashMap<>();
		List<Grade> grades = gradeDAO.findByTrainee(traineeId);
		List<Grade> weekgrades = grades.stream().filter(g -> g.getAssessment().getWeek() == weekNumber)
				.collect(Collectors.toList());
		for (Grade g : weekgrades) {
			Category skill = g.getAssessment().getCategory();
			if (results.get(skill) == null) {
				Double temp[] = { g.getScore(), 1.0 };
				results.put(skill, temp);
			} else {
				Double[] temp = results.get(skill);
				temp[0] = ((temp[0] * temp[1]) + g.getScore()) / (temp[1] + 1);
				temp[1] = temp[1] + 1;
				results.remove(skill);
				results.put(skill, temp);
			}
		}
		return results;
	}
}
