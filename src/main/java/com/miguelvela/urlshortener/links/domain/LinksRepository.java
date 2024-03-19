package com.miguelvela.urlshortener.links.domain;

import java.util.List;

public interface LinksRepository {
    List<Link> getAll();
    Link getByUrlHash(String urlHash);
    Link create(Link newLink);
}
