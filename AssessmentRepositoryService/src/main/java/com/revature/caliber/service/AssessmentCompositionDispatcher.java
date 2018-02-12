package com.revature.caliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.Assessment;

@Service
public class AssessmentCompositionDispatcher {
	@Autowired
	private AssessmentCompositionService acs;
	
	public List<Assessment> processAssessmentRequest(JsonObject request){
		String methodName = request.get("methodName").getAsString();
		if(methodName.equals("findByBatchIdAndWeek")) {
			return acs.findByBatchIdAndWeek(request.get("batchId").getAsInt(), request.get("week").getAsShort());
		}else if(methodName.contains("findAll")) {
			return acs.findAll();
		}else if(methodName.contains("findByBatchId")) {
			return acs.findByBatchId(request.get("batchId").getAsInt());
		}else if(methodName.contains("findByWeek")) {
			return acs.findByWeek(request.get("week").getAsShort());
		}else if(methodName.contains("findByCategoryId")) {
			return acs.findByCategoryId(request.get("categoryId").getAsInt());
		}
		return null;
	}
	
}
