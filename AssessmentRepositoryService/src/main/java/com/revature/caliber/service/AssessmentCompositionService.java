package com.revature.caliber.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.model.Assessment;
import com.revature.caliber.model.Batch;
import com.revature.caliber.model.Category;
import com.revature.caliber.model.SimpleAssessment;
import com.revature.caliber.model.SimpleBatch;
import com.revature.caliber.model.SimpleCategory;
import com.revature.caliber.repository.AssessmentRepository;

@Service
public class AssessmentCompositionService {
	@Autowired
	private AssessmentRepository assessmentRepository;
	@Autowired
	private AssessmentCompositionMessagingService assessmentCompositionMessagingService;

	/**
	 * SAVE ASSESSMENT
	 *
	 * @param assessment
	 */
	public void save(SimpleAssessment assessment) {
		assessmentRepository.save(assessment);
	}

	public void delete(long id) {
		assessmentRepository.delete(id);
	}

	/**
	 * Saves a Assessment by instantiating a new SimpleAssessment from the
	 * Assessment and calling the AssessmentRepository save method.
	 * 
	 * @param assessment
	 *            The Assessment that will be saved
	 */
	public SimpleAssessment save(Assessment assessment) {
		SimpleAssessment simpleAssessment = new SimpleAssessment(assessment);

		return assessmentRepository.save(simpleAssessment);
	}

	/**
	 * Returns the Assessment that is associated with the given assessmentId
	 * 
	 * @param assessmentId
	 *            The ID that identifies the Assessment
	 * @return The Assessment constructed from the SimpleAssessment saved in the
	 *         database
	 */
	public Assessment findOne(long id) {
		SimpleAssessment basis = assessmentRepository.findOne(id);
		Assessment result = composeAssessment(basis);

		return result;
	}

	/**
	 * Returns List of all Assessments
	 * 
	 * @return List of Assessments
	 */
	public List<Assessment> findAll() {
		List<SimpleAssessment> basis = assessmentRepository.findAll();
		List<Assessment> assessments = composeListOfAssessments(basis);

		return assessments;
	}

	/**
	 * Returns List of all Assessments of the given batchId
	 * 
	 * @param batchId
	 *            The batchId to search for Assessments with
	 * @return List of Assessments with the batchId
	 */
	public List<Assessment> findByBatchId(Integer batchId) {
		List<SimpleAssessment> basis = assessmentRepository.findByBatchId(batchId);
		List<Assessment> assessments = composeListOfAssessments(basis);

		return assessments;
	}

	/**
	 * Returns List of all Assessments of the given week
	 * 
	 * @param week
	 *            The week to search for Assessments with
	 * @return List of Assessments with the week
	 */
	public List<Assessment> findByWeek(Short week) {
		List<SimpleAssessment> basis = assessmentRepository.findByWeek(week);
		List<Assessment> assessments = composeListOfAssessments(basis);

		return assessments;
	}

	/**
	 * Returns List of all Assessments of a given batchId and week
	 * 
	 * @param batchId
	 *            The batchId to search for Assessments with
	 * @param week
	 *            The week to search for Assessments with
	 * @return List of Assessments with the batchId and week
	 */
	public List<Assessment> findByBatchIdAndWeek(Integer batchId, Short week) {
		List<SimpleAssessment> basis = assessmentRepository.findByBatchIdAndWeek(batchId, week);
		System.out.println(basis);
		List<Assessment> assessments = composeListOfAssessments(basis);

		return assessments;
	}

	/**
	 * Returns List of all Assessments of the given categoryId
	 * 
	 * @param categoryId
	 *            The categoryId to search for Assessments with
	 * @return List of Assessments with the categoryId
	 */
	public List<Assessment> findByCategoryId(Integer categoryId) {
		List<SimpleAssessment> basis = assessmentRepository.findByCategoryId(categoryId);
		List<Assessment> assessments = composeListOfAssessments(basis);

		return assessments;
	}

	/**
	 * Updates a Assessment. Save also handles update, so just call the save method.
	 * 
	 * @param assessment
	 *            The Assessment that will be updated
	 */
	public SimpleAssessment update(Assessment assessment) {
		SimpleAssessment simpleAssessment = new SimpleAssessment(assessment);

		return assessmentRepository.save(simpleAssessment);
	}

