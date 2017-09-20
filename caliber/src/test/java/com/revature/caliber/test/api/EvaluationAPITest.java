package com.revature.caliber.test.api;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.revature.caliber.beans.Grade;
import com.revature.caliber.data.GradeDAO;

public class EvaluationAPITest extends AbstractAPITest{

	private static final Logger log = Logger.getLogger(EvaluationAPITest.class);
	
	@Test
	public void createGrade(Grade grade){
		GradeDAO gradeDAO = new GradeDAO();
		log.info("");
	}
	@Test
	public void updateGrade(Grade grade){
		
	}
	@Test
	public void findAll(){
		
	}
	@Test
	public void findByAssessment(Long assessmentId){
		
	}
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByTrainee(Integer)
	 */
	@Test
	public void findByTrainee(){
		
	}
	
	/**
	 * @see com.revature.caliber.controllers.EvaluationController#findByBatch(Integer)
	 */
	@Test
	public void findByBatch(){
		
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByCategory(Integer)
	 */
	@Test
	public void findByCategory(){
		
	}
	
	/**
	 * @see com.revature.caliber.controllers.EvaluationController#findByWeek(Integer, Integer)
	 */
	@Test
	public void findByWeek(){
		
	}
	
	/**
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByTrainer(Integer)
	 * 
	 */
	@Test
	public void findByTrainer(){
		
	}
}
