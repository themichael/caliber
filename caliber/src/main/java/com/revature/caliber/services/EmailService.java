package com.revature.caliber.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.data.AssessmentDAO;
import com.revature.caliber.email.EmailAuthenticator;
import com.revature.caliber.email.Mailer;

/**
 * 
 * @author Andrew Bonds
 * @author Will Underwood
 *
 */
@Service
public class EmailService {

	@Value("#{systemEnvironment['DEV_CALIBER_EMAIL']}")
	private static String fromEmail;
	@Value("#{systemEnvironment['DEV_CALIBER_PASS']}")
	private static String fromPass;
	
	private Mailer mailer;

	private AssessmentDAO assess;
	
	private static final long DAYS_IN_WEEK = 7;
	private static final int YEAR = 2017;
	private static final int MONTH = 9;
	private static final int DATE = 11;
	private static final int HOUR = 9;
	private static final int MINUTE = 35;
	private static final int SECOND = 0;
	
	@Autowired
	public void setMailer(Mailer mailer) {
		this.mailer = mailer;
	}
	
	@Autowired
	public void setAssessmentDAO(AssessmentDAO assess) {
		this.assess = assess;
	}

	@PostConstruct
	public void init() {
//		this.startReminderJob();
		List<Assessment> list = assess.findAll();
		System.out.println(list.toString());
	}
	
	private void startReminderJob() {
		Timer timer = new Timer();
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR, MONTH, DATE, HOUR, MINUTE, SECOND);
		Date startDate = calendar.getTime();
		//long interval = TimeUnit.DAYS.toMillis(DAYS_IN_WEEK);
		long interval = 10000;
		timer.scheduleAtFixedRate(this.mailer, startDate, interval);
	}

}
