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
		log.info("getBatchWeekPieChartTest");
		Integer batchId = 0;
		Integer weekNumber = 0;
		
		Map<QCStatus, Integer> pieChart = reportingService.getBatchWeekPieChart(batchId, weekNumber);
		assertNotNull(pieChart);
		
	}
	
	@Test
	public void pieChartCurrentWeekQCStatusTest() {
		log.info("pieChartCurrentWeekQCStatusTest");
		Integer batchId = batchDao.findAll().get(1).getBatchId();
		
		Map<QCStatus, Integer> pieChart = reportingService.pieChartCurrentWeekQCStatus(batchId);
		
		assertNotNull(pieChart);
		
	}
	
	
	@Test
	public void getAllBatchesCurrentWeekQCStackedBarChart() {
		log.info("getAllBatchesCurrentWeekQCStackedBarChart");
		List<Object> object = reportingService.getAllBatchesCurrentWeekQCStackedBarChart();
		
		assertNotNull(object);
		
	}
	
	@Test
	public void getBatchWeekQcOverallBarChart() {
		log.info("getBatchWeekQcOverallBarChart");
		Batch batch = batchDao.findAll().get(1);
		Integer batchId = batch.getBatchId();
		Integer week = batch.getWeeks();
		
		Note note = reportingService.getBatchWeekQcOverallBarChart(batchId, week);
		
		assertNotNull(note);
		
	}
	
	@Test
	public void getBatchWeekAvgBarChart() {
		log.info("getBatchWeekAvgBarChart");
		Batch batch = batchDao.findAll().get(1);
		Integer batchId = batch.getBatchId();
		Integer week = batch.getWeeks();
		
		Map <String, Double[]> weekAvgBarChart = reportingService.getBatchWeekAvgBarChart(batchId, week);
		
		assertNotNull(weekAvgBarChart);
		
	}
	
	@Test
	public void  getBatchOverallBarChart(){
		log.info("getBatchOverallBarChart");
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
		log.info("getBatchWeekTraineeBarChart");
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
		log.info("getBatchOverallTraineeBarChart");
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
	
	
	@Test
	public void getBatchWeekAvgBarChartTest(){
		log.info("getBatchWeekAvgBarChartTest");
		Map<String, Double[]> test= reportingService.getBatchWeekAvgBarChart(2050, 2);
		assertEquals((Double)90.55166666666666, (Double)test.get("Exam")[0]);
		assertNotEquals((Double)90.55166666666666, (Double)test.get("Exam")[1]);
		assertEquals(2, test.get("Exam").length);
		assertEquals(1, test.size());
	}
	/**
	 * Tests method:
	 * com.revature.caliber.services.ReportingService.getBatchWeekSortedBarChart(int batchId, int week)
	 */
	@Test
	public void getBatchWeekSortedBarChartTest(){
		log.info("getBatchWeekSortedBarChartTest");
		Map<String, Double> test =reportingService.getBatchWeekSortedBarChart(2050, 2);
		assertNotEquals((Double)96.29, (Double)test.get("Issac,Fouche"));
		assertEquals((Double)96.29, (Double)test.get("Fouche, Issac"));
		assertNotEquals((Double)96.29, (Double)test.get("Castillo, Erika"));
		assertEquals(6, test.size());
	}	
	/**
	 * Tests method:
	 * com.revature.caliber.services.ReportingService.getBatchOverallTraineeBarChart(Integer batchId, Integer traineeId)
	 */
	@Test
	public void getBatchOverallTraineeBarChartTest(){
		log.info("getBatchOverallTraineeBarChartTest");
		Map<String, Double[]> test =reportingService.getBatchOverallTraineeBarChart(2050, 5354);
		assertEquals((Double)90.9825, (Double)test.get("Exam")[0]);
		assertNotEquals((Double)90.9825, (Double)test.get("Exam")[1]);
		assertEquals(2, test.get("Exam").length);
		assertEquals(1, test.size());
	}
}
		

