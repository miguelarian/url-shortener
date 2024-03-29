package com.miguelvela.urlshortener.links.domain;

import com.miguelvela.urlshortener.links.infrastructure.Persistence.LinkVO;

import java.util.List;

public interface LinksRepository {
    List<Link> getAll();
    Link getByUrlHash(String urlHash);
    Link create(Link newLink);

    default Link mapToDomain(LinkVO linkVO) {
        return new Link(linkVO.getUrl(), linkVO.getUrlHash());
    }
}
