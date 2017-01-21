package com.revature.caliber.controllers.trainer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.ResponseEntity;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.controllers.TrainerBatchController;

public class TrainerBatchControllerTest {
	
	private static ApplicationContext context;
	private static TrainerBatchController controller;
	
	
	@BeforeClass
	public static void preClass(){
		context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
		controller = context.getBean(TrainerBatchController.class);
	}
	
	/**
	 * 	Tests trainer batch controller method(getAllBatches)
	 * 	- returns all batches of that trainer
	 */
	@Test
	public void getAllBatches(){		 
		 ResponseEntity<Set<Batch>> entity = controller.getAllBatches();
		 Set<Batch> set = entity.getBody();
		 
		 // test set of batches on return
		 assertNotNull(set);
		 assertEquals(3, set.size());
	}
	
	/**
	 *  Tests trainer batch controller method(getBatch)
	 *  - returns a batch with the given id
	 */
	@Test
	public void getBatch(){
		int batchId = 2;
		ResponseEntity<Batch> entity = controller.getBatch(batchId);
		Batch batch = entity.getBody();
		
		// test batch on return
		assertNotNull(batch);
		assertEquals(batchId, batch.getBatchId());
	}
	
	/**
	 *  Tests trainer batch controller method(getCurrentBatch)
	 *  - returns the current batch
	 */
	@Test
	public void getCurrentBatch(){
		int currentBatchId = 4;
		ResponseEntity<Batch> entity = controller.getCurrentBatch();
		Batch batch = entity.getBody();
		
		// test batch on return
		assertNotNull(batch);
		assertEquals(currentBatchId, batch.getBatchId());
	}
}
