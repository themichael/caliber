package com.revature.caliber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.caliber.model.SimpleCategory;
import com.revature.caliber.repository.CategoryRepository;

/**
 * Processes messages from other services.
 * FindOne, findOneBySkillCategory, and findAll are requests from other services needed
 * to construct their complex beans for the front end.
 * 
 */
@Service
public class CategoryRepositoryRequestDispatcher {

	@Autowired
	private CategoryRepository categoryRepository;
	
	/**
	 * Handle a messaging request for a simple category.
	 * @param request The JsonObject that defines the parameters for the simple category to be returned
	 * @return A simple category according to the parameters in the request
	 */
	public SimpleCategory processSingleSimpleCategoryRequest(JsonObject request) {
		SimpleCategory result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findOne")) {
			int categoryId = request.get("categoryId").getAsInt();
			result = categoryRepository.findOne(categoryId);
		}
		else if(methodName.equals("findOneBySkillCategory")) {
			String skillCategory = request.get("skillCategory").getAsString();
			result = categoryRepository.findOneBySkillCategory(skillCategory);
		}
		
		return result;
	}
	
	/**
	 * Handle a messaging request for a list of simple category.
	 * @param request The JsonObject that defines the parameters for the list of simple categories to be returned
	 * @return A list of simple category according to the parameters in the request
	 */
	public List<SimpleCategory> processListSimpleCategoryRequest(JsonObject request) {
		List<SimpleCategory> result = null;
		String methodName = request.get("methodName").getAsString();
		
		if(methodName.equals("findAll")) {
			result = categoryRepository.findAll();
		}
		
		return result;
	}
}
