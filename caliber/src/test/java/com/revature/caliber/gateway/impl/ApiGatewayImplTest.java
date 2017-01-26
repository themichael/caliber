package com.revature.caliber.gateway.impl;

import com.revature.caliber.gateway.ApiGateway;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shehar on 1/26/2017.
 */
public class ApiGatewayImplTest {

    private static ApplicationContext context;
    private static ApiGateway apiGateway;

    @Before
    public void setUp() throws Exception {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        apiGateway = context.getBean(ApiGateway.class);
    }

    @Test
    public void getAggregatedGradesForTrainee() throws Exception {
        HashMap<String, String[]> grades = apiGateway.getAggregatedGradesForTrainee(1);
        for (Map.Entry<String, String[]> entry : grades.entrySet())
        {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
    }

}