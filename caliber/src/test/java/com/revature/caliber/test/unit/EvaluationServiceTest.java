package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.services.EvaluationService;

public class EvaluationServiceTest extends CaliberTest{

	private static final int TEST_BATCH_ID = 2150;
	private static final int TEST_ASSESSMENT_WEEK = 7;

	
	
	@Autowired
	EvaluationService evaluationService;
	@Autowired
	BatchDAO batchDAO;
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	// EVALUATION SERVICE
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test methods:
	 * 
	 * @see com.revature.caliber.services.EvaluationService#findGradesByWeek(Integer, Integer)
	 * 
	 */
	@Test
	public void findGradesByWeek(){
		//get test batch
		Batch batch = batchDAO.findOne(TEST_BATCH_ID);
		
		//get grades
		Map<Integer,List<Grade>> grades = 
				evaluationService.findGradesByWeek(batch.getBatchId(), TEST_ASSESSMENT_WEEK);
		
		//assert size of result as expected in test data
		assertEquals(13, grades.size());
		
		//iterate through map entries and assert it is of test week
		for (Map.Entry<Integer, List<Grade>> entry : grades.entrySet())
		{
		    List<Grade> weekGrades = entry.getValue();
		    for(Grade grade: weekGrades){
		    	assertEquals(TEST_ASSESSMENT_WEEK, grade.getAssessment().getWeek());
		    }
		}
		
		
	}
}
