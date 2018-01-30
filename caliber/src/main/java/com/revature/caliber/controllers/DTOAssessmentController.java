package com.revature.caliber.controllers;

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

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.AssessmentType;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Category;
import com.revature.caliber.services.AssessmentService;
import com.revature.caliber.services.CategoryService;
import com.revature.caliber.services.TrainingService;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin(origins = "http://ec2-54-163-132-124.compute-1.amazonaws.com")
public class DTOAssessmentController {
	private static final Logger log = Logger.getLogger(DTOAssessmentController.class);
	private AssessmentService assessmentService;
	private TrainingService trainingService;
	private CategoryService categoryService;
	
	@Autowired
	public void setAssessmentService(AssessmentService assessmentService) {
		this.assessmentService = assessmentService;
	}
	
	@Autowired
	public void setTrainingService(TrainingService trainingService) {
		this.trainingService = trainingService;
	}
	
	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	/*
	@RequestMapping(value = "/dto/get/{assessmentId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("permitAll()")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<Assessment> findAssessmentById(@PathVariable Integer assessmentId) {
		Assessment assessment = assessmentService.findAssessment(assessmentId);
		log.info("msa test got assessment: " + assessment);
		if (assessment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		int batchId = assessment.getBatch().getBatchId();
		assessment.setBatch(trainingService.findBatch(batchId));
		log.info("msa test post-fill assessment: " + assessment);
		return new ResponseEntity<>(assessment, HttpStatus.OK);
	}
	*/
	
	@RequestMapping(value = "/dto/create/assessment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("permitAll()")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<Void> createAssessment(@RequestBody Map<String, String> json) {
		String skillCategory = json.get("skillCategory");
		int batchId = Integer.parseInt(json.get("batchId"));
		
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
		log.info("msa test create assessment: " + assessment);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
