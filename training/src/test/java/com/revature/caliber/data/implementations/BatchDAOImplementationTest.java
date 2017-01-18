package com.revature.caliber.data.implementations;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class BatchDAOImplementationTest {
    private static ApplicationContext context;

    @BeforeClass
    public static void preClass () {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
    }

/*
    @Test
    public
*/

}
