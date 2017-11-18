package com.revature.caliber.tasks;

import org.springframework.scheduling.annotation.Scheduled;

public class BatchUpdate {
	//@Scheduled(cron = "0 0 0 * * *")  //Midnight
	@Scheduled(cron = "0 * * * * *") 	//Every minute (testing)
	public void updateBatchTask() {
		//Update job goes here
	}
}