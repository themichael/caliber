package com.revature.caliber;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.caliber.data.TrainerDAO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/beans.xml", "file:src/main/webapp/WEB-INF/spring-security.xml"})
public class TrainerDAOTest {
	
	private static Logger log = Logger.getLogger(TrainerDAOTest.class);
	
	@Autowired
	private TrainerDAO trainerDAO;
	
	@Test
	public void testing() {
		log.info("Don't forget to test your code :)");
		assertNotNull(trainerDAO);
		assertThat(trainerDAO, CoreMatchers.instanceOf(TrainerDAO.class));
	}	
}
