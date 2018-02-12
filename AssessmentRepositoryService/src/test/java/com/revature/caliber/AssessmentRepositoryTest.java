package com.revature.caliber;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.caliber.model.AssessmentType;
import com.revature.caliber.model.SimpleAssessment;
import com.revature.caliber.repository.AssessmentRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AssessmentRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private AssessmentRepository assessmentRepository;

	private SimpleAssessment testAssessment;

	@Before
	public void initializeTestAssessment() {
		testAssessment = new SimpleAssessment();
		testAssessment.setRawScore(77);
		testAssessment.setTitle("TITLE");
		testAssessment.setType(AssessmentType.Other);
		testAssessment.setWeek((short) 7);
		testAssessment.setBatchId(11);
		testAssessment.setCategoryId(1);
	}
	
	@Test
	public void testMethodFindOne() {
		Long addedAssessmentId = this.entityManager.merge(testAssessment).getAssessmentId();
		SimpleAssessment assessment = this.assessmentRepository.findOne(addedAssessmentId);
		assertThat(assessment).isEqualTo(testAssessment);
	}
	
	@Test
	public void testMethodFindAllTest() {
		this.entityManager.merge(testAssessment).getAssessmentId();
		List<SimpleAssessment> assessments = assessmentRepository.findAll();
		assertThat(assessments).isNotEmpty();
	}

	@Test
	public void testMethodFindByAssessmentId() {
		Long addedAssessmentId = this.entityManager.merge(testAssessment).getAssessmentId();
		SimpleAssessment assessment = this.assessmentRepository.findByAssessmentId(addedAssessmentId);
		assertThat(assessment).isEqualTo(testAssessment);
	}

	@Test
	public void testMethodFindDistinctByBatchId() {
		this.entityManager.merge(testAssessment);
		List<SimpleAssessment> assessments = this.assessmentRepository.findByBatchId(testAssessment.getBatchId());
		assertThat(assessments).contains(testAssessment);
	}

	@Test
	public void testMethodFindDistinctByWeek() {
		this.entityManager.merge(testAssessment);
		List<SimpleAssessment> assessments = this.assessmentRepository.findByWeek(testAssessment.getWeek());
		assertThat(assessments).contains(testAssessment);
	}

	@Test
	public void testMethodFindByBatchIdAndWeek() {
		this.entityManager.merge(testAssessment);
		List<SimpleAssessment> assessments = this.assessmentRepository.findByBatchIdAndWeek(testAssessment.getBatchId(),
				testAssessment.getWeek());
		assertThat(assessments).contains(testAssessment);
	}

	@Test
	public void testMethodFindByCategoryId() {
		this.entityManager.merge(testAssessment);
		List<SimpleAssessment> assessments = this.assessmentRepository.findByCategoryId(testAssessment.getCategoryId());
		System.out.println("\n\n\n" + assessments + "\n\n\n");
		assertThat(assessments).contains(testAssessment);
	}

	@Test
	public void testMethodDeleteByAssessmentId() {
		Long addedAssessmentId = this.entityManager.merge(testAssessment).getAssessmentId();
		SimpleAssessment assessment = this.assessmentRepository.findByAssessmentId(addedAssessmentId);
		assertThat(assessment).isNotNull();
		this.assessmentRepository.deleteByAssessmentId(addedAssessmentId);
		assessment = this.assessmentRepository.findByAssessmentId(addedAssessmentId);
		assertThat(assessment).isNull();
	}

	@Test
	public void testMethodDeleteByBatchId() {
		this.entityManager.merge(testAssessment).getAssessmentId();
		List<SimpleAssessment> assessments = this.assessmentRepository.findByBatchId(testAssessment.getBatchId());
		assertThat(assessments).isNotEmpty();
		this.assessmentRepository.deleteByBatchId(testAssessment.getBatchId());
		assessments = this.assessmentRepository.findByBatchId(testAssessment.getBatchId());
		assertThat(assessments).isEmpty();
	}
}
