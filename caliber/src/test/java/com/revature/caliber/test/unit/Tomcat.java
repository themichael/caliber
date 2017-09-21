package com.revature.caliber.test.unit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration
//@ComponentScan("com.revature")
@ImportResource(value={"integration-test.xml", "spring-security.xml"})
public class Tomcat{

	private static Log log = LogFactory.getLog(Tomcat.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Tomcat.class, args);
	}
	
	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {
			@Override
			public void contextInitialized(ServletContextEvent sce) {
				log.info("ServletContext initialized");
			}

			@Override
			public void contextDestroyed(ServletContextEvent sce) {
				log.info("ServletContext destroyed");
			}
		};
	}
}
