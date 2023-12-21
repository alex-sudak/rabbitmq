package com.ss.reciever;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RecieverApplication {
	static final String queueName = "testQueue";

	@Bean
	public Queue myQueue() {
		return new Queue(queueName, false);
	}

	@RabbitListener(queues = queueName)
	public void listen(String in) {
		System.out.println("Message read from myQueue : " + in);
	}

	public static void main(String[] args) {
		SpringApplication.run(RecieverApplication.class, args);
	}

}
