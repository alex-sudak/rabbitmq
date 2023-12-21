package com.ss.receiver2.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sergei Iurochkin
 */
@Configuration
public class MQConfiguration {
	static final String queueName = "secondQueue";
	static final String exchangeName = "testExchange";

	@Bean
	public Queue myQueue() {
		return new Queue(queueName, false);
	}

	@Bean
	Exchange exchange() {
		return new TopicExchange(exchangeName, false, false);
	}
	@Bean
	Binding binding(Queue queue, Exchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("second.key").noargs();
	}

	@RabbitListener(queues = queueName)
	public void listen(String message) {
		System.out.println("Message read from secondQueue : " + message);
	}
}
