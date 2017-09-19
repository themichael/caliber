package com.revature.caliber.test.unit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.AssessmentType;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.services.ReportingService;

public class ReportingServiceTest extends CaliberTest{

	private static final Logger log = Logger.getLogger(ReportingServiceTest.class);
	
	@Autowired
	private ReportingService reportingService;
	
	@Autowired
	private BatchDAO batchDAO;
	@Autowired
	private GradeDAO gradeDAO;
	
	private final String getBatches = "Select * from caliber_batch where batch_id = 2200";
	
	private static List<Trainee> trainees;
	private static List<Trainee> traineesGroup2;
	
	@BeforeClass
    public static void beforeClass(){
        trainees = new ArrayList<>();
        Batch batch = new Batch();
        batch.setWeeks(5);
        for(int i = 1; i < 4; i++){
            Trainee trainee = new Trainee("Trainee" + i, "java", "email@email.com", batch);
            trainee.setTraineeId(i);
            Set<Grade> grades = new HashSet<Grade>();
            for(int j = 1; j < 6; j++){
                Assessment week = new Assessment("A title:" + j, batch, 100, AssessmentType.Exam, j, new Category());
                grades.add(new Grade(week, trainee, new Date(), j*10 + (i-1)*10));
            }
            trainee.setGrades(grades);
            trainees.add(trainee);
        }
    }
	
	private static void traineesGroup2Init(){
        traineesGroup2 = new ArrayList<>();
        Batch batch2 = new Batch();
        batch2.setWeeks(5);
        for(int i = 1; i < 4; i++){
            Trainee trainee = new Trainee("Trainee" + i, "java", "email@email.com", batch2);
            trainee.setTraineeId(i);
            Set<Grade> grades = new HashSet<Grade>();
            for(int j = 1; j < 6; j++){
                Assessment week = new Assessment("A title:" + j, batch2, 100, AssessmentType.Exam, j, new Category());
                grades.add(new Grade(week, trainee, new Date(), j*10 + (i-1)*10));
            }
            trainee.setGrades(grades);
            trainees.add(trainee);
        }
	}
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.service.ReportingService#getBatchComparisonAvg(String skill, String training, Date startDate)
	 */
	@Test
	public void getBatchComparisonAvgTest(){
		log.info("Testing the ReportingService.getBatchCommparisonAvg(String skill, String training, Date startDate)");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1, 1);
		
		/*double batchAvg = 0;
		double traineeAvg = 0;
		double weekAvg = 0;
		List<Batch> batches =  batchDAO.findAllAfterDate(cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.YEAR));
		log.info(batches.size());
		for(Batch batch: batches){
			traineeAvg = 0;
			log.info("Trainees: " + batch.getTrainees().size());
			for(Trainee t: batch.getTrainees()){
				log.info(t.getTrainingStatus());
				weekAvg = 0;
				List<Grade> grades =gradeDAO.findByTrainee(t.getTraineeId());
				for(Grade g:grades){
					weekAvg += g.getAssessment().getRawScore();
				}
				weekAvg /= grades.size();
				traineeAvg += weekAvg;
			}
			traineeAvg /= batch.getTrainees().size();
			batchAvg += traineeAvg;
		}
		batchAvg /= batches.size();
		log.info(batchAvg);*/
	
		String skillType = "(All)";
		String trainingType = "(All)";
		double avg = reportingService.getBatchComparisonAvg(skillType, trainingType, cal.getTime());
		assertTrue(83<avg && avg<84);  //avg equals about 83.9
		
		skillType = "J2EE";  //There is only one type of training in the setup.sql, so this is the same result "(All)"
		trainingType = "University";												//but uses a different code path
		avg = reportingService.getBatchComparisonAvg(skillType, trainingType, cal.getTime());
		assertTrue(79<avg && avg<80);  //avg equals about 79.8
		
		skillType = "(All)";
		trainingType = "Revature";
		avg = reportingService.getBatchComparisonAvg(skillType, trainingType, cal.getTime());
		assertTrue(86<avg && avg<87);  //avg equals about 86.5
		
		skillType = "(All)";
		trainingType = "University";
		avg = reportingService.getBatchComparisonAvg(skillType, trainingType, cal.getTime());
		assertTrue(79<avg && avg<80);  //avg equals about 79.8
	}
	
	@Test
	public void getBatchWeekPieChartTest() {
		
		Integer batchId = 0;
		Integer weekNumber = 0;
		
		Map<QCStatus, Integer> pieChart = reportingService.getBatchWeekPieChart(batchId, weekNumber);
		assertNotNull(pieChart);
		
	}
	
	@Test
	public void pieChartCurrentWeekQCStatusTest() {
		
		Integer batchId = batchDAO.findAll().get(1).getBatchId();
		
		Map<QCStatus, Integer> pieChart = reportingService.pieChartCurrentWeekQCStatus(batchId);
		
		assertNotNull(pieChart);
		
	}
	
	
	@Test
	public void getAllBatchesCurrentWeekQCStackedBarChart() {
		
		List<Object> object = reportingService.getAllBatchesCurrentWeekQCStackedBarChart();
		
		assertNotNull(object);
		
	}
	
	@Test
	public void getBatchWeekQcOverallBarChart() {
		
		Batch batch = batchDAO.findAll().get(1);
		Integer batchId = batch.getBatchId();
		Integer week = batch.getWeeks();
		
		Note note = reportingService.getBatchWeekQcOverallBarChart(batchId, week);
		
		assertNotNull(note);
		
	}
	
	@Test
	public void getBatchWeekAvgBarChart() {
		
		Batch batch = batchDAO.findAll().get(1);
		Integer batchId = batch.getBatchId();
		Integer week = batch.getWeeks();
		
		Map <String, Double[]> weekAvgBarChart = reportingService.getBatchWeekAvgBarChart(batchId, week);
		
		assertNotNull(weekAvgBarChart);
		
	}
	
	@Test
	public void getBatchWeekSortedBarChartTest() {
		
		Batch batch = batchDAO.findAll().get(1);
		Integer batchId = batch.getBatchId();
		Integer week = batch.getWeeks();
		
		Map<String, Double> sortedBarChart = reportingService.getBatchWeekSortedBarChart(batchId, week);
		
		assertNotNull(sortedBarChart);
	
	}
}
