package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.revature.caliber.model.SimpleAssessment;

@RepositoryRestResource(collectionResourceRel = "assessment", path = "assessment")
public interface AssessmentRepository extends JpaRepository<SimpleAssessment, Long> {

	SimpleAssessment findByAssessmentId(@Param("assessmentId") Long assessmentId);

	List<SimpleAssessment> findByBatchId(@Param("batchId") Integer batchId);

	List<SimpleAssessment> findByWeek(@Param("week") Short week);

	List<SimpleAssessment> findByBatchIdAndWeek(@Param("batchId") Integer batchId, @Param("week") Short week);

	List<SimpleAssessment> findByCategoryId(@Param("categoryId") Integer categoryId);

	void deleteByAssessmentId(@Param("assessmentId") Long assessmentId);

	void deleteByBatchId(@Param("batchId") Integer batchId);
}
