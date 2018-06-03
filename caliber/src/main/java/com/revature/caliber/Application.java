package com.revature.caliber;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/*@Bean
	public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
	    HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
	    factory.setEntityManagerFactory(emf);
	    return factory;
	}*/
	
	@Bean
	 public SessionFactory sessionFactory(@Qualifier("entityManagerFactory") EntityManagerFactory emf) {
	     return emf.unwrap(SessionFactory.class);
	 }
	
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/static/pages/");
        resolver.setSuffix(".html");
        return resolver;
    } 

}
