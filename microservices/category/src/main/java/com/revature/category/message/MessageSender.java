package com.revature.category.message;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.category.beans.*;
@Service
public class MessageSender {
	@Autowired
	static
	AmqpTemplate rabbitsender;
	
	public static List<Category> send(String name) {
		List<Category> response = (List<Category>) rabbitsender.convertSendAndReceive("caliber.exchange", "category.queue", name);
		System.out.println(response);
		return response;
		
	}
	
	public static void send(Category category) {
		rabbitsender.convertAndSend("caliber.exchange", "category1.queue", category);
	}
	
	public static Category send(int id) {
		return (Category)rabbitsender.convertSendAndReceive("caliber.exchange", "category1.queue", id);
	}

}
