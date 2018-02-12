package com.revature.caliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.Assessment;
import com.revature.caliber.model.SimpleAssessment;
import com.revature.caliber.repository.AssessmentRepository;

@Service
public class AssessmentRepositoryRequestDispatcher {
	@Autowired
	private AssessmentRepository assessmentRepository;
	@Autowired
	private AssessmentCompositionService assessmentCompositionService;

	/**
	 * Handle a messaging request for a SimpleAssessment.
	 * 
	 * @param request
	 *            The JsonObject that defines the parameters for the
	 *            SimpleAssessment to be returned
	 * @return A SimpleAssessment according to the parameters in the request
	 */
	public SimpleAssessment processSingleSimpleAssessmentRequest(JsonObject request) {
		SimpleAssessment result = null;
		String methodName = request.get("methodName").getAsString();

		if (methodName.equals("findOne")) {
			Long assessmentId = request.get("assessmentId").getAsLong();
			result = assessmentRepository.findOne(assessmentId);
		}

		return result;
	}

	/**
	 * Handle a messaging request for a list of SimpleAssessment.
	 * 
	 * @param request
	 *            The JsonObject that defines the parameters for the list of
	 *            SimpleAssessment to be returned
	 * @return A list of SimpleAssessments according to the parameters in the
	 *         request
	 */
	public List<SimpleAssessment> processListSimpleAssessmentRequest(JsonObject request) {
		List<SimpleAssessment> result = null;
		String methodName = request.get("methodName").getAsString();

		if (methodName.equals("findAll")) {
			result = assessmentRepository.findAll();
		} else if (methodName.equals("findByWeek")) {
			Integer batchId = request.get("batchId").getAsInt();
			Short week = request.get("week").getAsShort();
			result = assessmentRepository.findByBatchIdAndWeek(batchId, week);
		} else if (methodName.equals("findByCategoryId")) {
			Integer categoryId = request.get("categoryId").getAsInt();
			result = assessmentRepository.findByCategoryId(categoryId);
		}

		return result;
	}

	/**
	 * Handle a messaging request for an Assessment.
	 * 
	 * @param request
	 *            The JsonObject that defines the parameters, category and batch
	 *            objects, for the Assessment to be returned
	 * @return An Assessment according to the parameters in the request
	 */
	public Assessment processAssessmentDTORequest(JsonObject request) {
		Assessment result = null;

		assessmentCompositionService.createAssessmentFromDTO(request.get("category").getAsString(),
				request.get("batch").getAsString());

		return result;
	}
}