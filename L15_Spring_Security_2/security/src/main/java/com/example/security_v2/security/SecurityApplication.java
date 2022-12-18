package com.example.security_v2.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication

/**
 * if you are using spring boot version is 2.7.x
 * @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
 */
/**
 * if you are using 3.0.0
 */
@EnableMethodSecurity(securedEnabled = true)
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

}
