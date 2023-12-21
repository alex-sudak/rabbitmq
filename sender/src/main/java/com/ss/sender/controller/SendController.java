package com.ss.sender.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergei Iurochkin
 */
@Log4j2
@RestController
@RequestMapping("/send")
@RequiredArgsConstructor
public class SendController {
	private final RabbitTemplate rabbitTemplate;
	private static Long counter = 0L;
	static final String exchangeName = "testExchange";

	@GetMapping("/qe1")
	public ResponseEntity<String> sendMessageToQ1(@RequestParam String message) {
		rabbitTemplate.convertAndSend(exchangeName, "first.key", message);
		log.info(message + " is send! Count send object = " + ++counter);

		return ResponseEntity.ok().body("Send to Q1!");
	}
	@GetMapping("/qe2")
	public ResponseEntity<String> sendMessageToQ2(@RequestParam String message) {
		rabbitTemplate.convertAndSend(exchangeName, "second.key", message);
		log.info(message + " is send! Count send object = " + ++counter);

		return ResponseEntity.ok().body("Send to Q2!");
	}
}
