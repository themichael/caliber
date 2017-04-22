package com.revature.caliber.services;

import java.lang.management.ThreadMXBean;

import org.apache.log4j.Logger;
import org.junit.Ignore;
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
	private static long serialRunTimeSystem = 0l;
	private static long serialRunTimeThread = 0l;
	private static long concurrentRunTimeSystem = 0l;
	private static long concurrentRunTimeThread = 0l;
	
	@Test
	@Ignore
	public void benchmarkTest(){
		testGetAllCurrentBatchesLineChart();
		testGetAllCurrentBatchesLineChartConcurrent();
		log.info("Serial System Time         : " + serialRunTimeSystem + " nano seconds.");
		log.info("Serial Thread CPU Time     : " + serialRunTimeThread + " nano seconds.");
		log.info("Concurrent Nano System Time: " + concurrentRunTimeSystem + " nano seconds.");
		log.info("Concurrent Thread CPU Time : " + concurrentRunTimeThread + " nano seconds.");
	}
	
	@Test
	@Ignore
	public void testGetAllCurrentBatchesLineChart(){
		log.info("testGetAllCurrentBatchesLineChart");
		ThreadMXBean tmx = java.lang.management.ManagementFactory.getThreadMXBean();
		Long startTimeThread = tmx.getCurrentThreadCpuTime();
		Long startTimeNano = System.nanoTime();
		for(int i = 0; i < runs; i++){
			reportingService.getAllCurrentBatchesLineChart();
		}
		serialRunTimeSystem = System.nanoTime() - startTimeNano;
		serialRunTimeThread = tmx.getCurrentThreadCpuTime() - startTimeThread;
		serialRunTimeSystem /= runs;
		serialRunTimeThread /= runs;
	}
	
	@Test
	@Ignore
	public void testGetAllCurrentBatchesLineChartConcurrent(){
		log.info("testGetAllCurrentBatchesLineChartConcurrent");
		ThreadMXBean tmx = java.lang.management.ManagementFactory.getThreadMXBean();
		Long startTimeThread = tmx.getCurrentThreadCpuTime();
		Long startTimeNano = System.nanoTime();
		for(int i = 0; i < runs; i++){
			reportingService.getAllCurrentBatchesLineChartConcurrent();
		}
		concurrentRunTimeSystem = System.nanoTime() - startTimeNano;
		concurrentRunTimeThread = tmx.getCurrentThreadCpuTime() - startTimeThread;
		concurrentRunTimeSystem /= runs;
		concurrentRunTimeThread /= runs;
	}
}
