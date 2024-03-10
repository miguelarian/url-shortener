package com.miguelvela.urlshortener.links.infrastructure.API;

import com.miguelvela.urlshortener.links.application.LinksService;
import com.miguelvela.urlshortener.links.domain.Link;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LinksControllerTest {

    @InjectMocks
    LinksController linksController;
    @Mock
    LinksService linksService;

    private static List<Link> testLinks;

    @BeforeAll
    public static void initializeInMemoryLinks() {
        testLinks = new ArrayList<>(Arrays.asList(
                new Link("https://google.com", "Google"),
                new Link("https://amazon.com", "Amazon"),
                new Link("https://linkedin.com", "LinkedIn")
        ));
    }

    @Test
    void getAll_returnsAllLinks() {

        when(linksService.GetAllLinks()).thenReturn(testLinks);

        ResponseEntity<List<Link>> responseEntity = linksController.getAll();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(linksService, times(1)).GetAllLinks();

        List<Link> linksReturned = responseEntity.getBody();
        assertThat(linksReturned).isNotNull();
        assertThat(linksReturned.size()).isEqualTo(testLinks.size());
    }

    @Test
    void getById_withExistingId_okResponse() {

        String linkId = "Example";
        String linkUrl = "http://example.com";
        Link linkMock = new Link(linkUrl, linkId);
        when(linksService.GetLinkById(linkId)).thenReturn(linkMock);

        ResponseEntity<Link> responseEntity = linksController.getById(linkId);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(linksService, times(1)).GetLinkById(linkId);

        Link linkReturned = responseEntity.getBody();
        assertThat(linkReturned).isNotNull();
        assertThat(linkReturned.getLinkId()).isEqualTo(linkId);
        assertThat(linkReturned.getUrl()).isEqualTo(linkUrl);
    }

    @Test
    void getById_withNoExistingLinkId_notFoundResponse() {

        String linkId = "Example";
        when(linksService.GetLinkById(linkId)).thenReturn(null);

        ResponseEntity<Link> responseEntity = linksController.getById(linkId);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void getById_withEmptyLinkId_badRequestResponse() {

        ResponseEntity<Link> responseEntity = linksController.getById("");

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void getById_withNullLinkId_badRequestResponse() {

        ResponseEntity<Link> responseEntity = linksController.getById(null);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}