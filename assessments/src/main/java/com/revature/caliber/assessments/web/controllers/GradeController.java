package com.revature.caliber.assessments.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.assessments.beans.Grade;
import com.revature.caliber.assessments.service.BusinessDelegate;

@RestController
@CrossOrigin(origins = { "*" }, 
				methods = { RequestMethod.GET, 
							RequestMethod.POST, 
							RequestMethod.PUT,
							RequestMethod.DELETE }, 
				allowedHeaders = { "X-PINGOTHER", "Content-Type" },
				maxAge = 10)
public class GradeController {
	
	private BusinessDelegate delegate;

	@Autowired
	public void setDelegate(BusinessDelegate delegate) {
		this.delegate = delegate;
	}
	
	@RequestMapping(value="/grade/all",
					method=RequestMethod.GET,
					produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Grade>> getAllGrades(){
		List<Grade> grades = delegate.getAllGrades();
		if(grades == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<Grade>>(grades, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="/grade/{gradeId}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Grade> getGradesById(@PathVariable (value="gradeId") int gradeId){
		Grade grade = delegate.getGradeByGradeId(gradeId);
		if(grade == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<Grade>(grade,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/grades/trainee/{traineeId}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Grade>> getGradesByTraineeId(@PathVariable (value="traineeId") int traineeId){
		List<Grade> grades = delegate.getGradesByTraineeId(traineeId);
		if(grades == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<List<Grade>>(grades,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/grades/assessment/{assessmentId}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Grade>> getGradesByAssessmentId(@PathVariable (value="assessmentId") int assessmentId){
		List<Grade> grades = delegate.getGradesByAssesessment(assessmentId);
		if(grades == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<List<Grade>>(grades,HttpStatus.OK);
		}
	}
	

	
	

}
