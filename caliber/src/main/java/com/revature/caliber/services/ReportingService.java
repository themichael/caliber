package com.revature.caliber.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
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
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.data.NoteDAO;
import com.revature.caliber.data.TraineeDAO;

import net.sf.ehcache.store.chm.ConcurrentHashMap;

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

	// @SuppressWarnings("unused")
	private final static Logger log = Logger.getLogger(ReportingService.class);

	private GradeDAO gradeDAO;
	private BatchDAO batchDAO;
	private TraineeDAO traineeDAO;
	private NoteDAO noteDAO;

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

	/*
	 *******************************************************
	 * Doughnut / Pie Charts
	 *******************************************************
	 */

	/**
	 * Pie chart displaying number of trainees that received red, yellow, green,
	 * x-Axis: QC Statuses y-Axis: Number of Trainees with
	 * 
	 * @param batchId
	 * @param weekNumber
	 * @return Map <The QC Status, Number of Trainees>
	 */
	public Map<QCStatus, Integer> getBatchWeekPieChart(Integer batchId, Integer weekNumber) {
		List<Trainee> trainees = traineeDAO.findAllByBatch(batchId);
		Map<QCStatus, Integer> results = new HashMap<>();
		for (QCStatus s : QCStatus.values()) {
			results.put(s, 0);
		}
		for (Trainee t : trainees) {
			List<Note> notes = noteDAO.findQCIndividualNotes(t.getTraineeId(), weekNumber);
			for (Note n : notes) {
				QCStatus status = n.getQcStatus();
				Integer temp = results.get(status);
				temp++;
				results.remove(status);
				results.put(status, temp);
			}
		}
		return results;
	}

	/*
	 *******************************************************
	 * Stacked Bar Chart
	 *******************************************************
	 */
	public Map<String, Map<QCStatus, Integer>> getAllBatchesCurrentWeekQCStackedBarChart() {
		Map<String, Map<QCStatus, Integer>> results = new HashMap<>();
		List<Batch> currentBatches = batchDAO.findAllCurrent();
		for (Batch b : currentBatches) {
			Map<Integer, Map<QCStatus, Integer>> batchWeekQCStats = utilSeparateQCTraineeNotesByWeek(b);
			for (Integer i = batchWeekQCStats.size(); i > 0; i--) {
				Map<QCStatus, Integer> temp = batchWeekQCStats.get(i);
				if (temp.values().stream().mapToInt(Number::intValue).sum() != 0) {
					results.put(b.getTrainingName(), temp);
					break;
				}
			}
		}

		return results;
	}

	public Map<Integer, Map<QCStatus, Integer>> utilSeparateQCTraineeNotesByWeek(Batch batch) {
		Map<Integer, Map<QCStatus, Integer>> results = new HashMap<>();

		Map<QCStatus, Integer> qcStatsMapTemplate = new HashMap<>();
		for (QCStatus q : QCStatus.values()) {
			qcStatsMapTemplate.put(q, 0);
		}

		for (Integer i = 1; i <= batch.getWeeks(); i++) {
			results.put(i, new HashMap<>(qcStatsMapTemplate));
		}

		for (Trainee t : batch.getTrainees()) {
			for (Note n : t.getNotes()) {
				if (n.getQcStatus() != null) {
					Map<QCStatus, Integer> temp = results.get((int) n.getWeek());
					Integer count = temp.get(n.getQcStatus()) + 1;
					temp.put(n.getQcStatus(), count);
					results.put((int) n.getWeek(), temp);
				}
			}
		}

		return results;
	}
	/*
	 *******************************************************
	 * Bar Charts
	 *******************************************************
	 */

	/**
	 * x-Axis: Assessment Names y-Axis: Average Batch Scores
	 * 
	 * @param batchId
	 * @param week
	 * @return
	 */
	public Map<String, Double[]> getBatchWeekAvgBarChart(int batchId, int week) {
		Map<String, Double[]> data = new HashMap<>();
		for (AssessmentType a : AssessmentType.values()) {
			Map<Trainee, Double[]> temp = utilAvgBatchWeek(batchId, week, a);
			Double batchAvg = 0d;
			double batchRaw = temp.entrySet().iterator().next().getValue()[1];
			for (Map.Entry<Trainee, Double[]> e : temp.entrySet()) {
				batchAvg += e.getValue()[0];
			}
			batchAvg = batchAvg / temp.size();
			if (!temp.entrySet().iterator().next().getValue()[0].isNaN()) {
				data.put(a.name(), new Double[] { batchAvg, batchRaw });
			}
		}
		return data;
	}

	/**
	 * x-Axis: Trainee Names y-Axis: Average Trainee Score
	 * 
	 * @param batchId
	 * @param week
	 * @return Map<Trainee's name, Double Average Score>
	 */
	public Map<String, Double> getBatchWeekSortedBarChart(int batchId, int week) {
		Map<Trainee, Double> avgBatchWeek = utilAvgBatchWeek(batchId, week);
		Map<String, Double> result = new HashMap<>();
		for (Map.Entry<Trainee, Double> t : avgBatchWeek.entrySet()) {
			result.put(t.getKey().getName(), t.getValue());
		}
		return result;
	}

	/**
	 * x-Axis: Assessment Name, (Weight as well) y-Axis: Average Scores
	 * 
	 * @param traineeId
	 * @param week
	 * @return Map<AssessmentType Name, [0: Trainee Overall Average, 1: Batch
	 *         Overall Average, 2: Score Weight]>
	 */
	public Map<String, Double[]> getBatchOverallTraineeBarChart(Integer batchId, Integer traineeId) {
		Map<String, Double[]> results = new HashMap<>();
		for (AssessmentType a : AssessmentType.values()) {
			int[] counts = { 0, 0 };
			Map<Integer, Double[]> avgTraineeWeek = utilAvgTraineeOverall(traineeId, a);
			Map<Integer, Double[]> avgBatchWeek = utilAvgBatchOverall(batchId, a);
			Double batchAvg = 0d;
			for (Entry<Integer, Double[]> e : avgBatchWeek.entrySet()) {
				counts[1] += e.getValue()[2];
				batchAvg += e.getValue()[0] * e.getValue()[2];
			}
			if (counts[1] > 0) {
				batchAvg = batchAvg / counts[1];
				Double traineeAvg = 0d;
				for (Entry<Integer, Double[]> e : avgTraineeWeek.entrySet()) {
					counts[0] += e.getValue()[2];
					traineeAvg += e.getValue()[0] * e.getValue()[2];
				}
				traineeAvg = traineeAvg / counts[0];
				results.put(a.name(), new Double[] { traineeAvg, batchAvg });
			}
		}
		return results;
	}

	/**
	 * x-Axis: Trainee y-Axis: Average score
	 * 
	 * @param batchId
	 * @return
	 */
	public Map<String, Double> getBatchOverallBarChart(Integer batchId) {
		Batch batch = batchDAO.findOne(batchId);
		Map<String, Double> results = new HashMap<>();
		int weeks = batch.getWeeks();
		log.debug("weeks are: " + weeks);

		List<Trainee> trainees = traineeDAO.findAllByBatch(batchId);
		for (Trainee trainee : trainees) {
			Double avg = 0.d;
			int weeksWithGrades = 0;
			for (Integer i = 0; i < weeks; i++) {
				Double tempAvg = utilAvgTraineeWeek(trainee.getTraineeId(), i);
				if (tempAvg > 0)
					weeksWithGrades++;
				avg += tempAvg;
				log.debug("avg for the week" + avg);
			}
			avg = avg / weeksWithGrades;
			log.debug("avg after computation" + avg);
			results.put(trainee.getName(), avg);
		}
		log.debug(results);
		return results;
	}

	/**
	 * x-Axis: y-Axis:
	 * 
	 * @param traineeId
	 * @param week
	 * @return map<'assessmentType', {traineeAvg, batchAvg, weight Percentage}
	 */
	public Map<String, Double[]> getBatchWeekTraineeBarChart(Integer batchId, Integer traineeId, Integer week) {
		Map<String, Double[]> results = new HashMap<>();
		List<Grade> grades = gradeDAO.findByTrainee(traineeId);
		for (AssessmentType a : AssessmentType.values()) {
			Double[] avgTraineeWeek = utilAvgTraineeWeek(week, a, grades);
			Map<Trainee, Double[]> avgBatchWeek = utilAvgBatchWeek(batchId, week, a);
			Double batchAvg = 0d;
			for (Map.Entry<Trainee, Double[]> e : avgBatchWeek.entrySet()) {
				batchAvg += e.getValue()[0];
			}
			batchAvg = batchAvg / avgBatchWeek.size();
			if (batchAvg != 0) {
				results.put(a.name(), new Double[] { avgTraineeWeek[0], batchAvg, avgTraineeWeek[1] });
			}
		}
		return results;
	}

	/*
	 *******************************************************
	 * Line Charts
	 *******************************************************
	 */

	/**
	 * x-Axis: y-Axis:
	 * 
	 * @param batchId
	 * @param week
	 * @param traineeId
	 * @return Map<'week', 'avgScore'>
	 */

	public Map<Integer, Double[]> getTraineeUpToWeekLineChart(int week, int traineeId) {
		Map<Integer, Double[]> results = new HashMap<>();
		int batchId = traineeDAO.findOne(traineeId).getBatch().getBatchId();
		for (int w = 1; w <= week; w++) {
			Double temp[] = { utilAvgTraineeWeek(traineeId, w), utilAvgBatchWeekValue(batchId, w) };
			results.put(w, temp);
		}
		return results;
	}

	/**
	 * x-Axis: trainee avg, batch avg y-Axis: week
	 * 
	 * @param batchId,
	 * @param traineeId
	 * @return Map<Total Weeks, Double[0:Trainee Overall Average 1: Batch
	 *         Overall Average]>
	 * 
	 */
	public Map<Integer, Double[]> getTraineeOverallLineChart(Integer batchId, Integer traineeId) {
		Map<Integer, Double> batchAvgOverall = utilAvgBatchOverall(batchId);
		Map<Integer, Double> traineeAvgOverall = utilAvgTraineeOverall(traineeId);
		Map<Integer, Double[]> results = new HashMap<>();
		int totalWeeks = traineeAvgOverall.size();
		for (int i = 1; i <= totalWeeks; i++) {
			Double[] temp = { traineeAvgOverall.get(i), batchAvgOverall.get(i) };
			if (temp[1] == 0) {
				continue;
			}
			results.put(i, temp);
		}
		return results;
	}

	/**
	 * x-Axis: y-Axis: Method for Controller to fetch Week number Batch Average
	 * Score
	 * 
	 * @param batchId
	 * @return Map<Week #, Double Average Score>
	 */
	public Map<Integer, Double> getBatchOverallLineChart(int batchId) {
		return utilAvgBatchOverall(batchId);
	}
	
	public Map<String, Map<Integer, Double>> getAllCurrentBatchesLineChart(){
		Map<String, Map<Integer, Double>> results = new ConcurrentHashMap<>();
		
		
		
		
		return results;
	}

	/*
	 *******************************************************
	 * Radar Charts
	 *******************************************************
	 */

	/**
	 * label-Axis: value-Axis:
	 * 
	 * @param traineeId
	 * @param week
	 * @return
	 */
	public Map<String, Double> getTraineeUpToWeekRadarChart(Integer traineeId, Integer week) {
		List<Grade> grades = gradeDAO.findByTrainee(traineeId);
		List<Grade> weekgrades = grades.stream().filter(g -> g.getAssessment().getWeek() <= week)
				.collect(Collectors.toList());
		Map<Category, Double[]> skills = utilAvgSkills(weekgrades);
		return utilReplaceCategoryWithSkillName(skills);
	}

	/**
	 * label-Axis: value-Axis:
	 * 
	 * @param traineeId
	 * @return
	 */
	public Map<String, Double> getTraineeOverallRadarChart(Integer traineeId) {
		List<Grade> grades = gradeDAO.findByTrainee(traineeId);
		Map<Category, Double[]> skills = utilAvgSkills(grades);
		return utilReplaceCategoryWithSkillName(skills);
	}

	/**
	 * label-Axis: value-Axis:
	 * 
	 * @param batchId
	 * @return
	 */
	public Map<String, Double> getBatchOverallRadarChart(Integer batchId) {
		List<Grade> grades = gradeDAO.findByBatch(batchId);
		Map<Category, Double[]> skills = utilAvgSkills(grades);
		return utilReplaceCategoryWithSkillName(skills);
	}

	/*
	 *******************************************************
	 * Utility Methods
	 *******************************************************
	 */

	/**
	 * Gets the average for a given Trainee ID for the entire week for one
	 * particular assessment. One Week -> One Trainee -> Average Score -> One
	 * Assessment Type
	 * 
	 * @param traineeId
	 * @param week
	 * @param assessmentType
	 * @return Double [0: Average Trainee score for the week for AssessmentType,
	 *         1: Weight of that assessment type, 2: Number of Assessments of
	 *         Each Type}
	 */
	public Double[] utilAvgTraineeWeek(Integer week, AssessmentType assessmentType, List<Grade> allGrade) {
		List<Grade> gradesForTheWeek = allGrade.stream().filter(el -> el.getAssessment().getWeek() == week)
				.collect(Collectors.toList());
		Double totalRawScore = gradesForTheWeek.stream().mapToDouble(el -> el.getAssessment().getRawScore()).sum();
		List<Grade> gradesForAssessment = gradesForTheWeek.stream()
				.filter(e -> e.getAssessment().getType().name().equalsIgnoreCase(assessmentType.name()))
				.collect(Collectors.toList());
		Double[] result = { 0d, 0d, 0d };
		if (gradesForAssessment.size() == 0) {
			return result;
		} else {
			result[2] = (double) gradesForAssessment.size();
		}
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
	 * @return Map<'Trainee in Batch, Double[0: Average score for all trainees
	 *         for the week for assessmentType, weight for assessment}
	 */
	public Map<Trainee, Double[]> utilAvgBatchWeek(Integer batchId, Integer week, AssessmentType assessmentType) {
		Map<Trainee, Double[]> results = new HashMap<>();
		List<Trainee> trainees = traineeDAO.findAllByBatch(batchId);
		for (Trainee trainee : trainees) {
			List<Grade> gradeList = new ArrayList<Grade>(trainee.getGrades());
			results.put(trainee, utilAvgTraineeWeek(week, assessmentType, gradeList));
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
	public Map<Integer, Double[]> utilAvgTraineeOverall(Integer traineeId, AssessmentType assessmentType) {
		Map<Integer, Double[]> results = new HashMap<>();
		Trainee trainee = traineeDAO.findOne(traineeId);
		List<Grade> grades = gradeDAO.findByTrainee(traineeId);
		int weeks = trainee.getBatch().getWeeks();
		for (Integer i = 1; i <= weeks; i++) {
			Double[] avg = utilAvgTraineeWeek(i, assessmentType, grades);
			if (avg[0] > 0.0) {
				results.put(i, avg);
			}
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
	public Map<Integer, Double[]> utilAvgBatchOverall(Integer batchId, AssessmentType assessmentType) {
		Map<Integer, Double[]> results = new HashMap<>();
		Batch batch = batchDAO.findOne(batchId);
		int weeks = batch.getWeeks();
		for (Integer i = 1; i <= weeks; i++) {
			Map<Trainee, Double[]> temp = utilAvgBatchWeek(batchId, i, assessmentType);
			Double[] avg = { 0d, 0d, 0d };
			avg[1] = temp.values().iterator().next()[1];
			avg[2] = temp.values().iterator().next()[2];
			for (Map.Entry<Trainee, Double[]> t : temp.entrySet()) {
				avg[0] += t.getValue()[0];
			}
			avg[0] = avg[0] / temp.size();
			results.put(i, avg);
		}
		return results;
	}

	/**
	 * Gets the average score of a trainee over the week for all assessments.
	 * 
	 * @param traineeId
	 * @param week
	 * @return Double average score over the week for all assessments
	 */
	public Double utilAvgTraineeWeek(Integer traineeId, Integer week) {
		List<Grade> allGrade = gradeDAO.findByTrainee(traineeId);
		log.debug("all grades in util = " + allGrade);
		List<Grade> gradesForTheWeek = allGrade.stream().filter(el -> el.getAssessment().getWeek() == week)
				.collect(Collectors.toList());
		log.debug("filtered grades in util = " + gradesForTheWeek);
		Double totalRawScore = gradesForTheWeek.stream().mapToDouble(el -> el.getAssessment().getRawScore()).sum();
		Double result = 0d;
		for (Grade grade : gradesForTheWeek) {
			result += (grade.getScore() * grade.getAssessment().getRawScore() / totalRawScore);
		}
		log.debug(result);
		return result;
	}

	/**
	 * Takes a batchId and Week number, and returns the average score per
	 * trainee for that week.
	 * 
	 * @param batchId
	 * @param week
	 * @return Map<Trainee in Batch, Average Total Assessment Score>
	 */
	public Map<Trainee, Double> utilAvgBatchWeek(Integer batchId, Integer week) {
		Map<Trainee, Double> results = new HashMap<>();
		List<Trainee> trainees = traineeDAO.findAllByBatch(batchId);
		for (Trainee trainee : trainees) {
			results.put(trainee, utilAvgTraineeWeek(trainee.getTraineeId(), week));
		}
		return results;
	}

	/**
	 * Gets the Week numbers and the average scores for all assessments for the
	 * given trainee id for all weeks.
	 * 
	 * @param traineeId
	 * @return Map<Week Number, Average Total Assessment Score Per Week>
	 */
	public Map<Integer, Double> utilAvgTraineeOverall(Integer traineeId) {
		Map<Integer, Double> results = new HashMap<>();
		Trainee trainee = traineeDAO.findOne(traineeId);
		int weeks = trainee.getBatch().getWeeks();
		for (Integer i = 1; i <= weeks; i++) {
			results.put(i, utilAvgTraineeWeek(traineeId, i));
		}
		return results;
	}

	/**
	 * Takes in batch ID, and returns a Map with the Week and the average Grades
	 * for that week for the batch.
	 * 
	 * @param batchId
	 * @return Map<Week Number, Double Average Score for All Assessments For the
	 *         Week>
	 */
	public Map<Integer, Double> utilAvgBatchOverall(Integer batchId) {
		Map<Integer, Double> results = new HashMap<>();
		Batch batch = batchDAO.findOne(batchId);
		int weeks = batch.getWeeks();
		for (Integer i = 1; i <= weeks; i++) {
			Map<Trainee, Double> temp = utilAvgBatchWeek(batchId, i);
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
	 * Takes in a List of Grades and Returns an Map with Categories and Averages
	 * for Each Categories
	 * 
	 * @param traineeId
	 * @param weekNumber
	 * @return Map<Category, Double[0: Average, 1: Number of assessments for
	 *         that skill]>
	 */
	public Map<Category, Double[]> utilAvgSkills(List<Grade> grades) {
		Map<Category, Double[]> results = new HashMap<>();
		for (Grade g : grades) {
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

	/**
	 * Takes a Map of Category, Double and converts it to Category Skill Names
	 * and Double
	 * 
	 * @param skills
	 * @return Map<Category Skill Name, Double Same as Input>
	 */
	public Map<String, Double> utilReplaceCategoryWithSkillName(Map<Category, Double[]> skills) {
		Map<String, Double> skillsWithLabels = new TreeMap<>();
		for (Entry<Category, Double[]> entry : skills.entrySet()) {
			skillsWithLabels.put(entry.getKey().getSkillCategory(), entry.getValue()[0]);
		}
		return skillsWithLabels;
	}

	/**
	 * Takes the weighted sum of scores by assessment type, averages that with
	 * all other assessments for the week, sums the value for all trainees overa
	 * wekk, and finally divides by number of trainees
	 * 
	 * @param batchId
	 * @param week
	 * @return Double [Average Value of Trainee Scores Weighted by Category
	 *         Types]
	 */
	public Double utilAvgBatchWeekValue(Integer batchId, Integer week) {
		Map<Trainee, Double> traineeAverageGrades = utilAvgBatchWeek(batchId, week);
		Double weeklyBatchAverage = traineeAverageGrades.entrySet().stream().mapToDouble(e -> e.getValue()).sum()
				/ traineeAverageGrades.size();
		return weeklyBatchAverage;
	}

	public Map<Integer, Set<Category>> utilCategorybyWeek(Integer batchId) {
		List<Grade> grades = gradeDAO.findByBatch(batchId);
		Set<Category> categories = new HashSet<>();
		Map<Integer, Set<Category>> results = new HashMap<>();
		for (Grade grade : grades) {
			categories.add(grade.getAssessment().getCategory());
			results.put(Integer.valueOf(grade.getAssessment().getWeek()), categories);
		}
		return results;

	}

	public Set<Category> utilCatagoryByWeekNumber(Integer batchId, Integer week) {
		Map<Integer, Set<Category>> allWeekCatagory = utilCategorybyWeek(batchId);
		Set<Category> result = allWeekCatagory.get(week);
		return result;

	}

}
