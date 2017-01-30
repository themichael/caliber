package com.revature.caliber.gateway.services.impl;

import com.revature.caliber.gateway.services.TrainingService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

/**
 * Created by Shehar on 1/27/2017.
 */
public class TrainingServiceImplTest {

    private static ApplicationContext context;
    private static TrainingService trainingService;

    @Before
    public void setUp() throws Exception {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        trainingService = context.getBean(TrainingService.class);
    }

    @Test
    public void getWeekByBatch() throws Exception {
        List<com.revature.caliber.beans.Week> week = trainingService.getWeekByBatch(1);
        for(int i=0;i<week.size();i++){
            System.out.println(week.get(i).getWeekNumber());
        }

    }

}