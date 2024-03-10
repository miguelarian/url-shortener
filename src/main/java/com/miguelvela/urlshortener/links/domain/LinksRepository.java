package com.miguelvela.urlshortener.links.domain;

import com.miguelvela.urlshortener.links.domain.Link;

import java.util.List;

public interface LinksRepository {
    List<Link> GetLinks();
    Link GetByLinkId(String linkId);
}
