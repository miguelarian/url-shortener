package com.miguelvela.urlshortener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlShortenerApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortenerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApplication.class, args);
		LOGGER.info(UrlShortenerApplication.class.toString() + " started...");
	}
}
