package com.revature.caliber.test.unit;

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
import com.revature.caliber.data.AddressDAO;


public class AddressDAOTest  extends CaliberTest{
/*
 * save    Ruha
getAll  Ruha
findAll  Ruha
getAddressByInt  Ruha
update  Ruha
getOne Ruha
 */
	private AddressDAO addressdao;
	
	public void setAddressdao(AddressDAO addressdao) {
		this.addressdao = addressdao;
	}
	
	@Test
	public void saveAddressDAO(){
		addressdao.save(null);
	}
	@Test
	public void getAllAddressDAO(){
		
	}
	@Test
	public void findAllAddressDAO(){
		
	}
	@Test
	public void getAddressByIntAddressDAO(){
		
	}
	@Test
	public void updateAddressDAO(){
		
	}
	@Test
	public void getOne(){
		
	}
}

