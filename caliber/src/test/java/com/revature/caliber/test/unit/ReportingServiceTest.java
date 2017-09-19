package com.revature.caliber.test.unit;

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
import com.revature.caliber.data.AssessmentDAO;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.services.ReportingService;
import com.revature.caliber.test.integration.AssessmentTest;

public class ReportingServiceTest extends CaliberTest{
	private static final Logger log = Logger.getLogger(AssessmentTest.class);
	@Autowired
	ReportingService reportingService;
	
	/*
	 * Arrange Dummy Data to look like this
	 * 
	 * |  Trainee  |   Week 1  |   Week 2  |   Week 3  |   Week 4  |   Week 5  |
	 * |-----------|-----------|-----------|-----------|-----------|-----------|
	 * | Trainee 1 |     10,20 |     20,40 |     30,60 |     40,80 |    50,100 |
	 * | Trainee 2 |     20,45 |     30,65 |     40,85 |    50,105 |    60,125 |
	 * | Trainee 3 |     30,70 |     40,90 |    50,110 |    60,130 |    70,150 |
	 * 
	 * Everything past that amount of detail doesn't really matter
	 * 
	 */
	private static List<Trainee> trainees;
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
				Assessment assess1 = new Assessment("A title:" + j, batch, 200, AssessmentType.Exam, j, new Category());
				Assessment assess2 = new Assessment("Another title:" + j, batch, 200, AssessmentType.Exam, j, new Category());
				grades.add(new Grade(assess1, trainee, new Date(), j*10 + (i-1)*10));
				grades.add(new Grade(assess2, trainee, new Date(), j*20 + (i-1)*5));
			}
			trainee.setGrades(grades);
			trainees.add(trainee);
		}
	}

	
	/**
	 * Create several Dummy grades with dummy assessments and dummy categories
	 * Run tested method and test to see if: 
	 * 	-result set is right size
	 * 	-results are the correct values
	 * 
	 * Method should be flexible enough to allow any list of grades with categories 
	 **/
	@Test
	public void testUtilAvgSkills() {
		log.info("TEST UTILITY AVERAGE SKILL");
		Assessment assessment1 = new Assessment("title", new Batch(), 200, null, 5,  new Category("CatOne",true));
		Assessment assessment2 = new Assessment("title two", new Batch(), 200, null, 5,  new Category("CatTwo",true));		
		Grade grade1 = new Grade(assessment1, null, null, 150);
		Grade grade2 = new Grade(assessment1, null, null, 200);
		Grade grade3 = new Grade(assessment2, null, null, 100);
		Grade grade4 = new Grade(assessment2, null, null, 150);
		List<Grade> grades = new ArrayList<Grade>();
		grades.add(grade1);
		grades.add(grade2);
		grades.add(grade3);
		grades.add(grade4);
		Map<Category,Double[]> results = reportingService.utilAvgSkills(grades);
		
		//Get all keys
		List<Category> keys = new ArrayList<Category>();
		for(Category cat : results.keySet()) {	
			keys.add(cat);
		}
		// check that the result set is the right size
		assertEquals(2, keys.size());
		assertEquals(keys.get(0).getSkillCategory(), "CatOne");
		assertNotEquals(keys.get(0).getSkillCategory(), "CatTwo");
		assertEquals((Double)175.0,results.get(keys.get(0))[0]);
		assertEquals(keys.get(1).getSkillCategory(), "CatTwo");
		assertNotEquals(keys.get(1).getSkillCategory(), "CatOne");
		assertEquals((Double)125.0,results.get(keys.get(1))[0]);	
	}
	@Test
	public void testUtilReplaceCategoryWithSkillName() {
		log.info("TEST UTILITY REPLACE CATEGORY WITH SKILL NAME");
		Map<Category,Double[]> skills = new HashMap<>();
		Double[] values = {(double)20,(double)10};
		skills.put(new Category("Name One", true), values);
		skills.put(new Category("Name Two", true), values);		
		Map<String, Double> replaced = reportingService.utilReplaceCategoryWithSkillName(skills);
		List<String> keys = new ArrayList<>();
		for(Entry<String, Double> entry : replaced.entrySet()) {
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
		Double expectedWeekOne = (double) (22.5);
		Double expectedWeekTwo = (double) (37.5);
		assertEquals(expectedWeekOne, actualWeekOne);
		assertEquals(expectedWeekTwo, actualWeekTwo);
	}
	
}
