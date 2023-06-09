package com.lookup_and_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LookupQueriesJpaMethodsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LookupQueriesJpaMethodsApplication.class, args);
	}

}
