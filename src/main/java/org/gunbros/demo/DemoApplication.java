package org.gunbros.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

	public enum Status {
		OK, NOT_OK
	}

	public record StatusResponse(Status status) {
	}

	@GetMapping(path = "/healthz")
	public StatusResponse getStatus() {
		final var healthResponse = new StatusResponse(Status.OK);
		LOGGER.info("Responding to health check with status {}", healthResponse);
		return healthResponse;
	}
}
