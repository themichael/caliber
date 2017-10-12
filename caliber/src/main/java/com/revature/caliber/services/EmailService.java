package com.revature.caliber.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Timer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	private AssessmentDAO assess;

	@Autowired
	private BatchDAO batch;

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

	public void setGrade(GradeDAO grade) {
		this.gradeDAO = grade;
	}

	public void setMailer(Mailer mailer) {
		this.mailer = mailer;
	}

	public void setAssessmentDAO(AssessmentDAO assess) {
		this.assess = assess;
	}

	public void setBatch(BatchDAO batch) {
		this.batch = batch;
	}

	public void setTrainee(TraineeDAO trainee) {
		this.traineeDAO = trainee;
	}

	@PostConstruct
	public void init() {
		this.startReminderJob();
	}

	public void startReminderJob() {
		List<Assessment> list = assess.findAll();
		System.out.println(list.toString());
		List<Batch> batchList = batch.findAllCurrent();
		List<Assessment> assessList = assess.findAll();

		Timer timer = new Timer();
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR, MONTH, DATE, HOUR, MINUTE, SECOND);
		Date startDate = calendar.getTime();
		//long interval = TimeUnit.DAYS.toMillis(DAYS_IN_WEEK);
		long interval = 20000;
		timer.scheduleAtFixedRate(this.mailer, startDate, interval);
		this.checkGrades(batchList, assessList);
	}

	public void checkGrades(List<Batch> batchList, List<Assessment> assessList) {
		ArrayList<Trainer> trainer = new ArrayList<Trainer>();

		for(Batch batch : batchList) {
			//System.out.println("Trainer in batch " + batch.getBatchId() + " " + batch.getTrainer().getName());
			ArrayList<Long> assessmentIDs = new ArrayList<Long>();
			List<Grade> batchGrades = gradeDAO.findByBatch(batch.getBatchId());
			List<Trainee> batchTrainees = traineeDAO.findAllByBatch(batch.getBatchId());
			
			for(Assessment assessment : assessList) {
				if(batch.getBatchId() == assessment.getBatch().getBatchId()) {
					//System.out.println(batch.getBatchId() + " and " + assessment.getBatch().getBatchId());
					assessmentIDs.add(assessment.getAssessmentId());
				}
			}

			if(batch.getTrainer().getTrainerId() == 6) {
				System.out.println("Genesis List: " + assessmentIDs);
			}

			boolean checkCount = false;
			for(Grade g: batchGrades) {
				for(int i = 0; i < assessmentIDs.size(); i++) {
					if(g.getAssessment().getAssessmentId() == assessmentIDs.get(i)) {
						for(Trainee t: batchTrainees) {
							if(g.getTrainee().getTraineeId() != t.getTraineeId()) {
								if(trainer.contains(batch.getTrainer())) {
									continue;
								}
								checkCount = true;
							}
						}
					}
				}
			}
			
			if(checkCount) {
				trainer.add(batch.getTrainer());
			}
			else {
				trainer.remove(batch.getTrainer());
			}
		}
		System.out.println("This trainer needs to do work: " + trainer);
	}

}
