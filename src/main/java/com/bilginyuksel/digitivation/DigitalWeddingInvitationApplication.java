package com.bilginyuksel.digitivation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class DigitalWeddingInvitationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalWeddingInvitationApplication.class, args);
	}
}
