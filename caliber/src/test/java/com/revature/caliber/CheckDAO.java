package com.revature.caliber;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.data.AssessmentDAO;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.CategoryDAO;
import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.data.NoteDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;
import com.revature.caliber.services.TrainingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/beans.xml" })
public class CheckDAO {

	private static Logger log = Logger.getLogger(CheckDAO.class);

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
	private TrainingService trainingService;

	@Test
	public void testmethod() {
		log.info("Testing my code");
		try {
			List<Assessment> assessments = assessmentDAO.findByWeek(1050, 1);
			for (Iterator iterator = assessments.iterator(); iterator.hasNext();) {
				Assessment assessment = (Assessment) iterator.next();
				log.info(assessment);
				for(Grade g : assessment.getGrades())
					log.info(g);
			}
/*			List<Grade> grades = gradeDAO.findByWeek(1050, 1);
			for(Grade g : grades)
				log.info(g);*/
		} catch (Exception e) {
			log.info(e.getClass() + " " + e.getMessage());
		}
	}

}
