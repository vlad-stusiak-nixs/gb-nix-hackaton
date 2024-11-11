package org.gunbros.demo;

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

	public enum Status {
		OK, NOT_OK
	}

	public record StatusResponse(Status status) {
	}

	@GetMapping(path = "/healthz")
	public StatusResponse getStatus() {
		return new StatusResponse(Status.OK);
	}
}
