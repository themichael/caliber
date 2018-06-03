package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.caliber.Application;
import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Address;
import com.revature.caliber.data.AddressRepository;

@SpringBootTest(classes= Application.class)
public class AddressDAOTest extends CaliberTest{

	private static final Logger log = Logger.getLogger(AddressDAOTest.class);
	private static final String ADDRESS_COUNT = "select count(address_id) from caliber_address";

	private AddressRepository dao;
	@Autowired
	public void setDao(AddressRepository dao) {
		this.dao = dao;
	}

	//Tests the save method in AddressDAO. Creates a new address and checks if the address appears, and then checks to make sure the size has increased.
	@Test
	public void saveAddressDAO(){
		log.debug("Saving a new Address using AddressDAO");
		int before = jdbcTemplate.queryForObject(ADDRESS_COUNT, Integer.class);
		Address address = new Address(1, "Sunshine st","New Hope", "MN", "55428", "moneybags inc",false);
		dao.save(address);
		int after = jdbcTemplate.queryForObject(ADDRESS_COUNT, Integer.class);
		int addressId = address.getAddressId();
		assertEquals(address, dao.findOne(addressId));
		assertEquals(++before, after);
	}
	//Tests the gettAll method in AddressDAO and returns a String version of it. Asserts that is is not empty, the size is correct, and the findAll string version is equal to getAll.
	@Test
	public void getAllAddressDAO(){
		log.debug("Getting all addresses using AddressDAO getAll function");
		int num = jdbcTemplate.queryForObject(ADDRESS_COUNT, Integer.class);
		assertNotNull(dao.findAll());
		assertEquals(dao.findAll().size(), num);
	}

	//Tests the findAll method in AddressDAO and returns a list. Asserts the findAll is not empty and that it is the correct size.
	@Test
	public void findAllAddressDAO(){
		log.debug("Find All Addresses");
		int size = jdbcTemplate.queryForObject(ADDRESS_COUNT, Integer.class);
		assertFalse(dao.findAll().isEmpty());
		assertEquals(dao.findAll().size(),size);
	}

	//Tests to make sure an address not in the database returns null. 
	@Test
	public void nullGetAddressByInt(){
		log.debug("About to fail gettingAddressByInt");
		Address address = dao.findOne(9999999);
		assertNull(address);
	}

	//Tests update method of AddressDAO. Asserts that addresses zipcode was changed.
	@Test
	public void updateAddressDAO(){
		log.debug("UpdateAddessDAO Test");
		String zipcode = "11111";
		Address address = dao.findOne(2);
		address.setZipcode(zipcode);
		dao.save(address);
		assertEquals(zipcode, dao.findOne(2).getZipcode());
	}
}