package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;

import org.jboss.logging.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
class LoadDatabase {
	Logger log = Logger.getLogger(LoadDatabase.class.getName());

	@Bean
	CommandLineRunner initDatabase() {
		return args -> {
		};
	}
}