package com.revature.caliber.salesforce;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.data.SalesforceDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/beans.xml" })
public class SalesforceDAOTest  {

	@Autowired
	private SalesforceDAO salesforceDAO;
	
	public void setSalesforceDAO(SalesforceDAO salesforceDAO) {
		this.salesforceDAO = salesforceDAO;
	}

	public List<Batch> getAllBatchesByYear(int year){
		List<Batch> batches = new ArrayList<Batch>();
		return null;
	}

	public Batch getBatchByResourceId(String resourceId){
		return null;
	}
	
	
}
