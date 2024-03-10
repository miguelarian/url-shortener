package com.miguelvela.urlshortener.links.infrastructure.API;

import com.miguelvela.urlshortener.links.application.LinksService;
import com.miguelvela.urlshortener.links.domain.Link;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;

import java.net.URISyntaxException;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RedirectControllerTest {

    @InjectMocks
    RedirectController redirectController;
    @Mock
    LinksService linksService;

    @Test
    void redirect_withExistingLink_302redirection() throws URISyntaxException {
        String linkId = "Google";
        String linkUrl = "https://google.com";
        Link linkMock = new Link(linkUrl, linkId);

        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();
        when(linksService.getLinkById(linkId)).thenReturn(linkMock);

        ResponseEntity responseEntity = redirectController.redirect(httpServletResponse, linkId);

        verify(linksService, times(1)).getLinkById(linkId);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.TEMPORARY_REDIRECT);
        assertThat(Objects.requireNonNull(responseEntity.getHeaders().getLocation()).toString()).isEqualTo(linkUrl);
    }
}