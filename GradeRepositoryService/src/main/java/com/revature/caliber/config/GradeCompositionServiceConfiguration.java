package com.revature.caliber.config;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.gson.JsonObject;
import com.revature.caliber.model.Grade;
import com.revature.caliber.service.GradeCompositionMessagingService;
import com.revature.caliber.service.GradeCompositionService;

@Configuration
public class GradeCompositionServiceConfiguration {
	
		@Autowired
		private GradeCompositionMessagingService mms;
	   @Bean
	   public AmqpTemplate rabbitTemplate(ConnectionFactory factory) {
	       RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
	       rabbitTemplate.setExchange("revature.caliber.repos");
	       return new RabbitTemplate(factory);
	   }
	   
	   @Bean
		public GradeCompositionService gradeCompositionService() {
			return new GradeCompositionService();
		}
	   /*
	   @Bean
		public CommandLineRunner runner() {
		  
			return args -> {
				Grade grade = gcs.findOne((long) 2063);
				if(grade == null) {
					System.out.println("grade is null");
				}
				System.out.println(grade);
			};
			
		}
	  */
}
