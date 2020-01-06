package com.github.shuvava.DependencyInjection;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DependencyInjectionApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DependencyInjectionApplication.class);

	@Autowired
	List<SimpleService> simpleServices;

	public static void main(String[] args) {
		SpringApplication.run(DependencyInjectionApplication.class, args);
	}

	public void run(final String... strings) throws Exception {
		for (SimpleService simpleService : simpleServices) {
			log.info("Echo: " + simpleService.echo("test"));
		}
	}
}
