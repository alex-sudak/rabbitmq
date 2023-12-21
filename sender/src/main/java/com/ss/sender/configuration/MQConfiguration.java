package com.ss.sender.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sergei Iurochkin
 */
@Configuration
public class MQConfiguration {
	static final String queueName = "firstQueue";
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
		return BindingBuilder.bind(queue).to(exchange).with("first.key").noargs();
	}
}
