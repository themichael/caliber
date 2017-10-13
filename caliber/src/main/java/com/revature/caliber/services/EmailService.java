package com.revature.caliber.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import com.revature.caliber.data.TrainerDAO;
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
	
	@Autowired
	private TrainerDAO trainerDAO;

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
	
	public void setTrainer(TrainerDAO trainerDAO) {
		this.trainerDAO = trainerDAO;
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
		//timer.scheduleAtFixedRate(this.mailer, startDate, interval);

		this.checkGrades();
	}

	public void checkGrades() {
		List<Trainer> trainers = this.trainerDAO.findAll();
		for (Trainer trainer : trainers) {
			List<Batch> trainerBatches = this.batchDAO.findAllByTrainer(trainer.getTrainerId());
			for (Batch batch : trainerBatches) {
				List<Assessment> batchAssessments = this.assessmentDAO.findByBatchId(batch.getBatchId());
				List<Trainee> batchTrainees = this.traineeDAO.findAllByBatch(batch.getBatchId());
				int expectedNumberOfGrades = batchAssessments.size() * batchTrainees.size();
				int actualNumberOfGrades = 0;
				for (Assessment assessment : batchAssessments) {
					List<Grade> assessmentGrades = gradeDAO.findByAssessment(assessment.getAssessmentId());
					actualNumberOfGrades += assessmentGrades.size();
				}
				if (actualNumberOfGrades < expectedNumberOfGrades) {
					System.out.println("\n" + trainer.getName() + " needs to submit grades" + "\n");
				}
			}
		}
	}

}
