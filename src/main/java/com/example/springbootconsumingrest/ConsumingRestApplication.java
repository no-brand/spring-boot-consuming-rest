package com.example.springbootconsumingrest;

import com.example.springbootconsumingrest.model.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class ConsumingRestApplication {

	// Spring uses apache's common-logging for logging.
	// Logger helps to manage several levels, and show pre-defined useful information, including time, thread, et cetra.
	// 2021-09-16 12:26:56.091  INFO 7648 --- [           main] c.e.s.ConsumingRestApplication
	private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
	}

	// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html
	// Bean is an object, Spring IoC container manages.
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	// Functional interface is an interface who has a single abstract method; also it can be lambda statement.
	// CommandLineRunner : void run(String... args)
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote = restTemplate.getForObject("https://quoters.apps.pcfone.io/api/random", Quote.class);
			log.info(quote.toString());
		};
	}
}
