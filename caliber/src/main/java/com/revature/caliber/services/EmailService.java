package com.revature.caliber.services;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.revature.caliber.email.Mailer;

/**
 * 
 * @author Andrew Bonds
 * @author Will Underwood
 *
 */
@Service
public class EmailService {
	
	private static final Logger logger = Logger.getLogger(EmailService.class);
	
	@Component
	static class EmailTimer extends Timer {
		
	}

	@Autowired
	private Mailer mailer;
	
	@Autowired
	private EmailTimer emailTimer;


	private static final int YEAR = 2017;
	private static final int MONTH = 11;
	private static final int DATE = 14;
	private static final int HOUR = 11;
	private static final int MINUTE = 45;
	private static final int SECOND = 0;
	

	public void setMailer(Mailer mailer) {
		this.mailer = mailer;
	}

	static boolean init = false;
	@PostConstruct
	private synchronized void startReminderJob() {
		if (init)
			return;
		init = true;
		logger.warn("startReminderJob()");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		calendar.set(Calendar.HOUR, 9);
		calendar.set(Calendar.MINUTE, 15);
		Date startDate = calendar.getTime();
		long interval = 300000;
		emailTimer.scheduleAtFixedRate(this.mailer, startDate, interval);

	}

}
