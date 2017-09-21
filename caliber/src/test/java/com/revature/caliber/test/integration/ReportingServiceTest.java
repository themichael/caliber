package com.revature.caliber.test.integration;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Address;
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

	public static final String NOT_YET_IMPLEMENTED = "Not yet implemented";
	private static Logger log = Logger.getLogger(ReportingServiceTest.class);
	public static List<Trainee> trainees;
	ReportingService reportingService;

	@Autowired
	public void setReportingService(ReportingService reportingService) {
		this.reportingService = reportingService;
	}

	/*
	 * Arrange Dummy Data to look like this
	 * 
	 * |  Trainee  |   Week 1  |   Week 2  |   Week 3  |   Week 4  |   Week 5  |
	 * |-----------|-----------|-----------|-----------|-----------|-----------|
	 * | Trainee 1 |     10,50 |     20,50 |     30,50 |     40,50 |     50,50 |
	 * | Trainee 2 |     20,50 |     30,50 |     40,50 |     50,50 |     60,50 |
	 * | Trainee 3 |     30,50 |     40,50 |     50,50 |     60,50 |     70,50 |
	 * 
	 * Everything past that amount of detail doesn't really matter
	 * 
	 */
	@BeforeClass
	public static void beforeClass() {
		trainees = new ArrayList<>();
		Batch batch = new Batch();
		batch.setWeeks(5);
		for (int i = 1; i < 4; i++) {
			Trainee trainee = new Trainee("Trainee" + i, "java", "email@email.com", batch);
			trainee.setTraineeId(i);
			Set<Grade> grades = new HashSet<Grade>();
			for (int j = 1; j < 6; j++) {
				Assessment assess1 = new Assessment("A title:" + j, batch, 100, AssessmentType.Exam, j, new Category());
				Assessment assess2 = new Assessment("Another title:" + j, batch, 100, AssessmentType.Exam, j,
						new Category());
				grades.add(new Grade(assess1, trainee, new Date(), j * 10 + (i - 1) * 10));
				grades.add(new Grade(assess2, trainee, new Date(), 50));
			}
			trainee.setGrades(grades);
			trainees.add(trainee);
		}
	}

	/**
	 * Create several Dummy grades with dummy assessments and dummy categories
	 * Run tested method and test to see if: -result set is right size -results
	 * are the correct values
	 * 
	 * Method should be flexible enough to allow any list of grades with
	 * categories
	 **/
	@Test
	public void testUtilAvgSkills() {
		log.info("TEST UTILITY AVERAGE SKILL");
		Assessment assessment1 = new Assessment("title", new Batch(), 200, null, 5, new Category("CatOne", true));
		Assessment assessment2 = new Assessment("title two", new Batch(), 200, null, 5, new Category("CatTwo", true));
		Grade grade1 = new Grade(assessment1, null, null, 150);
		Grade grade2 = new Grade(assessment1, null, null, 200);
		Grade grade3 = new Grade(assessment2, null, null, 100);
		Grade grade4 = new Grade(assessment2, null, null, 150);
		List<Grade> grades = new ArrayList<Grade>();
		grades.add(grade1);
		grades.add(grade2);
		grades.add(grade3);
		grades.add(grade4);
		Map<Category, Double[]> results = reportingService.utilAvgSkills(grades);

		// Get all keys
		List<Category> keys = new ArrayList<Category>();
		for (Category cat : results.keySet()) {
			keys.add(cat);
		}
		// check that the result set is the right size
		assertEquals(2, keys.size());
		assertEquals(keys.get(0).getSkillCategory(), "CatOne");
		assertNotEquals(keys.get(0).getSkillCategory(), "CatTwo");
		assertEquals((Double) 175.0, results.get(keys.get(0))[0]);
		assertEquals(keys.get(1).getSkillCategory(), "CatTwo");
		assertNotEquals(keys.get(1).getSkillCategory(), "CatOne");
		assertEquals((Double) 125.0, results.get(keys.get(1))[0]);
	}

	@Test
	public void testUtilReplaceCategoryWithSkillName() {
		log.info("TEST UTILITY REPLACE CATEGORY WITH SKILL NAME");
		Map<Category, Double[]> skills = new HashMap<>();
		Double[] values = { (double) 20, (double) 10 };
		skills.put(new Category("Name One", true), values);
		skills.put(new Category("Name Two", true), values);
		Map<String, Double> replaced = reportingService.utilReplaceCategoryWithSkillName(skills);
		List<String> keys = new ArrayList<>();
		for (Entry<String, Double> entry : replaced.entrySet()) {
			keys.add(entry.getKey());
		}
		assertEquals("Name One", keys.get(0));
		assertEquals("Name Two", keys.get(1));
	}

	@Test
	public void testUtilAvgBatchWeekValue() {
		log.info("TEST UTILITY AVERAGE BATCH WEEK VALUE");
		Double actualWeekOne = reportingService.utilAvgBatchWeekValue(trainees, 1);
		Double actualWeekTwo = reportingService.utilAvgBatchWeekValue(trainees, 2);
		Double expectedWeekOne = (double) (35.0);
		Double expectedWeekTwo = (double) (40.0);
		assertEquals(expectedWeekOne, actualWeekOne);
		assertEquals(expectedWeekTwo, actualWeekTwo);
	}

	/*
	 * Test utilAvgBatch with any number of weeks entered With the weeks for the
	 * batch set to 5, I use a range from -1 to 6 to see if it works for the
	 * boundaries and even past them
	 */
	@Test
	public void testUtilAvgBatch() {
		log.info("Calculate Average Batch Grade");
		// Calculated by hand with dummy data above
		double[] posAvg = { 0.0, 35, 37.5, 40, 42.5, 45 };
		for (int i = 0; i < 6; i++) {
			assertEquals(new Double(posAvg[i]), reportingService.utilAvgBatch(trainees, i));
		}
	}

	@Test
	public void testUtilAvgTraineeOverallWithTwoParams() {
		log.info("Calculate Average grade per week for a trainee");
		// Calculated by hand with dummy data above
		double[] weekAvgs = { 30, 35, 40, 45, 50 };
		for (int i = 0; i < 5; i++) {
			assertEquals(new Double(weekAvgs[i]),
					reportingService.utilAvgTraineeOverall(trainees.get(0).getGrades(), i + 1).get(i + 1));
		}
	}

	@Test
	// Need a specific batch to get trainees
	public void testUtilAvgBatchOverallWithTwoParams() {
		log.info("Calculating batch averages per week");
		double[] overallAvg = { 35, 40, 45, 50, 55 };
		for (int i = 0; i < 5; i++) {
			assertEquals(new Double(overallAvg[i]), reportingService.utilAvgBatchOverall(trainees, i + 1).get(i + 1));
		}
	}

	@Test
	public void testUtilAvgTraineeWeekWithThreeParam() {
		log.info("UtilAvgTraineeWeekWithThreeParam Test");
		double[] possAvg = { 30.0, 100.0, 2.0 };
		Double[] actual = reportingService.utilAvgTraineeWeek(1, AssessmentType.Exam, trainees.get(0).getGrades());
		for (int i = 0; i < 3; i++) {
			assertEquals(new Double(possAvg[i]), actual[i]);
		}
	}

	@Test
	public void testUtilAvgBatchWeekWithThreeParam() {
		log.info("UtilAvgBatchWeekWithThreeParam Test");
		Map<Trainee, Double[]> actual = new HashMap<>();
		double[] possAvg = { 30.0, 35.0, 40.0 };
		int pos = 0;
		actual = reportingService.utilAvgBatchWeek(trainees, 1, AssessmentType.Exam);
		for (Trainee trainee : trainees) {
			assertEquals(new Double(possAvg[pos]), actual.get(trainee)[0]);
			pos++;
		}
	}

	@Test
	public void testUtilAvgTraineeOverallWithThreeParam() {
		log.info("UtilAvgTraineeOverallWithThreeParam Test");
		int weeks = 5;
		Map<Integer, Double[]> actual = new HashMap<>();
		double[] possAvg = { 30.0, 35.0, 40.0, 45.0, 50.0 };
		actual = reportingService.utilAvgTraineeOverall(trainees.get(0).getGrades(), AssessmentType.Exam, weeks);
		for (int i = 0; i < weeks; i++) {
			assertEquals(new Double(possAvg[i]), actual.get(i + 1)[0]);
		}
	}

	@Test
	public void utilAvgBatchOverallWithThreeParams() {
		log.info("Calculate Average Batch Grade");
		AssessmentType assessmentType = AssessmentType.Exam;
		Map<Integer, Double[]> results = reportingService.utilAvgBatchOverall(trainees, assessmentType, 3);
		double[] possAvg = { 35.0, 40.0, 45.0 };
		for (int i = 1; i < 4; i++) {
			log.info(results.get(i));
			assertEquals(new Double(possAvg[i - 1]), results.get(i)[0]);
		}
	}

	// returns average grade for one trainee in a given week
	@Test
	public void utilAvgTraineeWeekWithTwoParams() {
		log.info("Calculate One Trainee's Average for all Exams in a Given Week");
		Set<Grade> grades = new HashSet<Grade>();
		Double expectedAverage = 30.0;
		int selectedTrainee = 0; // Selects first trainee in dummy batch
		grades = trainees.get(selectedTrainee).getGrades();
		Double avg = 0d;
		for (Grade g : grades)
			avg += g.getScore();
		Double actualAverage = reportingService.utilAvgTraineeWeek(grades, 1);
		assertEquals(expectedAverage, actualAverage);
	}

	// returns average for each trainee in a given week
	@Test
	public void utilAvgBatchWeekWithTwoParams() {
		log.info("Calculate Each Trainee's Average for a Given Week");
		Set<Grade> grades = new HashSet<Grade>();
		double[] averages = { 30.0, 35.0, 40.0 }; // Week 1 averages for all 3 trainees
		for (int i = 0; i < 3; i++) {
			grades = trainees.get(i).getGrades();
			Double avg = 0d;
			for (Grade g : grades)
				avg += g.getScore();
			avg = reportingService.utilAvgTraineeWeek(grades, 1);
			assertEquals(new Double(averages[i]), avg);
		}
	}



	@Autowired
	public BatchDAO batchDAO;
	
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
		log.info(avg);
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
	 * @see com.revature.caliber.services.ReportingService.getBatchOverallBarChart
	 */
	@Test
	public void getBatchOverallBarChartTest() {
		log.info("Testing getBatchOverallBarChart(int batchId)");
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
	 * @see com.revature.caliber.services.ReportingService.getBatchWeekTraineeBarChart
	 */
	@Test
	public void getBatchWeekTraineeBarChartTest() {
		log.info("Testing getBatchWeekTraineeBarChart(int batchId, int TraineeId, int week)");
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
		//Test non-existent week.
		results = reportingService.getBatchWeekTraineeBarChart(2100, 5455, -1000);
		assertTrue("Check invalid week", results.size() == 0);
	}
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.services.ReportingService.getBatchOverallTraineeBarChart
	 */
	@Test
	public void getBatchOverallTraineeBarChartTest() {
		log.info("Testing getBatchOverallTraineeBarChart(int batchId, int TraineeId)");
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
	 * Tests methods:
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
	
	/**
	 * Tests methods:
	 * com.revature.caliber.services.ReportingService.getBatchWeekAvgBarChart(int batchId, int week)
	 */
	@Test
	public void getBatchWeekAvgBarChartTest() {
		log.info("Testing getBatchWeekAvgBarChartTest");
		Map <String, Double[]> weekAvgBarChart = reportingService.getBatchWeekAvgBarChart(2201, 5);
		assertEquals((Double) 88.75, (Double) weekAvgBarChart.get("Project")[0]);
		assertEquals((Double) 76.109375, (Double) weekAvgBarChart.get("Exam")[0]);
		assertEquals((Double) 74.375, (Double) weekAvgBarChart.get("Verbal")[0]);	
	}
	
	/**
	 * Tests method:
	 * com.revature.caliber.services.ReportingService.getBatchWeekSortedBarChart(int batchId, int week)
	 */
	@Test
	public void getBatchWeekSortedBarChartTest(){
		log.info("getBatchWeekSortedBarChartTest");
		Map<String, Double> test =reportingService.getBatchWeekSortedBarChart(2050, 2);
		assertEquals((Double)96.29, (Double)test.get("Fouche, Issac"));
		assertEquals((Double) 89.63, (Double)test.get("Castillo, Erika"));
		assertEquals(6, test.size());
	}
	
	/**
	 * Tests methods:
	 * com.revature.caliber.services.ReportingService.getBatchWeekPieChart(Integer batchId, Integer weekNumber)
	 */
	@Test
	public void getBatchWeekPieChartTest() {
		log.info("Testing getBatchWeekPieChart");
		Map<QCStatus, Integer> pieChart = reportingService.getBatchWeekPieChart(2201, 7);
		assertEquals( (Integer) 0, (Integer) pieChart.get(QCStatus.Superstar));
		assertEquals( (Integer) 9, (Integer) pieChart.get(QCStatus.Good));
		assertEquals( (Integer) 0, (Integer) pieChart.get(QCStatus.Average));
		assertEquals( (Integer) 7, (Integer) pieChart.get(QCStatus.Poor));
	}
	
	/**
	 * Tests methods:
	 * com.revature.caliber.services.ReportingService.pieChartCurrentWeekQCStatus(Integer batchId)
	 */
	@Test
	public void pieChartCurrentWeekQCStatusTest() {
		log.info("Testing pieChartCurrentWeekQCStatus(int batchId)");
		Integer batchId = 2201;	
		Map<QCStatus, Integer> pieChart = reportingService.pieChartCurrentWeekQCStatus(batchId);
		
		assertNotNull(pieChart);
		assertEquals( (Integer) 0, (Integer) pieChart.get(QCStatus.Superstar));
		assertEquals( (Integer) 9, (Integer) pieChart.get(QCStatus.Good));
		assertEquals( (Integer) 0, (Integer) pieChart.get(QCStatus.Average));
		assertEquals( (Integer) 7, (Integer) pieChart.get(QCStatus.Poor));
	}
	
	/**
	 *  Tests methods:
	 *  com.revature.caliber.services.ReportingService.getAllBatchesCurrentWeekQCStackedBarChart
	 */
	@Test
	public void getAllBatchesCurrentWeekQCStackedBarChartTest() {
		log.info("Testing getAllBatchesCurrentWeekQCStackedBarChart()");
		
		List<Object> object = reportingService.getAllBatchesCurrentWeekQCStackedBarChart();
		@SuppressWarnings("unchecked")
		Map<String, Object> test = (Map<String, Object>) object.get(0);
		
		// find a way to acquire the map separately, then iterate through its keys
		
		@SuppressWarnings("unchecked")
		Map<QCStatus, Integer> qcStatus = (Map<QCStatus, Integer>) test.get("qcStatus");
		
		//asserts batch ID
		assertEquals((Integer) 2201, (Integer) test.get("id"));
		
		//asserts QCStatus values for Poor, Good, Superstar, and Average
		assertEquals((Integer) 7, (Integer) qcStatus.get(QCStatus.Poor));
		assertEquals((Integer) 9, (Integer) qcStatus.get(QCStatus.Good));
		assertEquals((Integer) 0, (Integer) qcStatus.get(QCStatus.Superstar));
		assertEquals((Integer) 0, (Integer) qcStatus.get(QCStatus.Average));
		
		//asserts the label
		assertEquals((String)"2017-09-13...Patrick", (String) test.get("label"));
		
		//asserts the address
		Address address = (Address) test.get("address");
	
		assertEquals("65-30 Kissena Blvd, CEP Hall 2", address.getStreet());	
	}
	
	/**
	 * Method for creating two batches with grades
	 * @return
	 */
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
	
}
