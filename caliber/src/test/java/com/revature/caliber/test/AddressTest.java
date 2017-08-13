package com.revature.caliber.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.revature.caliber.beans.Address;
import com.revature.caliber.data.AddressDAO;
import com.revature.caliber.data.BatchDAO;

public class AddressTest {

	private static final Logger log = Logger.getLogger(AddressDAO.class);

	@Test
	public void testAddressDAO() {
		BatchDAO bdao = new BatchDAO();
		System.out.println(bdao.findAll());
		AddressDAO adao = new AddressDAO();
		Address address = new Address("1", "2", "3", "4", "5");
		adao.saveAddress(address);
		

	}
}
