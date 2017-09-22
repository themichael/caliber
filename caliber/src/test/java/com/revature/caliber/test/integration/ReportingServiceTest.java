package com.revature.caliber.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import java.util.List;
import java.util.Map;

import java.util.Map.Entry;
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
import com.revature.caliber.beans.Trainee;
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
		double[] posAvg = { 0.0, 0.0, 35, 37.5, 40, 42.5, 45 };
		for (int i = 0; i < 7; i++) {
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
}
