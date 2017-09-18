package com.revature.caliber.test.unit;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.services.ReportingService;

public class ReportingServiceTest extends CaliberTest{

	private static final Logger log = Logger.getLogger(ReportingServiceTest.class);
	
	@Autowired
	ReportingService reportingService;
	
	/**
	 * Tests methods:
	 * @see com.revature.caliber.service.ReportingService#getBatchComparisonAvg(String skill, String training, Date startDate)
	 */
	@Test
	public void getBatchComparisonAvgTest(){
		log.info("Testing the ReportingService.getBatchCommparisonAvg(String skill, String training, Date startDate)");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1, 1);
		String skillType = "(All)";
		String trainingType = "(All)";
		double avg = reportingService.getBatchComparisonAvg(skillType, trainingType, cal.getTime());
		assertTrue(83<avg && avg<84);  //avg equals about 83.9
		
		skillType = "J2EE";  //There is only one type of training in the setup.sql, so this is the same result "(All)"
		trainingType = "University";												//but uses a different code path
		avg = reportingService.getBatchComparisonAvg(skillType, trainingType, cal.getTime());
		assertTrue(79<avg && avg<80);  //avg equals about 79.8
		
		skillType = "(All)";
		trainingType = "Revature";
		avg = reportingService.getBatchComparisonAvg(skillType, trainingType, cal.getTime());
		assertTrue(86<avg && avg<87);  //avg equals about 86.5
		
		skillType = "(All)";
		trainingType = "University";
		avg = reportingService.getBatchComparisonAvg(skillType, trainingType, cal.getTime());
		assertTrue(79<avg && avg<80);  //avg equals about 79.8
	}
}
