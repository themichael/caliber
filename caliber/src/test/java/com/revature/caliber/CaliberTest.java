package com.revature.caliber;

import static org.junit.Assert.assertNotNull;

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

/**
 * To create a consistent testing approach that provides
 * 		1) transient data in an embedded H2 database,
 * 		2) a single ApplicationContext instance across tests,
 * 		3) a JdbcTemplate object to perform low-level SQL 
 * please inherit from this class in your unit/integration tests.
 * 
 * @author Patrick Walsh
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:integration-test.xml" })
@Sql(scripts = "/setup.sql", config = @SqlConfig(transactionMode = TransactionMode.ISOLATED), executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/teardown.sql", config = @SqlConfig(transactionMode = TransactionMode.ISOLATED), executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class CaliberTest {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Test
	public void testJdbcTemplate(){
		assertNotNull(jdbcTemplate);
	}
	
}
