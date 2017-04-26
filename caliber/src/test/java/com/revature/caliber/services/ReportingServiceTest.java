package com.revature.caliber.services;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/beans.xml" })
public class ReportingServiceTest {

	private static Logger log = Logger.getLogger(ReportingServiceTest.class);
	@Autowired
	private ReportingService reportingService;

	private static int runs = 100;
	private static long nano = 1000000000l;

	@Test
	// @Ignore
	public void benchmarkTest() {
		long serialRunTimeSystem = serialMethodTest();
		long concurrentRunTimeSystem = concurrentMethod();

		log.info("Serial System Time         : " + ((double) serialRunTimeSystem / nano) + " seconds.");
		log.info("Concurrent System Time     : " + ((double) concurrentRunTimeSystem / nano) + " seconds.");
		Double percentageImprovement = (double) (serialRunTimeSystem - concurrentRunTimeSystem) / serialRunTimeSystem
				* 100;
		log.info("Concurrent Speed Improvement: " + percentageImprovement + "%");
	}

	public long serialMethodTest() {
		log.info("testGetAllCurrentBatchesLineChart");
		Long startTimeNano = System.nanoTime();
		for (int i = 0; i < runs; i++) {
			reportingService.getAllCurrentBatchesLineChart();
		}
		long serialRunTimeSystem = System.nanoTime() - startTimeNano;
		serialRunTimeSystem /= runs;
		return serialRunTimeSystem;
	}

	public long concurrentMethod() {
		log.info("testGetAllCurrentBatchesLineChartConcurrent");
		Long startTimeNano = System.nanoTime();
		for (int i = 0; i < runs; i++) {
			reportingService.getAllCurrentBatchesLineChartConcurrent();
		}
		long concurrentRunTimeSystem = System.nanoTime() - startTimeNano;
		concurrentRunTimeSystem /= runs;
		return concurrentRunTimeSystem;
	}
}
