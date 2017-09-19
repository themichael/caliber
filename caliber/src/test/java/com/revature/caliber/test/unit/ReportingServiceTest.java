package com.revature.caliber.test.unit;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.services.ReportingService;

public class ReportingServiceTest extends CaliberTest{

	private static final Logger log = Logger.getLogger(ReportingServiceTest.class);
	
	@Autowired
	ReportingService reportingService;
	@Autowired
	BatchDAO batchDao;
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getAllCurrentBatchesLineChartConcurrent()
	 */
	@Test
	public void testGetAllCurrentBatchesLineChartConcurrent() {
		log.info("Testing getAllCurrentBatchesLineChartConcurrent");
		
		final String key = "1702 Feb13 Java (AP)";
		Map<String, Map<Integer, Double>> batches = reportingService.getAllCurrentBatchesLineChartConcurrent();
		
		log.debug(batches);
		
		assertTrue(batches.containsKey(key));
		assertTrue(batches.get(key).containsValue(68.34475));
		assertTrue(batches.get(key).containsValue(84.9646875));
		assertTrue(batches.get(key).containsValue(76.82675000000002));
		assertTrue(batches.get(key).containsValue(75.09325));
		assertTrue(batches.get(key).containsValue(77.94375000000001));
		assertTrue(batches.get(key).containsValue(82.80416666666666));
		assertTrue(batches.get(key).containsValue(74.265));
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTraineeUpToWeekRadarChart(Integer, Integer)
	 */
	@Test
	public void testGetTraineeUpToWeekRadarChart() {
		log.info("Testing getTraineeUpToWeekRadarChart");
		
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTraineeOverallRadarChart(Integer)
	 */
	@Test
	public void testGetTraineeOverallRadarChart() {
		log.info("Testing getTraineeOverallRadarChart");
		
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
