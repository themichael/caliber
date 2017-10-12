package com.revature.caliber.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.data.AssessmentDAO;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.email.Mailer;

/**
 * 
 * @author Andrew Bonds
 * @author Will Underwood
 *
 */
@Service
public class EmailService {

	@Autowired
	private Mailer mailer;

	@Autowired
	private AssessmentDAO assessmentDAO;

	@Autowired
	private BatchDAO batchDAO;

	@Autowired
	private TraineeDAO traineeDAO;

	@Autowired
	private GradeDAO gradeDAO;

	private static final long DAYS_IN_WEEK = 7;
	private static final int YEAR = 2017;
	private static final int MONTH = 9;
	private static final int DATE = 14;
	private static final int HOUR = 9;
	private static final int MINUTE = 44;
	private static final int SECOND = 0;

	public void setMailer(Mailer mailer) {
		this.mailer = mailer;
	}

	public void setGrade(GradeDAO gradeDAO) {
		this.gradeDAO = gradeDAO;
	}

	public void setAssessmentDAO(AssessmentDAO assessmentDAO) {
		this.assessmentDAO = assessmentDAO;
	}

	public void setBatch(BatchDAO batchDAO) {
		this.batchDAO = batchDAO;
	}

	public void setTrainee(TraineeDAO traineeDAO) {
		this.traineeDAO = traineeDAO;
	}

	@PostConstruct
	public void init() {
		this.startReminderJob();
	}

	private void startReminderJob() {
		Timer timer = new Timer();
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR, MONTH, DATE, HOUR, MINUTE, SECOND);
		Date startDate = calendar.getTime();
		//long interval = TimeUnit.DAYS.toMillis(DAYS_IN_WEEK);
		long interval = 20000;
		timer.scheduleAtFixedRate(this.mailer, startDate, interval);
		
		List<Batch> batchList = batchDAO.findAllCurrent();
		List<Assessment> assessList = assessmentDAO.findAll();
		this.checkGrades(batchList, assessList);
	}

	public void checkGrades(List<Batch> batchList, List<Assessment> assessList) {
		ArrayList<Trainer> trainers = new ArrayList<Trainer>();

		for(Batch batch : batchList) {
			//System.out.println("Trainer in batch " + batch.getBatchId() + " " + batch.getTrainer().getName());
			List<Assessment> assessments = new ArrayList<Assessment>();
			List<Grade> batchGrades = gradeDAO.findByBatch(batch.getBatchId());
			List<Trainee> batchTrainees = traineeDAO.findAllByBatch(batch.getBatchId());
			
			for(Assessment assessment : assessList) {
				if(batch.getBatchId() == assessment.getBatch().getBatchId()) {
					//System.out.println(batch.getBatchId() + " and " + assessment.getBatch().getBatchId());
					assessments.add(assessment);
				}
			}

			for(Grade grade : batchGrades) {
				for(Assessment assessment : assessments) {
					if(grade.getAssessment().equals(assessment)) {
						for(Trainee trainee : batchTrainees) {
							boolean traineeHasGradeForThisAssessment = grade.getTrainee().equals(trainee);
							if(!traineeHasGradeForThisAssessment) {
								boolean trainerHasSubmittedAllGrades = trainers.contains(batch.getTrainer());
								if(!trainerHasSubmittedAllGrades) {
									trainers.add(batch.getTrainer());
								}
							}
						}
					}
				}
			}
		}
		for (Trainer trainer : trainers) {
			System.out.println(trainer.getName() + " needs to submit grades");
		}
	}

}
