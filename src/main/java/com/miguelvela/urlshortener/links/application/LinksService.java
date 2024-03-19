package com.miguelvela.urlshortener.links.application;

import com.miguelvela.urlshortener.links.domain.Link;

import java.util.List;

public interface LinksService {
    List<Link> getAll();
    Link getByUrlHash(String urlHash);
    Link addLink(String url);
}
