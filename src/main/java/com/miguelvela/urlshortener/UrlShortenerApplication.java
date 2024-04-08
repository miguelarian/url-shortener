package com.miguelvela.urlshortener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
public class UrlShortenerApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortenerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApplication.class, args);
		LOGGER.info(UrlShortenerApplication.class + " started...");
	}
}
