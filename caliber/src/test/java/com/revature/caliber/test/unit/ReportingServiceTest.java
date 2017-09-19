package com.revature.caliber.test.unit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Ignore;
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
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.services.ReportingService;

public class ReportingServiceTest extends CaliberTest{

	private static final Logger log = Logger.getLogger(ReportingServiceTest.class);
	
	@Autowired
	private ReportingService reportingService;
	
	@Autowired
	private BatchDAO batchDAO;
	
	private static List<Batch> batchComparisonInit(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1, 1);
		Calendar start = Calendar.getInstance();
		start.set(Calendar.YEAR, 3, 1);
		Calendar end = Calendar.getInstance();
		end.set(Calendar.YEAR, 4, 1);
		
		Batch b1 = new Batch("1808-Java",new Trainer(),start.getTime(), end.getTime(), "Revature");
		Batch b2 = new Batch("1909-Java",new Trainer(), start.getTime(), end.getTime(), "Revature");
		b1.setWeeks(4);
		b2.setWeeks(4);
		
		Set<Trainee> trainees1 = new HashSet<>();
		Set<Trainee> trainees2 = new HashSet<>();
		
		int score1[] = {70, 80, 80, 90};
		int score2[] = {100, 90, 90, 80};
		
		for(int i = 1; i < 2; i++){
            Trainee trainee1 = new Trainee("Trainee1_" + i, "java", "email@email.com", b1);
            Trainee trainee2 = new Trainee("Trainee2_" + i,".net","email@email.com",b2);
            trainee1.setTraineeId(i+100);
            trainee2.setTraineeId(i+200);
            
            Set<Grade> grades1 = new HashSet<Grade>();
            Set<Grade> grades2 = new HashSet<Grade>();
            for(int j = 1; j < 5; j++){
                Assessment weekB1 = new Assessment("A title:" + j, b1, 100, AssessmentType.Exam, j, new Category());
                Assessment weekB2 = new Assessment("A title:" + j, b2, 100, AssessmentType.Exam,j, new Category());
                grades1.add(new Grade(weekB1, trainee1, new Date(), score1[j-1]));
                grades2.add(new Grade(weekB2, trainee2, new Date(), score2[j-1]));
            }
            trainee1.setGrades(grades1);
            trainee2.setGrades(grades2);
            trainees1.add(trainee1);
            trainees2.add(trainee2);
        }
		
		b1.setTrainees(trainees1);
		b2.setTrainees(trainees2);
		
		List<Batch> batches = new ArrayList<>();
		batches.add(b1);
		batches.add(b2);
		return batches;
	}
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.service.ReportingService#getBatchComparisonAvg(List<Batch> batches)
	 */
	@Test
	public void getBatchComparisonAvgTest(){
		log.info("Testing the ReportingService.getBatchCommparisonAvg(List<Batch> batches)");

		List<Batch> batches = batchComparisonInit();
		
		List<Batch> singleBatch1 = new ArrayList<>();
		singleBatch1.add(batches.get(0));
		
		List<Batch> singleBatch2 = new ArrayList<>();
		singleBatch2.add(batches.get(1));
		
		double avg = reportingService.getBatchComparisonAvg(singleBatch1);
		double expected = 80;
		assertTrue(Math.abs(avg-expected)<.0001);
		
		avg = reportingService.getBatchComparisonAvg(singleBatch2);
		expected = 90;
		assertTrue(Math.abs(avg-expected)<.0001);
		
		avg = reportingService.getBatchComparisonAvg(batches);
		expected = 85;
		assertTrue(Math.abs(avg-expected)<.0001);
	}
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.service.ReportingService#batchComparisonFilter(List<Batch> batches, String skill, String training)
	 */
	@Test
	public void batchComparisonFilterTest(){
		log.info("Testing the ReportingService.batchComparisonFilter(List<Batch> batches, String skill, String training)");
		
		String skillType = "(All)";
		String trainingType = "(All)";
		List<Batch> batches = reportingService.batchComparisonFilter(batchDAO.findAll(), skillType, trainingType);
		int expected = 5;
		int actual = batches.size();
		assertEquals(expected, actual);
		
		skillType = "J2EE";  //There is only one type of training in the setup.sql, so this is the same result "(All)"
		trainingType = "University";												//but uses a different code path
		batches = reportingService.batchComparisonFilter(batchDAO.findAll(), skillType, trainingType);
		expected = 2;
		actual = batches.size();
		assertEquals(expected, actual);
		
		skillType = "(All)";
		trainingType = "Revature";
		batches = reportingService.batchComparisonFilter(batchDAO.findAll(), skillType, trainingType);
		expected = 3;
		actual = batches.size();
		assertEquals(expected, actual);
		
		skillType = "(All)";
		trainingType = "University";
		batches = reportingService.batchComparisonFilter(batchDAO.findAll(), skillType, trainingType);
		expected = 2;
		actual = batches.size();
		assertEquals(expected, actual);
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
