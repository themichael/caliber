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

	private static final long DAYS_IN_WEEK = 7;
	private static final int YEAR = 2017;
	private static final int MONTH = 11;
	private static final int DATE = 14;
	private static final int HOUR = 9;
	private static final int MINUTE = 44;
	private static final int SECOND = 0;
	
	static boolean init = false;
	@PostConstruct
	private synchronized void startReminderJob() {
		if (init)
			return;
		init = true;
		logger.warn("startReminderJob()");
		Calendar calendar = Calendar.getInstance();
		//calendar.set(YEAR, MONTH, DATE, HOUR, MINUTE, SECOND);
		Date startDate = new Date(calendar.getTime().getTime() + 15000);
		//long interval = TimeUnit.DAYS.toMillis(DAYS_IN_WEEK);
		long interval = 20000;
		emailTimer.scheduleAtFixedRate(this.mailer, startDate, interval);
	}

}
