package com.revature.caliber.gateway.impl;

import com.revature.caliber.gateway.ApiGateway;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.HashMap;

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
    @Ignore
    public void getAggregatedGradesForTrainee() throws Exception {
        HashMap<String, Double[]> grades = apiGateway.getTechGradeDataForTrainee(1);

        for (String grade : grades.keySet()) {
            System.out.print(grade + " -> [");
            for (Double d : grades.get(grade)) {
                System.out.print(d + ", ");
            }
            System.out.println("]");
        }

    }

}