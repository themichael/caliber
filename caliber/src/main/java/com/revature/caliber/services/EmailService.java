package com.revature.caliber.services;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.revature.caliber.email.Mailer;

/**
 * Starts the email job
 * @author Andrew Bonds
 * @author Will Underwood
 * @author Vladimir Yevseenko
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

	private static final int DAYS_IN_WEEK = 7;
	private static final int HOUR = 9;
	private static final int MINUTE = 0;
	
	public void setMailer(Mailer mailer) {
		this.mailer = mailer;
	}

	private static boolean init = false;
	
	@PostConstruct
	private synchronized void startReminderJob() {
		if (init)
			return;
		init = true;
		logger.warn("startReminderJob()"); //Change to info
		Calendar calendar = Calendar.getInstance(); //Set to 1 P.M. Eastern
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		calendar.set(Calendar.HOUR, HOUR);
		calendar.set(Calendar.MINUTE, MINUTE);
		Date startDate = calendar.getTime();
		long interval = TimeUnit.DAYS.toMillis(DAYS_IN_WEEK);
		emailTimer.scheduleAtFixedRate(this.mailer, startDate, interval);

	}

}
