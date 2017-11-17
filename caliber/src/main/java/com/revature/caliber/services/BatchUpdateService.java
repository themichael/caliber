package com.revature.caliber.services;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.stereotype.Service;

import com.revature.caliber.data.GradeDAO;


@Service
public class BatchUpdateService {
	private static final Logger log = Logger.getLogger(BatchUpdateService.class);
	private final Date midnight;
	private long initialDelay;
	private static final long PERIOD = 86400000L; //24 Hours
	BatchUpdateService() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(calendar.MINUTE, 59);
		calendar.set(calendar.SECOND, 59);
		midnight = calendar.getTime();
		initialDelay = new Date(midnight.getTime()-System.currentTimeMillis()).getTime();
	}
	public static void updateBatch() {
		ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);
		execService.scheduleAtFixedRate(()->{
			//Update database goes here?
			Date now = new Date();
			log.info("Update here: " + now.toString());
		}, 0, 1000L, TimeUnit.MILLISECONDS);
	}
	
}
