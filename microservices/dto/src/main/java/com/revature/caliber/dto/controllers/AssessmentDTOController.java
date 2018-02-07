package com.revature.caliber.dto.controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.dto.beans.Assessment;
import com.revature.caliber.dto.beans.AssessmentType;
import com.revature.caliber.dto.beans.Batch;
import com.revature.caliber.dto.beans.Category;
import com.revature.caliber.dto.services.AssessmentService;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin(origins = "http://ec2-54-163-132-124.compute-1.amazonaws.com")
public class AssessmentDTOController {
	
	private static final Logger log = Logger.getLogger(AssessmentDTOController.class);
	private AssessmentService assessmentService;
	
	@Autowired
	public void setAssessmentService(AssessmentService assessmentService) {
		this.assessmentService = assessmentService;
	}
	
	/**
	 * Creates an Assessment with only a skill category and batch id passed in.
	 * The Assessment is built up by calling various services to fill in missing information.
	 * The Assessment is passed to the assessment service to be handled.
	 * @param json A Map<String, String> derived from a Json containing only a "skillCategory" and "batchId" field
	 * @return HttpStatus
	 */
	@RequestMapping(value = "/dto/assessment/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("permitAll()")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<Long> createAssessment(@RequestBody Map<String, String> json) {
		String skillCategory = json.get("category");
		int batchId = Integer.parseInt(json.get("batch"));
		
		String title = "";
		Batch batch = trainingService.findBatch(batchId);
		int rawScore = 70;
		AssessmentType type = AssessmentType.Exam;
		int week = 1;
		
		Category category = null;
		List<Category> categories = categoryService.findAll();
		Iterator<Category> categoryIterator = categories.iterator();
		while(categoryIterator.hasNext()) {
			Category tmp = categoryIterator.next();
			if (tmp.getSkillCategory() == skillCategory) {
				category = tmp;
				break;
			}
		}
		if (category == null) {
			category = new Category(skillCategory, true);
			categoryService.saveCategory(category);
		}
		
		Assessment assessment = new Assessment(title, batch, rawScore, type, week, category);
		assessmentService.save(assessment);
		log.info("MSA Creating assessment: " + assessment);
		
		return new ResponseEntity<>(assessment.getAssessmentId(), HttpStatus.CREATED);
	}
}