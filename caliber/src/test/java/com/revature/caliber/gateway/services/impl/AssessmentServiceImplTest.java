package com.revature.caliber.gateway.services.impl;

import com.revature.caliber.beans.Grade;
import com.revature.caliber.gateway.services.AssessmentService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

/**
 * Created by Shehar on 1/26/2017.
 */
public class AssessmentServiceImplTest {

    private static ApplicationContext context;
    private static AssessmentService assessmentService;

    @Before
    public void setUp() throws Exception {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        assessmentService = context.getBean(AssessmentService.class);
    }

    @Test
    public void getGradesByTraineeId() throws Exception {
        List<Grade> grades = assessmentService.getGradesByTraineeId(1);
        for(int i=0;i<grades.size();i++){
            System.out.println(grades.get(i).toString());
        }
    }

}