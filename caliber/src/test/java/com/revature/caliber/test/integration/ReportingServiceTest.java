package com.revature.caliber.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.services.ReportingService;

public class ReportingServiceTest extends CaliberTest {

	private static final Logger log = Logger.getLogger(ReportingServiceTest.class);

	@Autowired
	ReportingService reportingService;

	// BatchDAO is only autowired here to get one batch from the database and
	// use it's id number.
	@Autowired
	BatchDAO batchDao;

	public void setReportingService(ReportingService reportingService) {
		this.reportingService = reportingService;
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

		Map<String, Double[]> weekAvgBarChart = reportingService.getBatchWeekAvgBarChart(batchId, week);

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
	/**
	 * Tests methods:
	 * com.revature.caliber.services.ReportingService.getBatchOverallBarChart
	 */
	@Test
	public void getBatchOverallBarChart() {
		// Positive Testing
		Map<String, Double> results = reportingService.getBatchOverallBarChart(2200);
		assertTrue("Test size of result set", results.size() == 15);
		assertTrue("Contains expected trainee", results.containsKey("Chen, Andrew"));
		assertTrue("Test accurate average calculation", results.get("Chen, Andrew").doubleValue() == 84.14575d);

		// Negative Testing
		// Grab non-existent batch
		try {
			results = reportingService.getBatchOverallBarChart(-1111);
			fail();
		} catch (NullPointerException e) {
			log.debug(e);
		}
	}
	/**
	 * Tests methods:
	 * com.revature.caliber.services.ReportingService.getBatchWeekTraineeBarChart
	 */
	@Test
	public void getBatchWeekTraineeBarChart() {
		// Positive testing
		Map<String, Double[]> results = reportingService.getBatchWeekTraineeBarChart(2100, 5455, 1);
		assertNotNull("Results exist", results);
		assertTrue("Test size of result set", results.size() == 1);
		assertTrue("Result contains exam", results.containsKey("Exam"));
		assertTrue("Exam contains expected values",
				results.get("Exam")[0] == 93.0 & results.get("Exam")[1] == 85.625 & results.get("Exam")[2] == 100);

		// Invalid TraineeID
		try {
			results = reportingService.getBatchWeekTraineeBarChart(2100, -123421, 1);
			fail();
		} catch (NoSuchElementException e) {
			log.info(e);
		}

		results = reportingService.getBatchWeekTraineeBarChart(2100, 5455, -1000);
		assertTrue("Check invalid week", results.size() == 0);
	}
	/**
	 * Tests methods:
	 * com.revature.caliber.services.ReportingService.getBatchOverallTraineeBarChart
	 */
	@Test
	public void getBatchOverallTraineeBarChart() {
		// Training
		Map<String, Double[]> results = reportingService.getBatchOverallTraineeBarChart(2201, 5531);
		assertNotNull("Results exist", results);
		assertTrue("Test size of result set ", results.size() == 4);

		assertTrue("Test data exists", results.containsKey("Exam"));
		Double[] myVals = results.get("Verbal");
		assertTrue("Test values of Verbal exam", myVals[0] == 69.2 & myVals[1] == 82.0);

		// Invalid TraineeID
		try {
			results = reportingService.getBatchOverallTraineeBarChart(2100, -123421);
			fail();
		} catch (NoSuchElementException e) {
			log.info(e);
		}

	}

	/**
	 * Utility method for testing Creates and returns a batch with 3 trainees
	 * with QC Notes and statuses for 7 weeks
	 * 
	 * @return Batch
	 */
	private Batch createTestBatchWithQCNotes() {
		Set<Trainee> trainees = new HashSet<>();
		Batch batch = new Batch();
		batch.setWeeks(7);
		for (int i = 1; i < 4; i++) {
			Trainee trainee = new Trainee("Trainee" + i, "java", "email@email.com", batch);
			trainee.setTraineeId(i);
			Set<Note> notes = new HashSet<Note>();
			for (int j = 1; j < 8; j++) {
				Note note = new Note();
				note.setWeek((short) j);
				switch (j % 4) {
				case 0: {
					note.setQcStatus(QCStatus.Poor);
					break;
				}
				case 1: {
					note.setQcStatus(QCStatus.Average);
					break;
				}
				case 2: {
					note.setQcStatus(QCStatus.Good);
					break;
				}
				case 3: {
					note.setQcStatus(QCStatus.Superstar);
					break;
				}
				}
				notes.add(note);
			}
			trainee.setNotes(notes);
			trainees.add(trainee);
		}
		batch.setTrainees(trainees);
		return batch;
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#utilSeparateQCTraineeNotesByWeekTest()
	 */
	@Test
	public void utilSeparateQCTraineeNotesByWeekTest() {

		log.info("Testing the ReportingService.utilSeperateQCTraineeNotesByWeek");

		int[] statusPoorCountPerWeek = { 0, 0, 0, 3, 0, 0, 0 };
		int[] statusAverageCountPerWeek = { 3, 0, 0, 0, 3, 0, 0 };
		int[] statusGoodCountPerWeek = { 0, 3, 0, 0, 0, 3, 0 };
		int[] statusSuperstarCountPerWeek = { 0, 0, 3, 0, 0, 0, 3 };

		Batch batch = createTestBatchWithQCNotes();

		Map<Integer, Map<QCStatus, Integer>> results = reportingService.utilSeparateQCTraineeNotesByWeek(batch);
		for (int i = 1; i <= batch.getWeeks(); i++) {
			Map<QCStatus, Integer> weekStatusCount = results.get(i);
			for (QCStatus status : weekStatusCount.keySet()) {
				int expectCount = weekStatusCount.get(status);
				// Actual count is the statusCountPerWeek - 1 to account that i
				// starts at 1;
				switch (status) {
				case Poor: {
					assertEquals(expectCount, statusPoorCountPerWeek[i - 1]);
					break;
				}
				case Average: {
					assertEquals(expectCount, statusAverageCountPerWeek[i - 1]);
					break;
				}
				case Good: {
					assertEquals(expectCount, statusGoodCountPerWeek[i - 1]);
					break;
				}
				case Superstar: {
					assertEquals(expectCount, statusSuperstarCountPerWeek[i - 1]);
					break;
				}
				default: {
					assertTrue("QCStatus " + status + "  not checked during test", false);
				}
				}
			}
		}
	}

}
