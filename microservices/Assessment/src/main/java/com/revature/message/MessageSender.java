package com.revature.message;


import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.beans.*;
@Service
public class MessageSender {
	@Autowired
	static
	AmqpTemplate rabbitsender;
	
	public static List<Assessment> send(String name) {
		List<Assessment> response = (List<Assessment>) rabbitsender.convertSendAndReceive("caliber.exchange", "category.queue", name);
		System.out.println(response);
		return response;
		
	}
	
	public static void send(Assessment assessment) {
		rabbitsender.convertAndSend("caliber.exchange", "category1.queue", assessment);
	}
	
	public static Assessment send(int id) {
		return (Assessment)rabbitsender.convertSendAndReceive("caliber.exchange", "category1.queue", id);
	}
	public static Assessment send(int id, int week) {
		return (Assessment)rabbitsender.convertSendAndReceive("caliber.exchange", "category1.queue", id);
	}
	public static Assessment send1(Assessment assessment) {
		return (Assessment)rabbitsender.convertSendAndReceive("caliber.exchange", "category2.queue", assessment);
	}
	public static List<Assessment> send1(int id) {
		return (List<Assessment>)rabbitsender.convertSendAndReceive("caliber.exchange", "category1.queue", id);
	}

}

