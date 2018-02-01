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
	
	public static List<Address> send(String name) {
		List<Address> response = (List<Address>) rabbitsender.convertSendAndReceive("caliber.exchange", "location.queue", name);
		System.out.println(response);
		return response;
		
	}
	
	public static void send(Address address) {
		rabbitsender.convertAndSend("caliber.exchange", "location.queue", address);
	}
	
	public static Address send(int id) {
		return (Address)rabbitsender.convertSendAndReceive("caliber.exchange", "category1.queue", id);
	}

}

