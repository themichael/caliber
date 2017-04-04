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
//	public Map<String, Double> getRadar(Integer batchId, Integer week, Integer traineeId){
//		Map<String, Double> results = new HashMap<>();
//		Batch batch = batchDAO.findOne(batchId);
//	}
	
	/**
	 * get all trainee's avg score of all assessments.
	 * @param batchId
	 * @param week
	 * @return Map<String (trainee's name, Double (Avg score)>
	 * @author Pier Yos
	 */
	public Map<String, Double> getBatchWeeklyAssessmentScore(int batchId, int week){
		Map<String, Double> results = new HashMap<>();
		Map<String, Double> assessmentNscoreMap = new HashMap<>();
		List<Trainee> trainees = traineeDAO.findAllByBatch(batchId);
		for(Trainee trainee : trainees) {
			double score = 0;
			int count = 0;
			assessmentNscoreMap = getWeightedAverageGradesOfTraineeByWeek(trainee, week);
			for(Map.Entry<String, Double> assessmentScore : assessmentNscoreMap.entrySet()){
				score += assessmentScore.getValue();
				count++;
			}			
			results.put(trainee.getName(), score/count);
		}
		return results;
	}
	/**
	 * 
	 * @param batchId
	 * @return Map<Integer (Week Num), Double (Avg score)>
	 */
	public Map<Integer, Double> getBatchOverallAvgScore(int batchId){
		Map<Integer, Double> results = new HashMap<>();
		return results;
	}
	
	/**
	 * Weighted Average of a Trainee's Grade Scores for a given week number 
	 * @param trainee For which to get the Average Scores
	 * @param week number to get the grades for 
	 * @return A Map of String Names of Assessment Types, and 
	 */
	public Map<String, Double> getWeightedAverageGradesOfTraineeByWeek(Trainee trainee, Integer week){
		Map<String, Double> results = new HashMap<>();
		Set<Grade> gradesForTheWeek = trainee.getGrades().stream().filter(el -> el.getAssessment().getWeek() == week).collect(Collectors.toSet());
		Double totalRawScore =  gradesForTheWeek.stream().mapToDouble(el -> el.getAssessment().getRawScore()).sum();
		for(Grade grade: gradesForTheWeek){
			results.put(grade.getAssessment().getType().name(), grade.getScore() * grade.getAssessment().getRawScore() / totalRawScore);
		}
		return results;
	}
	
	public Double merr(Set<Grade> grades, Integer week){
		Double results = 0d;
		Set<Grade> gradesForTheWeek = grades.stream().filter(el -> el.getAssessment().getWeek() == week).collect(Collectors.toSet());
		Double totalRawScore =  gradesForTheWeek.stream().mapToDouble(el -> el.getAssessment().getRawScore()).sum();
		for(Grade grade: gradesForTheWeek){
			results = grade.getScore() * grade.getAssessment().getRawScore() / totalRawScore;
		}
		return results;
	}

	//Pie chart displaying number of trainees that recieved red, yellow, green, or superstar
	//returns Map relating the number of trainees per QCStatus
	public Map<QCStatus, Integer> batchWeekPieChart(Integer batchId, Integer weekNumber){
		
		List<Trainee> trainees = traineeDAO.findAllByBatch(batchId);
		Map<QCStatus, Integer> results = new HashMap<>();
		 for(QCStatus s : QCStatus.values()){
			 results.put(s, 0);
		 }
		 for (Trainee t : trainees){
			 for (Note n : t.getNotes()){
				 if (n.getWeek()==weekNumber){
					 if (n.getType()==NoteType.QC_TRAINEE){
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

/*	public HashMap<Trainee, Double[]>  barChar(int batchId, int week) {
		//BatchDAO  

		
	
	}*/

	public Map<Integer, Double> findAvgGradeByWeek(int traineeId) {
		
		TraineeDAO td= new TraineeDAO();
		GradeDAO gd= new GradeDAO();
		AssessmentDAO ad= new AssessmentDAO(); 
		
		Trainee trainee =td.findOne(traineeId);
		List<Grade> gt=gd.findByTrainee(traineeId);
		
		Map<Integer, Double> data = new HashMap<Integer, Double>();
		
		

		int week = 0;
		
		
		for(Grade g: gt){
			//List<Double> thescorelist = gt.stream().map(g.getAssessment().getWeek():: g.getScore() ).collect(Collectors.toList());
		
			//data.put( g.getAssessment().getWeek(), mean(thescorelist));
		}
		
		return data;
		
	}
	
	public double mean ( ArrayList<Double> list){
		double sum= 0.0;
		int length= list.size();
		for(double item : list){
			sum+=item;
		}
		return sum/length;
	}
}