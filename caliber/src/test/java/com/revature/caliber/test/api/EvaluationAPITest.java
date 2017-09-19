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
}
