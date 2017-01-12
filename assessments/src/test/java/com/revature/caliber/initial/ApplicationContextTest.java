package com.revature.caliber.initial;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationContextTest {

	@Test
	public void context() {
		try {
			ApplicationContext ctxt = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
			DataSource ds = (DataSource) ctxt.getBean("dataSource");
			Connection conn = ds.getConnection();
			System.out.println("Connection " + conn);
			Assert.assertNotNull(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
