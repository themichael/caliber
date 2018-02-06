package com.revature.caliber.controllers;

import java.util.Date;
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
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.services.AssessmentService;
import com.revature.caliber.services.CategoryService;
import com.revature.caliber.services.EvaluationService;
import com.revature.caliber.services.TrainingService;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin(origins = "http://ec2-54-163-132-124.compute-1.amazonaws.com")
public class DTOController {
	private static final Logger log = Logger.getLogger(DTOController.class);
	private AssessmentService assessmentService;
	private TrainingService trainingService;
	private CategoryService categoryService;
	private EvaluationService evaluationService;
	
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
	
	@Autowired
	public void setEvaluationService(EvaluationService evaluationService) {
		this.evaluationService = evaluationService;
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
	
	/**
	 * Creates a grade from only an Assessment Id, score, and Trainee Id.
	 * The grade is built up by building up an Assessment and Trainee object and using them to fill in missing information.
	 * The grade is passed to the evaluation service to be handled.
	 * @param json A Map<String, String> derived from a Json containing only an "assessmentId", "grade", and "trainee" field
	 * @return The created Grade and a HttpStatus
	 */
	@RequestMapping(value = "/dto/grade/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("permitAll()")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<Void> createGrade(@RequestBody Map<String, String> json) {
		int assessmentId = Integer.parseInt(json.get("assessmentId"));
		double score = Double.parseDouble(json.get("grade"));
		int traineeId = Integer.parseInt(json.get("trainee"));
		
		Assessment assessment = assessmentService.findAssessment(assessmentId);
		int batchId = assessment.getBatch().getBatchId();
		assessment.setBatch(trainingService.findBatch(batchId));
		
		Trainee trainee = trainingService.findTrainee(traineeId);
		Date dateReceived = new Date();
		
		Grade grade = new Grade(assessment, trainee, dateReceived, score);
		log.info("MSA Saving grade: " + grade);
		evaluationService.save(grade);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	/**
	 * Updates a grade from only an Assessment Id, score, and Trainee Id.
	 * The grade is built up by building up an Assessment and Trainee object and using them to fill in missing information.
	 * The grade is passed to the evaluation service to be handled.
	 * As of this moment (1/31/18) the evaluation service cannot modify existing grades, so a new grade is inserted instead.
	 * @param json A Map<String, String> derived from a Json containing only an "assessmentId", "grade", and "trainee" field
	 * @return HttpStatus
	 */
	@RequestMapping(value = "/dto/grade/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("permitAll()")
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<Void> updateGrade(@RequestBody Map<String, String> json) {
		int assessmentId = Integer.parseInt(json.get("assessmentId"));
		double score = Double.parseDouble(json.get("grade"));
		int traineeId = Integer.parseInt(json.get("trainee"));
		
		Assessment assessment = assessmentService.findAssessment(assessmentId);
		int batchId = assessment.getBatch().getBatchId();
		assessment.setBatch(trainingService.findBatch(batchId));
		
		Trainee trainee = trainingService.findTrainee(traineeId);
		
		Date gradeDate = new Date();
		Grade grade = new Grade(assessment, trainee, gradeDate, score);
		
		// Insert commented out code here if evaluation service becomes able to modify existing grades
		
		log.info("MSA Updating grade: " + grade);
		evaluationService.update(grade);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
//	// Find the grade associated with the given Assessment and Trainee Id
//	long gradeId = 0;
//	int week = assessment.getWeek();
//	Map<Integer, List<Grade>> table = evaluationService.findGradesByWeek(batchId, week);
//	
//	// Find the entry with the matching Trainee Id
//	Iterator<Entry<Integer, List<Grade>>> tableIter = table.entrySet().iterator();
//	while(tableIter.hasNext()) {
//		Entry<Integer, List<Grade>> entry = tableIter.next();
//		
//		if (entry.getKey() == trainee.getTraineeId()) {
//			// Find the matching the assessment from the list of Grades
//			Iterator<Grade> gradeIter = entry.getValue().iterator();
//			while(gradeIter.hasNext()) {
//				Grade gradeObj = gradeIter.next();
//				
//				if (gradeObj.getAssessment().getAssessmentId() == assessment.getAssessmentId()) {
//					// Extract the Grade information
//					gradeId = gradeObj.getGradeId();
//					gradeDate = gradeObj.getDateReceived();
//					break;
//				}
//			}
//		}
//	}
//	
//	if (gradeId != 0)
//		grade.setGradeId(gradeId);
//	grade.setDateReceived(gradeDate);
}
