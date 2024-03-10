package com.miguelvela.urlshortener.links.application;

import com.miguelvela.urlshortener.links.domain.Link;

import java.util.List;

public interface LinksService {
    List<Link> GetAllLinks();
    Link GetLinkById(String linkId);
}
