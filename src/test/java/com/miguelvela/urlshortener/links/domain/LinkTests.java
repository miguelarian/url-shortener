package com.miguelvela.urlshortener.links.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class LinkTests {

	@Test
	void getUrlHash_withEmptyUrl_returnsNull() {
		String emptyUrl = "";
		Link link = new Link(emptyUrl);

		String hash = link.getUrlHash();

		assertNull(hash);
	}

}
