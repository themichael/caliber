package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.PanelFeedback;
import com.revature.caliber.data.PanelFeedbackDAO;

public class PanelFeedbackDAOTest extends CaliberTest{

	private static final Logger log = Logger.getLogger(PanelFeedbackDAOTest.class);
	private static final String PANELF_COUNT = "select count(panel_feedback_id) from caliber_panel_feedback";

	private PanelFeedbackDAO dao;
	
	@Autowired
	public void setDao(PanelFeedbackDAO dao) {
		this.dao = dao;
	}

	//Tests the save method in AddressDAO. Creates a new address and checks if the address appears, and then checks to make sure the size has increased.
//	@Test
//	public void saveFeedbackDAO(){
//		log.info("Saving a new Address using AddressDAO");
//		int before = jdbcTemplate.queryForObject(ADDRESS_COUNT, Integer.class);
//		Address address = new Address(1, "Sunshine st","New Hope", "MN", "55428", "moneybags inc",false);
//		dao.save(address);
//		int after = jdbcTemplate.queryForObject(ADDRESS_COUNT, Integer.class);
//		int addressId = address.getAddressId();
//		assertEquals(address, dao.getAddressById(addressId));
//		assertEquals(++before, after);
//	}
	//Tests the findAll method in AddressDAO and returns a String version of it. Asserts that is is not empty, the size is correct, and the findAll string version is equal to getAll.
	@Test
	public void findAllFeedbackDAO(){
		log.info("Getting all feedback using PanelFeedbackDAO getAll function");
		long num = jdbcTemplate.queryForObject(PANELF_COUNT, Long.class);
		assertNotNull(dao.findAll());
		assertEquals(dao.findAll().size(), num);
	}

	//Takes the getAddressById and finds the address from the database. Asserts the address obtained equals the address.
	@Test
	public void getFeedbackByIdDAO(){
		log.info("Finding Location by address");
		long search = 1;
		PanelFeedback feedback = dao.findOne(search);
		assertEquals(dao.findOne((long)1).toString(),feedback.toString());
	}

	//Tests to make sure an address not in the database returns null. 
	@Test
	public void nullGetAddressByInt(){
		log.info("About to fail gettingAddressByInt");
		PanelFeedback feedback = dao.findOne(99999999);
		assertNull(feedback);
	}

	//Tests update method of AddressDAO. Asserts that addresses zipcode was changed.
//	@Test
//	public void updateAddressDAO(){
//		log.info("UpdateAddessDAO Test");
//		String zipcode = "11111";
//		Address address = dao.getAddressById(2);
//		address.setZipcode(zipcode);
//		dao.update(address);
//		assertEquals(zipcode, dao.getAddressById(2).getZipcode());
//	}
}

