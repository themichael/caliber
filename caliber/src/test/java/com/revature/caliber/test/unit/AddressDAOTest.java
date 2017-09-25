package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Address;
import com.revature.caliber.controllers.TrainingController;
import com.revature.caliber.data.AddressDAO;


public class AddressDAOTest extends CaliberTest{

	private static final Logger log = Logger.getLogger(AddressDAOTest.class);
	private static final String ADDRESS_COUNT = "select count(address_id) from caliber_address";
	@Autowired
	TrainingController trainingController;

	private AddressDAO dao;
	@Autowired
	public void setDao(AddressDAO dao) {
		this.dao = dao;
	}
	
	@Ignore
	@Test
	public void saveAddressDAO(){
		log.info("Saving a new Address using AddressDAO");
		int before = jdbcTemplate.queryForObject(ADDRESS_COUNT, Integer.class);
		Address address = new Address(1, "Sunshine st","New Hope", "MN", "55428", "moneybags inc",false);
		dao.save(address);
		int after = jdbcTemplate.queryForObject(ADDRESS_COUNT, Integer.class);
		int addressId = address.getAddressId();
		assertEquals(trainingController.getAllLocations().getBody().get(2), address);
		assertEquals(address, dao.getAddressById(addressId));
		assertEquals(++before, after);
	}
	@Ignore
	@Test
	public void getAllAddressDAO(){
		log.info("Getting all addresses using AddressDAO getAll function");
		assertNotNull(dao.getAll());
		assertEquals(trainingController.getAllLocations().getBody().size(),dao.getAll().size());
		assertEquals(trainingController.getAllLocations().getBody().toString(),dao.getAll().toString());
	}
	@Ignore
	@Test
	public void findAllAddressDAO(){
		log.info("Find All Addresses");
		int size = jdbcTemplate.queryForObject(ADDRESS_COUNT, Integer.class);
		assertTrue(trainingController.getAllLocations().hasBody());
		assertEquals(dao.findAll().size(),size);
	}
	@Ignore
	@Test
	public void getAddressByIdDAO(){
		log.info("Finding Location by address");
		int search = 1;
		Address address = dao.getAddressById(search);
		assertEquals(trainingController.getAllLocations().getBody().get(0),address);
	}
	@Ignore
	@Test(expected=IndexOutOfBoundsException.class)
	public void failGetAddressByInt(){
		log.info("About to fail gettingAddressByInt");
		Address address = new Address(1, "not on Sunshine st","No Hope", "NY", "66666", "little bums",false);
		dao.save(address);
		int addressId = address.getAddressId();
		trainingController.getAllLocations().getBody().get(addressId+1);
	}
	@Ignore
	@Test
	public void updateAddressDAO(){
		log.info("UpdateAddessDAO Test");
		String zipcode = "11111";
		Address address = dao.getAddressById(2);
		address.setZipcode(zipcode);
		dao.update(address);
		assertEquals(zipcode, dao.getAddressById(2).getZipcode());
	}
}