package com.playbaby.bb.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableScheduling
@SpringBootApplication
@EnableWebSecurity
public class BBAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(BBAdminApplication.class, args);
	}
}
