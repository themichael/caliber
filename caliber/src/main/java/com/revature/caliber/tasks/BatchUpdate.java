package com.revature.caliber.tasks;

import java.util.Date;
import java.util.logging.*;

import org.springframework.scheduling.annotation.Scheduled;

public class BatchUpdate {
	private int i = 0;
	//@Scheduled(cron = "0 0 0 * * *")  //Midnight
	@Scheduled(cron = "0 * * * * *") 	//Every minute (testing)
	public void updateBatchTask() {
		//Update job goes here
	}

}