package com.revature.caliber.email;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Will Underwood
 *
 */
public class SubmitGradesReminder {
	
	private static final long DAYS_IN_WEEK = 7;
	private static final int YEAR = 2017;
	private static final int MONTH = 9;
	private static final int DATE = 10;
	private static final int HOUR = 13;
	private static final int MINUTE = 45;
	private static final int SECOND = 0;

	public void startReminderJob() {
		Timer timer = new Timer();
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR, MONTH, DATE, HOUR, MINUTE, SECOND);
		Date startDate = calendar.getTime();
		//long interval = TimeUnit.DAYS.toMillis(DAYS_IN_WEEK);
		long oneMinute = 10000;
		timer.scheduleAtFixedRate(new Mailer(), startDate, oneMinute);
	}

}
