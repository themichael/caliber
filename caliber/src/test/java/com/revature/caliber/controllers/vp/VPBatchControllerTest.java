package com.revature.caliber.controllers.vp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.ResponseEntity;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.controllers.VPBatchController;

public class VPBatchControllerTest {

	private static ApplicationContext context;
	private static VPBatchController controller;
	
	@Before
	public void preClass(){
		context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
		controller = context.getBean(VPBatchController.class);
	}
	
	@Test
	public void getAllBatches(){
		ResponseEntity<Set<Batch>> enitity = controller.getAllBatches();
		Set<Batch> set = enitity.getBody();
		
		assertNotNull(set);
		assertEquals(3, set.size());
	}
	
	@Test
	public void getAllCurrentBatches(){
		ResponseEntity<Set<Batch>> enitity = controller.getAllCurrentBatches();
		Set<Batch> set = enitity.getBody();
		
		assertNotNull(set);
		assertEquals(3, set.size());
	}
	
	@Test
	public void getBatch(){
		int batchId = 5;
		ResponseEntity<Batch> enitity = controller.getBatch(batchId);
		Batch batch = enitity.getBody();
		
		assertNotNull(batch);
		assertEquals(batchId, batch.getBatchId());
	}
	
	@Test
	public void getCurrentBatch(){
		int batchId = 6;
		ResponseEntity<Batch> enitity = controller.getCurrentBatch(batchId);
		Batch batch = enitity.getBody();
		
		assertNotNull(batch);
		assertEquals(batchId, batch.getBatchId());
	}
	
}
