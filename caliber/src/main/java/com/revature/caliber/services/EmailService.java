package com.revature.caliber.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Trainee;
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
	private TraineeDAO trainee;
	
	@Autowired
	private GradeDAO grade;
	
	private static final long DAYS_IN_WEEK = 7;
	private static final int YEAR = 2017;
	private static final int MONTH = 9;
	private static final int DATE = 14;
	private static final int HOUR = 9;
	private static final int MINUTE = 28;
	private static final int SECOND = 0;
	
	public void setGrade(GradeDAO grade) {
		this.grade = grade;
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
		this.trainee = trainee;
	}
	
	@PostConstruct
	public void init() {
		this.startReminderJob();
	}

	public void startReminderJob() {
		List<Assessment> list = assess.findAll();
		System.out.println(list.toString());
		List<Batch> batchList = batch.findAll();
		List<Assessment> assessList = assess.findAll();
		List<Trainee> traineeList = trainee.findAll();
		
		Timer timer = new Timer();
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR, MONTH, DATE, HOUR, MINUTE, SECOND);
		Date startDate = calendar.getTime();
		//long interval = TimeUnit.DAYS.toMillis(DAYS_IN_WEEK);
		long interval = 20000;
		this.checkGrades(batchList, assessList, traineeList);
		timer.scheduleAtFixedRate(this.mailer, startDate, interval);
	}
	
	public void checkGrades(List<Batch> batchList, List<Assessment> assessList, List<Trainee> traineeList) {
		Date currentDate = new Date();
		
//		for(Batch b : batchList) {
//			ArrayList<Long> list = new ArrayList<Long>();
//			List<Grade> gradeList = grade.findByBatch(b.getBatchId());
//			if (b.getEndDate().before(currentDate) && b.getWeeks() < 8) {
//				for(Assessment a: assessList) {
//					if(b.getBatchId() == a.getBatch().getBatchId()) {
//						list.add(a.getAssessmentId());
//					}
//				}
//				for(Trainee t : traineeList) {
//					
//			}
//		}
//		
//		
//
//	}

	

	
	
}

}
