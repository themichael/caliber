package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.util.List;
import java.util.Map;

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
		
		log.info("\n \n \n \n <getBatchWeekPieChartTest> Acquired batch information. BatchId: " + 2201 + " weekNumber: " + 7);
		
		Map<QCStatus, Integer> pieChart = reportingService.getBatchWeekPieChart(2201, 7);
		
		for (QCStatus key : pieChart.keySet()) {
			
			log.info("key: "+ key+ " " + pieChart.get(key));
			
		}
		
		assertEquals( (Integer) 0, (Integer) pieChart.get(QCStatus.Superstar));
		assertEquals( (Integer) 9, (Integer) pieChart.get(QCStatus.Good));
		assertEquals( (Integer) 0, (Integer) pieChart.get(QCStatus.Average));
		assertEquals( (Integer) 7, (Integer) pieChart.get(QCStatus.Poor));
		
		
	}
	
	@Test
	public void pieChartCurrentWeekQCStatusTest() {
		
		Integer batchId = 2201;
		
		log.info("\n \n \n <pieChartCurrentWeekQCStatusTest> Acquired batch information. BatchId: " + batchId);
		
		Map<QCStatus, Integer> pieChart = reportingService.pieChartCurrentWeekQCStatus(batchId);
		
		for (QCStatus key : pieChart.keySet()) {
			
			log.info("key: "+ key+ " " + pieChart.get(key));
			
		}
		
		assertEquals( (Integer) 0, (Integer) pieChart.get(QCStatus.Superstar));
		assertEquals( (Integer) 9, (Integer) pieChart.get(QCStatus.Good));
		assertEquals( (Integer) 0, (Integer) pieChart.get(QCStatus.Average));
		assertEquals( (Integer) 7, (Integer) pieChart.get(QCStatus.Poor));
		
		assertNotNull(pieChart);
		
	}
	
	
	@Test
	public void getAllBatchesCurrentWeekQCStackedBarChartTest() {
		
		
		log.info("\n \n \n \n \n <getAllBatchesCurrentWeekQCStackedBarChartTest> Acquire dem batches.");
		List<Object> object = reportingService.getAllBatchesCurrentWeekQCStackedBarChart();
		
		for (int i = 0; i < object.size(); i++) {
			
			log.info("Batch number " + i + ": " + object.get(i));
			
		}
		
		assertNotNull(object);
		
	}
	
	@Test
	public void getBatchWeekQcOverallBarChart() {
		
		Batch batch = batchDao.findAll().get(1);
		int batchId = batch.getBatchId();
		int weekNumber = 5;
		
		log.info("\n \n \n \n \n <getBatchWeekQcOverallBarChart> BatchId: " + batchId + " Week: " + 5);
		
		Note note = reportingService.getBatchWeekQcOverallBarChart(batchId, weekNumber);
		
		log.info("<getBatchWeekQcOverallBarChart> Note: " + note);
		
		assertEquals(5, note.getWeek());
		assertEquals(6438, note.getNoteId());
		
	}
	
	@Test
	public void getBatchWeekAvgBarChartTest() {
		
		int batchId = 2201;
		int weekNumber = 5;
		
		Map <String, Double[]> weekAvgBarChart = reportingService.getBatchWeekAvgBarChart(batchId, weekNumber);
		
		log.info("\n \n \n \n \n <getBatchWeekAvgBarChartTest> Acquiring batch number: " + 2201);
	
		for (String key : weekAvgBarChart.keySet()) {
			
			log.info("key: " + key + " " + weekAvgBarChart.get(key)[0]);
			
		}
		
		
		assertEquals((Double) 88.75, (Double) weekAvgBarChart.get("Project")[0]);
		assertEquals((Double) 76.109375, (Double) weekAvgBarChart.get("Exam")[0]);
		assertEquals((Double) 74.375, (Double) weekAvgBarChart.get("Verbal")[0]);
		
	}
	
	@Test
	@Ignore
	public void getBatchWeekSortedBarChartTest() {
		
		int batchId = 2201;
		int weekNumber = 5;
		
		Map<String, Double> sortedBarChart = reportingService.getBatchWeekSortedBarChart(batchId, weekNumber);
		
		log.info("\n \n \n \n \n <getBatchWeekSortedBarChartTest> Acquiring batch number: " + batchId);
		
		for (String key : sortedBarChart.keySet()) {
			
			log.info("key: " + key + " " + sortedBarChart.get(key));
			
		}
		
		assertEquals((Double) 97.0, (Double) sortedBarChart.get("Khawaja, Ateeb"));
	
	}
		
		
}
