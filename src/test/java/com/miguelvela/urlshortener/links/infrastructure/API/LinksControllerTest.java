package com.miguelvela.urlshortener.links.infrastructure.API;

import com.miguelvela.urlshortener.links.application.LinksService;
import com.miguelvela.urlshortener.links.domain.Link;
import com.miguelvela.urlshortener.links.infrastructure.API.dto.LinkDto;
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
class LinksControllerTest {

    @InjectMocks
    LinksController linksController;
    @Mock
    LinksService linksService;

    @Test
    void getAll_withSomeLinks_returnsAllLinks() {
        List<Link> testLinks = new ArrayList<>(Arrays.asList(
                new Link("https://google.com"),
                new Link("https://amazon.com"),
                new Link("https://linkedin.com")
        ));
        when(linksService.getAll()).thenReturn(testLinks);


        ResponseEntity<List<LinkDto>> responseEntity = linksController.getAll();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(linksService, times(1)).getAll();
        List<LinkDto> linksReturned = responseEntity.getBody();
        assertThat(linksReturned).isNotNull();
        assertThat(linksReturned.size()).isEqualTo(testLinks.size());
        assertThat(linksReturned.get(0).url().equals(testLinks.get(0).getUrl()));
        assertThat(linksReturned.get(1).url().equals(testLinks.get(1).getUrl()));
        assertThat(linksReturned.get(2).url().equals(testLinks.get(2).getUrl()));
    }

    @Test
    void getAll_withNoLinks_returnsEmptyList() {
        List<Link> testLinks = new ArrayList<>();
        when(linksService.getAll()).thenReturn(testLinks);

        ResponseEntity<List<LinkDto>> responseEntity = linksController.getAll();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(linksService, times(1)).getAll();
        List<LinkDto> linksReturned = responseEntity.getBody();
        assertThat(linksReturned).isNotNull();
        assertThat(linksReturned.size()).isEqualTo(0);
    }

    @Test
    void getByHashUrl_withExistingHashUrl_okResponse() {

        String urlHash = "Example";
        String linkUrl = "http://example.com";
        Link linkMock = new Link(linkUrl);
        when(linksService.getByUrlHash(urlHash)).thenReturn(linkMock);

        ResponseEntity<LinkDto> responseEntity = linksController.getByUrlHash(urlHash);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(linksService, times(1)).getByUrlHash(urlHash);
        LinkDto linkReturned = responseEntity.getBody();
        assertThat(linkReturned).isNotNull();
        assertThat(linkReturned.url()).isEqualTo(linkUrl);
    }

    @Test
    void getByHashUrl_withNoExistingHashUrl_notFoundResponse() {
        String hashUrl = "Example";
        when(linksService.getByUrlHash(hashUrl)).thenReturn(null);

        ResponseEntity<LinkDto> responseEntity = linksController.getByUrlHash(hashUrl);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void getByHashUrl_withEmptyHashUrl_badRequestResponse() {

        ResponseEntity<LinkDto> responseEntity = linksController.getByUrlHash("");

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void getByHashUrl_withNullHashUrl_badRequestResponse() {

        ResponseEntity<LinkDto> responseEntity = linksController.getByUrlHash(null);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void createLink_withEmptyLinkUrl_badRequestResponse() {
        LinkDto requestLinkDto = new LinkDto("", "");

        ResponseEntity<LinkDto> responseEntity = linksController.createLink(requestLinkDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    void createLink_withInvalidLinkUrl_badRequestResponse() {
        String url = "http://invalid-url";
        LinkDto requestLinkDto = new LinkDto(url, "");

        ResponseEntity<LinkDto> responseEntity = linksController.createLink(requestLinkDto);

        verify(linksService, never()).addLink(anyString());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void createLink_withValidLinkUrl_SuccessResponse() {
        String url = "http://www.test.example.com";
        LinkDto requestLinkDto = new LinkDto(url, "");
        Link linkMock = new Link(url);
        when(linksService.addLink(url)).thenReturn(linkMock);

        ResponseEntity<LinkDto> responseEntity = linksController.createLink(requestLinkDto);

        verify(linksService, times(1)).addLink(url);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getHeaders().getLocation().toString()).isEqualTo("/links/1582525482");
    }
}