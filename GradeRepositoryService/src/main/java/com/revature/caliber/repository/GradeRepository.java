package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.caliber.model.SimpleGrade;

@Repository
public interface GradeRepository extends JpaRepository<SimpleGrade, Long>{
	
	List<SimpleGrade> findAll();
	
	
	/**
	 * Delete an grade by assessmentId
	 * 
	 * @param gradeId
	 * @return
	 */
	public void deleteByAssessmentId(Long assessmentId);
	
	/**
	 * Delete an grade by traineeId
	 * 
	 * @param gradeId
	 * @return
	 */
	public void deleteByTraineeId(Integer traineeId);
	

	/**
	 * Returns all grades for an assessment. 
	 * 
	 * @param assessmentId
	 * @return
	 */
	public List<SimpleGrade> findByAssessmentId(Long assessmentId);
	
	
	
	/**
	 * Returns all grades for a trainee. Useful for generating a full-view of
	 * individual trainee performance.
	 * 
	 * @param traineeId
	 * @return
	 */
	public List<SimpleGrade> findByTraineeId(Integer traineeId);
	
}
