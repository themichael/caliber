package com.revature.caliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.Grade;
import com.revature.caliber.model.SimpleGrade;
import com.revature.caliber.repository.GradeRepository;

@Service
public class GradeRepositoryRequestDispatcher {
	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private GradeCompositionService gradeCompositionService;
	
	
	/**
	 * Takes in a request with methods findOne, delete in json request and 
	 * will call functions matching the one in the request
	 * It will return a Simple Grade. 
	 * 
	 * @param request
	 * @return SimpleGrade result
	 */
	
	public SimpleGrade processSingleSimpleGradeRequest(JsonObject request) {
		SimpleGrade result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findOne")) {
			Long gradeId = request.get("gradeId").getAsLong();
			result = gradeRepository.findOne(gradeId);
		} else if(methodName.equals("delete")) {
			if(request.has("traineeId")) {
				Integer traineeId = request.get("traineeId").getAsInt();
				gradeRepository.deleteByTraineeId(traineeId);
			}
			else if(request.has("assessmentId")) {
				Long assessmentId = request.get("assessmentId").getAsLong();
				gradeRepository.deleteByAssessmentId(assessmentId);
			}
			
		}
		
		return result;
	}
	
	
	/**
	 * Takes in a request with methods findAllby* in json request
	 * and will call functions matching the one in the request
	 * It will return a List of Simple Grades 
	 * 
	 * @param request
	 * @return List<SimpleGrade> result
	 */
	public List<SimpleGrade> processListSimpleGradeRequest(JsonObject request){
		List<SimpleGrade> result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findAll")) {
			result = gradeRepository.findAll();
		} else if(methodName.equals("findAllByTraineeId")) {
			result = gradeRepository.findByTraineeId(request.get("traineeId").getAsInt());
		} else if(methodName.equals("findAllByBatchId")) {
			result = gradeRepository.findByAssessmentId(request.get("batchId").getAsLong());
		}
		
		return result;
	}
	
	
	/**
	 * Takes in a request with methods findByTrainee or findByBatch in json request
	 * and will call functions matching the one in the request
	 * It will return a List of Complex Grades 
	 * 
	 * @param request
	 * @return List<SimpleGrade> result
	 */
	public List<Grade> processListComplexGradeRequest(JsonObject request){
		List<Grade> result = null;
		String methodName = request.get("methodName").getAsString();
		System.out.println(request);
		if(methodName.equals("findByTrainee")) {
			result = gradeCompositionService.findByTrainee(request.get("traineeId").getAsInt());
		} else if (methodName.equals("findByBatch")) {
			result = gradeCompositionService.findByBatch(request.get("batchId").getAsInt());
		}
		return result;
	}

	public Grade processGradeDTORequest(JsonObject request) {
		Grade result = null;
		
		gradeCompositionService.saveOrUpdateGradeFromDTO(request.get("assessmentId").getAsLong(), request.getAsDouble(), request.get("trainee").getAsString());
		
		return result;
	}
}
