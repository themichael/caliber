package com.revature.caliber.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.revature.caliber.beans.Address;
import com.revature.caliber.data.AddressDAO;

public class AddressTest {

	private static final Logger log = Logger.getLogger(AddressDAO.class);

	@Test
	public void testAddressDAO() {
		AddressDAO dao = new AddressDAO();
		Address address = new Address("1", "2", "3", "4", "5");
		dao.saveAddress(address);
		Address address2;
		address2 = dao.getAddressById(1);
		log.info(address2);
		dao.deleteAddress(address);

	}
}
