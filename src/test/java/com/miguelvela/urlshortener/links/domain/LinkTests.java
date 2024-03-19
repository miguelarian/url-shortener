package com.miguelvela.urlshortener.links.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LinkTests {

	@Test
	void getUrlHash_withEmptyUrl_returnsNull() {
		String emptyUrl = "";
		Link link = new Link(emptyUrl);

		String hash = link.getUrlHash();

		assertNull(hash);
	}

	@Test
	void getUrlHash_withExampleUrl_returnsAHashCode() {
		String exampleUrl = "http://example.com";
		Link link = new Link(exampleUrl);

		String hash = link.getUrlHash();

		assertNotNull(hash);
	}

	@Test
	void getUrlHash_withExampleUrl_returnsUrlStringHashCode() {
		String exampleUrl = "http://example.com";
		Link link = new Link(exampleUrl);

		String expectedHash = String.valueOf(exampleUrl.hashCode());

		String hash = link.getUrlHash();

		assertEquals(hash, expectedHash, "The url has is wrong");
	}
}
