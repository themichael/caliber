package com.revature.caliber.gateway.training;

import com.revature.caliber.gateway.services.TrainingService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class BatchGatewayTest {
    private static ApplicationContext context;
    private static TrainingService trainingService;

    /**
     * Retrieve application context and get the TrainerBatchController bean
     */
    @BeforeClass
    public static void preClass() {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        trainingService = context.getBean(TrainingService.class);
    }

    @Test
    public void getAll(){
/*        Trainer trainer = new Trainer();
        trainer.setTraineeId(1);
        List<Batch> batches = trainingService.allBatch();*/
       /* System.out.println(batches);*/
    }

    @Test
    public void getBatch(){

        //trainingService.getBatch(1);
    }
}