	/**
	 * Deletes a Assessment using its ID. Calls the AssessmentRepository method for
	 * deletion, gives the ID as parameter
	 * 
	 * @param assessment
	 *            The Assessment to be deleted
	 */
	public void delete(Assessment assessment) {
		assessmentRepository.delete(assessment.getAssessmentId());
	}

	/**
	 * Deletes the Assessment with the given assessmentId as well as sends a message
	 * to the Grade Service to delete any Grades that are tied to the deleted
	 * Assessment
	 * 
	 * @param assessmentId
	 *            The assessmentId of the requested to delete Assessment
	 */
	public void deleteByAssessmentId(Long assessmentId) {
		assessmentCompositionMessagingService.sendGradeDeleteRequestForAssessmentId(assessmentId);
		assessmentRepository.deleteByAssessmentId(assessmentId);
	}

	/**
	 * Deletes all Assessments of the given batchId as well as sends a message to
	 * the Grade Service to delete all Grades that are tied to the deleted
	 * Assessments
	 * 
	 * @param batchId
	 *            The batchId of the requested to delete Assessments
	 */
	public void deleteByBatchId(Integer batchId) {
		for (SimpleAssessment sa : assessmentRepository.findByBatchId(batchId)) {
			assessmentCompositionMessagingService.sendGradeDeleteRequestForAssessmentId(sa.getAssessmentId());
		}
		assessmentRepository.deleteByBatchId(batchId);
	}

	/**
	 * Composes a list of Assessments from a list of SimpleAssessments
	 * 
	 * @param simpleAssessments
	 *            The list of SimpleAssessments to convert to Assessments
	 * @return List of Assessments converted from a List of SimpleAssessments
	 */
	private List<Assessment> composeListOfAssessments(List<SimpleAssessment> simpleAssessments) {
		List<Assessment> assessments = new LinkedList<Assessment>();

		for (SimpleAssessment simpleAssessment : simpleAssessments) {
			Assessment assessment = composeAssessment(simpleAssessment);
			assessments.add(assessment);
		}

		return assessments;
	}

	/**
	 * Composes a Assessment from a SimpleAssessment by messaging the Batch and
	 * Category services for a SimpleBatch and SimpleCategory according to the
	 * batchId and categoryId stored in the SimpleAssessment.
	 * 
	 * @param simpleAssessment
	 *            The SimpleAssessment to be converted
	 * @return The Assessment composed from information in the SimpleAssessment
	 */
	private Assessment composeAssessment(SimpleAssessment simpleAssessment) {
		Assessment assessment = new Assessment(simpleAssessment);

		SimpleBatch simpleBatch = assessmentCompositionMessagingService
				.sendSingleSimpleBatchRequest(simpleAssessment.getBatchId());
		Batch batch = new Batch(simpleBatch);
		assessment.setBatch(batch);

		SimpleCategory simpleCategory = assessmentCompositionMessagingService
				.sendSingleSimpleCategoryRequest(simpleAssessment.getCategoryId());
		Category category = new Category(simpleCategory);
		assessment.setCategory(category);

		return assessment;
	}

	/**
	 * Saves a SimpleAssessment that only has its categoryId and batchId set.
	 * categoryId and batchId are retrieved respectively from the Category service
	 * through the category param and from the Batch service through the stringified
	 * batchResourceId param
	 * 
	 * @param category
	 * @param batchResourceId
	 */
	public void createAssessmentFromDTO(String category, String batchResourceId) {
		SimpleBatch simpleBatch = assessmentCompositionMessagingService.sendSingleSimpleBatchRequest(batchResourceId);
		SimpleCategory simpleCategory = assessmentCompositionMessagingService.sendSingleSimpleCategoryRequest(category);
		SimpleAssessment simpleAssessment = new SimpleAssessment();

		simpleAssessment.setBatchId(simpleBatch.getBatchId());
		simpleAssessment.setCategoryId(simpleCategory.getCategoryId());

		assessmentRepository.save(simpleAssessment);
	}
}