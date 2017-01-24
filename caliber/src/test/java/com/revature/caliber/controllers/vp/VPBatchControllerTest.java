package com.revature.caliber.controllers.vp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.ResponseEntity;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.controllers.VPBatchController;

public class VPBatchControllerTest {

	private static ApplicationContext context;
	private static VPBatchController controller;
	
	/**
	 * Retrieve application context and get the VPBatchController bean
	 */
	@BeforeClass
	public static void preClass(){
		context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
		controller = context.getBean(VPBatchController.class);
	}
	
	/**
	 * 	Tests vp batch controller method(getAllBatches)
	 * 	- test that all batches are returned
	 */
	@Test
	public void getAllBatches(){
		ResponseEntity<Set<Batch>> enitity = controller.getAllBatches();
		Set<Batch> set = enitity.getBody();
		
		assertNotNull(set);
		assertEquals(3, set.size());
	}
	
	/**
	 *  Tests vp batch controller method(getAllCurrentBatches)
	 *  
	 *  - test that all current batches are returned
	 */
	@Test
	public void getAllCurrentBatches(){
		ResponseEntity<Set<Batch>> enitity = controller.getAllCurrentBatches();
		Set<Batch> set = enitity.getBody();
		
		assertNotNull(set);
		assertEquals(3, set.size());
	}
	
	/*
	 * 	Tests vp batch controller method(getBatch)
	 * 
	 * 	- tests that a batch with the given id is returned
	 */
	@Test
	public void getBatch(){
		int batchId = 5;
		ResponseEntity<Batch> enitity = controller.getBatch(batchId);
		Batch batch = enitity.getBody();
		
		assertNotNull(batch);
		assertEquals(batchId, batch.getBatchId());
	}
	
	/**
	 * 	Tests vp batch controller method(getCurrentBatch)
	 * 
	 * 	- tests that a current batch with the given id is returned
	 */
	@Test
	public void getCurrentBatch(){
		int batchId = 6;
		ResponseEntity<Batch> enitity = controller.getCurrentBatch(batchId);
		Batch batch = enitity.getBody();
		
		assertNotNull(batch);
		assertEquals(batchId, batch.getBatchId());
	}
	
}
