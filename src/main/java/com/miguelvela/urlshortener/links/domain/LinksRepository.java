package com.miguelvela.urlshortener.links.domain;

import java.util.List;

public interface LinksRepository {
    List<Link> GetLinks();
    Link GetByLinkId(String linkId);
}
