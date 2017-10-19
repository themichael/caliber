package com.revature.caliber.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.email.Mailer;

/**
 * Starts the email job
 * @author Andrew Bonds
 * @author Will Underwood
 * @author Vladimir Yevseenko
 */
@Service
public class EmailService implements InitializingBean {
	
	private static final Logger logger = Logger.getLogger(EmailService.class);

	@Autowired
	private Mailer mailer;
	
	private static final ScheduledExecutorService scheduler =
		Executors.newScheduledThreadPool(1);
	
	private static final ZoneId TIME_ZONE = ZoneId.of("America/Eastern");
	private static final DayOfWeek DAY_OF_WEEK_TO_FIRE = DayOfWeek.TUESDAY;
	private static final int HOUR_TO_FIRE = 12; // hours go 0-23
	private static final int MINUTE_TO_FIRE = 0; // minutes go 0-59
	private static final int INITIAL_DELAY = 0; 
	private static final int DAYS_BETWEEN_EMAILS = 7;
	
	public void setMailer(Mailer mailer) {
		this.mailer = mailer;
	}

	private void startReminderJob() {
		logger.info("startReminderJob()");
		
		LocalTime localTime = LocalTime.of(HOUR_TO_FIRE, MINUTE_TO_FIRE);
		LocalDate localDate = LocalDate.now().with(TemporalAdjusters.next(DAY_OF_WEEK_TO_FIRE));
		ZonedDateTime timeToFire = ZonedDateTime.of(localDate, localTime, TIME_ZONE);
	
		logger.info(timeToFire);
		
		scheduler.scheduleAtFixedRate(mailer, INITIAL_DELAY, DAYS_BETWEEN_EMAILS, TimeUnit.DAYS);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		startReminderJob();
	}

}
