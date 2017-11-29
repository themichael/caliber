package com.revature.caliber.test.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.Salesforce;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.TrainingStatus;
import com.revature.caliber.tasks.BatchUpdate;

public class BatchUpdateTest extends CaliberTest{
	
	private Trainee traineeOne = new Trainee();
	private Trainee traineeTwo = new Trainee();
	private Trainee traineeThree = new Trainee();
	
	public void setTraineeInfo() {
		this.traineeOne.setName("Joe Smith");
		this.traineeOne.setResourceId("one");
		this.traineeOne.setEmail("one@gmail.com");
		this.traineeOne.setPhoneNumber("954-798-6005");
		this.traineeOne.setTrainingStatus(TrainingStatus.Training);
		
		this.traineeTwo.setName("Shakira Jimenez");
		this.traineeTwo.setResourceId("two");
		this.traineeTwo.setEmail("two@gmail.com");
		this.traineeTwo.setPhoneNumber("347-798-6005");
		this.traineeTwo.setTrainingStatus(TrainingStatus.Training);
		
		this.traineeThree.setName("Joe Smith");
		this.traineeThree.setResourceId("one");
		this.traineeThree.setEmail("one@gmail.com");
		this.traineeThree.setPhoneNumber("954-798-6005");
		this.traineeThree.setTrainingStatus(TrainingStatus.Dropped);
	}
	
	/*
	 * Test Methods: Verifies cron trigger executions at midnight,
	 * 				 Checks the last time execution was fired and 
	 * 				 Shows the next execution date that trigger will be fired
	 */
	private static final Logger log = Logger.getLogger(BatchUpdate.class);
	@Test
	public void testScheduler() {
		org.springframework.scheduling.support.CronTrigger trigger = new CronTrigger("0 0 0 * * *");
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		final Date yesterday = today.getTime();
		String lastMessage = (sdf.format(yesterday)) + " : [Yesterday]";
		log.info(lastMessage);
		//System.out.println(lastMessage);
		Date nextExecution = trigger.nextExecutionTime(
				new TriggerContext() {
					@Override
					public Date lastScheduledExecutionTime() {
						return yesterday;
					}
					@Override
					public Date lastActualExecutionTime() {
						return yesterday;
					}
					@Override
					public Date lastCompletionTime() {
						return yesterday;
					}
				});
		String nextMessage = sdf.format(nextExecution) + " : [Execution] ";
		log.info(nextMessage);
		//System.out.println(nextMessage);
	}
	
	@Test
	public void testUpdateTrainees() {
		Set<Trainee> caliberTrainees = new HashSet<>();
		Set<Trainee> salesforceTrainees = new HashSet<>();
		
		setTraineeInfo();
		
		caliberTrainees.add(this.traineeOne);
		caliberTrainees.add(this.traineeTwo);
		
		salesforceTrainees.add(this.traineeThree);
		salesforceTrainees.add(this.traineeTwo);
		
		BatchUpdate batchUpdate = new BatchUpdate();
		boolean updated = batchUpdate.updateTrainees(caliberTrainees, salesforceTrainees);
		log.info("Trainees were updated: "+updated);
		assertTrue(updated);
		
	}
	
	@Test
	public void compareBatchTest() {
		
	}
	
}
