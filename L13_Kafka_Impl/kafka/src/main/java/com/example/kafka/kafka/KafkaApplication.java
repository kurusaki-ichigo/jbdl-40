package com.example.kafka.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaApplication {


	public static final String KAFKA_TOPIC = "MAROON_5";
	public static final String ORDER_CREATED = "ORDER_CREATED";

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

}
