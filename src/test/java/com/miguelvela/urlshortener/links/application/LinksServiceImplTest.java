package com.miguelvela.urlshortener.links.application;

import com.miguelvela.urlshortener.links.domain.Link;
import com.miguelvela.urlshortener.links.domain.LinksRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LinksServiceImplTest {

    @Mock
    LinksRepository linksRepository;

    @InjectMocks
    LinksServiceImpl linksService;

    @Test
    void getAll_withNoLinks_returnsEmptyList() {
        when(linksRepository.getAll()).thenReturn(Collections.emptyList());

        var result = this.linksService.getAll();

        assertEquals(0, result.size());
        verify(linksRepository, times(1)).getAll();
    }

    @Test
    void getAll_withLinks_returnsNoEmptyList() {
        Link l1 = new Link("url1");
        Link l2 = new Link("url2");
        var links = List.of(l1, l2);
        when(linksRepository.getAll()).thenReturn(links);

        var result = this.linksService.getAll();

        assertEquals(2, result.size());
        verify(linksRepository, times(1)).getAll();
    }

    @Test
    void getByUrlHash_withNonExistingHash_returnsNull() {
        String inexistentHash = "NON_EXISTING_HASH";
        when(linksRepository.getByUrlHash(inexistentHash)).thenReturn(null);

        var result = this.linksService.getByUrlHash(inexistentHash);

        assertNull(result);
        verify(linksRepository, times(1)).getByUrlHash(inexistentHash);
    }

    @Test
    void getByUrlHash_withExistingHash_returnsLink() {
        String validHash = "VALID_HASH";
        String linkUrl = "url";
        Link linkFound = new Link(linkUrl);
        when(linksRepository.getByUrlHash(validHash)).thenReturn(linkFound);

        var result = this.linksService.getByUrlHash(validHash);

        assertEquals(linkUrl, result.getUrl());
        verify(linksRepository, times(1)).getByUrlHash(validHash);
    }

    @Test
    void getByUrlHash_withNullHash_returnsNull() {
        var result = this.linksService.getByUrlHash(null);

        assertNull(result);
    }

    @Test
    void getByUrlHash_withEmptyHash_returnsNull() {
        var result = this.linksService.getByUrlHash("");

        assertNull(result);
    }

    @Test
    void addLink_withNullUrl_returnsNull() {
        var result = this.linksService.addLink(null);

        assertNull(result);
    }

    @Test
    void addLink_withEmptyUrl_returnsNull() {
        var result = this.linksService.addLink("");

        assertNull(result);
    }
    @Test
    void addLink_withValidUrl_returnsNewLink() {
        String linkUrl = "url";
        Link newLink = new Link(linkUrl);
        when(linksRepository.create(any(Link.class))).thenReturn(newLink);

        var result = this.linksService.addLink(linkUrl);

        assertEquals(linkUrl, result.getUrl());
        verify(linksRepository, times(1)).create(any(Link.class));
    }
}