package com.revature.caliber.email;

/**
 * 
 * @author Will Underwood
 *
 */
public class MailMain {

	public static void main(String[] args) {
		SubmitGradesReminder reminder = new SubmitGradesReminder();
		reminder.startReminderJob();
	}

}
