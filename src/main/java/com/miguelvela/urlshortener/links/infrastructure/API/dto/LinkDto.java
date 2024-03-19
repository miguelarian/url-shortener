package com.miguelvela.urlshortener.links.infrastructure.API.dto;

import com.miguelvela.urlshortener.links.domain.Link;

public record LinkDto (String url, String hash) {
    public static LinkDto fromLink(Link link) {
        return new LinkDto(link.getUrl(), link.getUrlHash());
    }
}
