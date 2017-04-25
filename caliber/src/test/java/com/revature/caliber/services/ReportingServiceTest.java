/*package com.revature.caliber.services;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.junit.Ignore;
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
	@Ignore
	public void getAvgBatchWeekTest() {
		Integer batchId = 1050;
		Integer week = 3;
		AssessmentType assessmentType = AssessmentType.Project;
		Map<Trainee, Double[]> results = reportingService.utilAvgBatchWeek(batchId, week, assessmentType);

		for (Entry<Trainee, Double[]> entry : results.entrySet()) {
			if (entry.getKey().getTraineeId() == 1059) {
				log.info("---------------------------------------------------");
				log.info("Trainee Name: " + entry.getKey().getName());
				log.info("Assessment Type: " + assessmentType);
				log.info("Trainee Average Score: " + entry.getValue()[0]);
				log.info("Total Possible Score: " + entry.getValue()[1]);
			}
		}

		List<Grade> testGrades = gradeDAO.findByWeek(batchId, week);
		for (Grade grade : testGrades) {
			if (grade.getTrainee().getTraineeId() == 1059) {
				log.info("---------------------------------------------------");
				log.info("Trainee Name: " + grade.getTrainee().getName());
				log.info("Assessment: " + grade.getAssessment().getType().name());
				log.info("Score: " + grade.getScore());
				log.info("Raw Score: " + grade.getAssessment().getRawScore());
			}
		}
	}

	@Test
	@Ignore
	public void getAvgBatchOverallTest() {
		log.info(reportingService.utilAvgBatchOverall(1050, AssessmentType.Exam));
	}

}
*/