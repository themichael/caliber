package com.revature.caliber.test.unit;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.services.ReportingService;

public class ReportingServiceTest extends CaliberTest{

	private static final Logger log = Logger.getLogger(ReportingServiceTest.class);
	
	
	@Autowired
	ReportingService reportingService;
	
	//BatchDAO is only autowired here to get one batch from the database and use it's id number.
	@Autowired
	BatchDAO batchDao;
	
	
	@Test
	public void getBatchWeekPieChartTest() {
		
		Integer batchId = 0;
		Integer weekNumber = 0;
		
		Map<QCStatus, Integer> pieChart = reportingService.getBatchWeekPieChart(batchId, weekNumber);
		assertNotNull(pieChart);
		
	}
	
	@Test
	public void pieChartCurrentWeekQCStatusTest() {
		
		Integer batchId = batchDao.findAll().get(1).getBatchId();
		
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
		
		Batch batch = batchDao.findAll().get(1);
		Integer batchId = batch.getBatchId();
		Integer week = batch.getWeeks();
		
		Note note = reportingService.getBatchWeekQcOverallBarChart(batchId, week);
		
		assertNotNull(note);
		
	}
	
	@Test
	public void getBatchWeekAvgBarChart() {
		
		Batch batch = batchDao.findAll().get(1);
		Integer batchId = batch.getBatchId();
		Integer week = batch.getWeeks();
		
		Map <String, Double[]> weekAvgBarChart = reportingService.getBatchWeekAvgBarChart(batchId, week);
		
		assertNotNull(weekAvgBarChart);
		
	}
	
	@Test
	public void getBatchWeekSortedBarChartTest() {
		
		Batch batch = batchDao.findAll().get(1);
		Integer batchId = batch.getBatchId();
		Integer week = batch.getWeeks();
		
		Map<String, Double> sortedBarChart = reportingService.getBatchWeekSortedBarChart(batchId, week);
		
		assertNotNull(sortedBarChart);
	
	}
		
	@Test
	public void  getBatchOverallBarChart(){
		//Positive Testing
		Map<String, Double> results = reportingService.getBatchOverallBarChart(2200);
		assertTrue("Test size of result set", results.size()== 15);
		assertTrue("Contains expected trainee", results.containsKey("Chen, Andrew"));
		assertTrue("Test accurate average calculation", results.get("Chen, Andrew").doubleValue() == 84.14575d);
		
		//Negative Testing
		//Grab non-existent batch
		try{
			results = reportingService.getBatchOverallBarChart(-1111);
			fail();
		}catch(NullPointerException e){
			log.debug(e);
		}
	}
	@Test
	public void getBatchWeekTraineeBarChart(){
		//Positive testing
		Map<String, Double[]> results = reportingService.getBatchWeekTraineeBarChart(2100, 5455, 1);
		assertNotNull("Results exist", results);
		assertTrue("Test size of result set", results.size() == 1);
		assertTrue("Result contains exam", results.containsKey("Exam"));
		assertTrue("Exam contains expected values", results.get("Exam")[0] == 93.0 
				& results.get("Exam")[1] == 85.625
				& results.get("Exam")[2] == 100);

		//Invalid TraineeID
		try{
		results = reportingService.getBatchWeekTraineeBarChart(2100, -123421, 1);
		fail();
		}catch(NoSuchElementException e){
			log.info(e);
		}

		results = reportingService.getBatchWeekTraineeBarChart(2100, 5455, -1000);
		assertTrue("Check invalid week", results.size() == 0);
	}
	@Test
	public void getBatchOverallTraineeBarChart() {
		//Training
		Map<String, Double[]> results = reportingService.getBatchOverallTraineeBarChart(2201, 5531);
	    assertNotNull("Results exist", results);
		assertTrue("Test size of result set ", results.size() == 4);
		
		assertTrue("Test data exists", results.containsKey("Exam"));
		Double[] myVals = results.get("Verbal");
		assertTrue("Test values of Verbal exam", myVals[0] == 69.2 & myVals[1] == 82.0);
		
		//Invalid TraineeID
		try{
			results = reportingService.getBatchOverallTraineeBarChart(2100, -123421);
			fail();
		}catch(NoSuchElementException e){
				log.info(e);
		}
	}
	/**
	 * @see com.revature.caliber.services.getBatchWeekQcOverallBarChart(Integer batchId, Integer week)
	 */
	@Test
	public void getBatchWeekQcOverallBarChartTest(){
		log.info("Testing method: getBatchWeekQcOverallBarChart(Integer batchId, Integer week)");
		Note testNote = reportingService.getBatchWeekQcOverallBarChart(2201, 7);
		//Note: I was pulling the getContent from testNote, and it would return a different string sometimes.
		assertEquals(testNote.getWeek(),(short)7);
		//invalid batch
		testNote = reportingService.getBatchWeekQcOverallBarChart(22, 7);
		assertNull(testNote);
		//invalid batch
		testNote = reportingService.getBatchWeekQcOverallBarChart(2201, -5);
		assertNull(testNote);
	}
}
		

