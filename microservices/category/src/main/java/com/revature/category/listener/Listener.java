package com.revature.category.listener;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import com.revature.category.beans.Category;

public class Listener {
	@RabbitListener(queues = "category.queue")
	public String recieve(String msg) {
		System.out.println(msg);
		return msg;
	}
	
	@RabbitListener(queues = "category1.queue")
	public List<Category> recieve(List<Category> msg) {
		System.out.println(msg);
		return msg;
	}
	
	@RabbitListener(queues = "category2.queue")
	public int receive(int id) {
		return 1;
	}
	
	
}
