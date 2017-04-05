package com.revature.caliber.services;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.caliber.beans.AssessmentType;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.AssessmentDAO;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.CategoryDAO;
import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.data.NoteDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/beans.xml" })
public class ReportingServiceTest {
	
	private static Logger log = Logger.getLogger(ReportingServiceTest.class);
	
	@Autowired
	private TrainerDAO trainerDAO;
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private BatchDAO batchDAO;
	@Autowired
	private TraineeDAO traineeDAO;
	@Autowired
	private AssessmentDAO assessmentDAO;
	@Autowired
	private GradeDAO gradeDAO;
	@Autowired
	private NoteDAO noteDAO;
	@Autowired
	private ReportingService reportingService;
	
	
	@Test
	public void getAvgBatchWeekTest(){
		Integer batchId = 1050;
		Integer week = 3;
		AssessmentType assessmentType =  AssessmentType.Project;
		Map<Trainee, Double[]> results = reportingService.getAvgBatchWeek(batchId, week, assessmentType);
		
		for(Entry<Trainee, Double[]> entry: results.entrySet()){
			if(entry.getKey().getTraineeId() == 1059){
				System.out.println("---------------------------------------------------");
				System.out.println("Trainee Name: " + entry.getKey().getName());
				System.out.println("Assessment Type: " + assessmentType);
				System.out.println("Trainee Average Score: " + entry.getValue()[0]);
				System.out.println("Total Possible Score: " + entry.getValue()[1]);
			}
		}

		List<Grade> testGrades = gradeDAO.findByWeek(batchId, week);
		for(Grade grade: testGrades){
			if(grade.getTrainee().getTraineeId() == 1059){
				System.out.println("---------------------------------------------------");
				System.out.println("Trainee Name: " + grade.getTrainee().getName());
				System.out.println("Assessment: " + grade.getAssessment().getType().name());
				System.out.println("Score: " + grade.getScore());
				System.out.println("Raw Score: " + grade.getAssessment().getRawScore());
			}
		}


		
	}

}
