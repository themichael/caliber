package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Address;
import com.revature.caliber.controllers.TrainingController;
import com.revature.caliber.data.AddressDAO;
import com.revature.caliber.test.integration.AssessmentTest;


public class AddressDAOTest  extends CaliberTest{
/*
 * save    Ruha
getAll  Ruha
findAll  Ruha
getAddressByInt  Ruha
update  Ruha
getOne Ruha
 */
	private static final Logger log = Logger.getLogger(AddressDAOTest.class);
	@Autowired
	TrainingController trainingController;

	private AddressDAO dao;
	@Autowired
	public void setDao(AddressDAO dao) {
		this.dao = dao;
	}
	
	@Test
	public void saveAddressDAO(){
		log.info("Saving a new Address using AddressDAO");
		//int before = trainingController.getAllLocations().getBody().size();
		Address address = new Address(1, "Sunshine st","New Hope", "MN", "55428", "moneybags inc",0);
		dao.save(address);
		assertEquals(trainingController.getAllLocations().getBody().get(2), address);
	}
	@Test
	public void getAllAddressDAO(){
		log.info("Getting all addresses using AddressDAO getAll function");
		assertNotNull(dao.getAll());
		assertEquals(trainingController.getAllLocations().getBody().size(),dao.getAll().size());
		assertEquals(trainingController.getAllLocations().getBody().toString(),dao.getAll().toString());
	}
	
	@Test
	public void findAllAddressDAO(){
		log.info("Find All Addresses");
		assertTrue(trainingController.getAllLocations().hasBody());
		
	}
	@Test
	public void getAddressByIntAddressDAO(){
		log.info("Finding Location by address");
		Address address = dao.getAddressById(1);
		assertEquals(trainingController.getAllLocations().getBody().get(0),address);
		
	}
	@Test
	public void updateAddressDAO(){
		
	}
	@Test
	public void getOne(){
		
	}
}

