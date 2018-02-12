package com.revature.caliber.test.unit;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.caliber.model.SimpleGrade;
import com.revature.caliber.repository.GradeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GradeRepositoryTest {

	private static final long TEST_ASSESSMENT_ID = 2063;
	private static final int TEST_TRAINEE_ID = 5529;
	
	@Autowired
	private GradeRepository gradeRepository;
	
	@Test
	public void findAllTest() {
		List<SimpleGrade> grades = gradeRepository.findAll();
		
		System.out.println(grades.size());
		
		assertFalse(grades.isEmpty());
	}
	
	@Test
	public void findGradeByTraineeTest() {
		List<SimpleGrade> grades = gradeRepository.findByTraineeId(1);
		
		for(int i = 0; i < grades.size(); i++) {
			assertEquals(grades.get(i).getTraineeId(), (Integer)1);
		}
	}
	
	@Test
	public void findGradeByAssessmentTest() {
		List<SimpleGrade> grades = gradeRepository.findByAssessmentId(2063L);
		
		for(int i = 0; i < grades.size(); i++) {
			assertEquals(grades.get(i).getAssessmentId(), (Long)2063L);
		}
	}
	
	
	@Test
	public void findOneTest() {
	
		SimpleGrade grade = gradeRepository.findOne(2063L);
		assertEquals(grade.getGradeId().longValue(), 2063L);
	}
	
	
	@Test
	public void deleteByAssessmentIdTest() {
		gradeRepository.deleteByAssessmentId(2053L);
		List<SimpleGrade> grades = gradeRepository.findByAssessmentId(2053L);
		
		
		assertEquals(grades.size(), 0);//should return an empty list
		
	}
	
	@Test
	public void deleteByTraineeIdTest() {
		gradeRepository.deleteByTraineeId(5354);
		
		List<SimpleGrade> grades = gradeRepository.findByTraineeId(5354);
		
		assertEquals(grades.size(), 0);	//should return an empty list
		
		
	}
	
	
}
