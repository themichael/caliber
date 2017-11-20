package com.revature.caliber.tasks;

import org.springframework.scheduling.annotation.Scheduled;

public class BatchUpdate {
	
	/*
	 * Test Method: Used cron to perform midnight execution 
	 * 				To update batches
	 */
	
	//@Scheduled(cron = "0 0 0 * * *")  //Midnight
	@Scheduled(cron = "0 * * * * *") 	//Every minute (testing)
	public void updateBatchTask() {
		//Update job goes here
	}
}