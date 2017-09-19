package com.revature.caliber.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.log4j.Logger;
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
import com.revature.caliber.services.ReportingService;

public class ReportingServiceTest extends CaliberTest {

	private static final Logger log = Logger.getLogger(ReportingServiceTest.class);

	@Autowired
	public ReportingService reportingService;

	// BatchDAO is only autowired here to get one batch from the database and
	// use it's id number.
	@Autowired
	public BatchDAO batchDAO;

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

		//positive testing
		Map<Integer, Map<QCStatus, Integer>> results = reportingService.utilSeparateQCTraineeNotesByWeek(batch);
		for (int i = 1; i <= batch.getWeeks(); i++) {
			//grab the QCStatus map made by utilSeperateQCTraineeNotesByWeek for the week
			Map<QCStatus, Integer> weekStatusCount = results.get(i);
			for (QCStatus status : weekStatusCount.keySet()) {
				int expectCount = weekStatusCount.get(status);
				// Actual count is the statusCountPerWeek - 1 to account that i starts at 1;
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
		
		//Negative Testing
		try {
			results = reportingService.utilSeparateQCTraineeNotesByWeek(null);
		} catch (NullPointerException e) {
			log.debug(e);
		}
	}
	
	
}
