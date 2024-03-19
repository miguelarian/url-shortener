package com.miguelvela.urlshortener.links.infrastructure.Persistence;

import com.miguelvela.urlshortener.links.domain.Link;
import com.miguelvela.urlshortener.links.domain.LinksRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryLinksRepositoryImpl implements LinksRepository {

    private final List<LinkVO> inMemoryLinks = new ArrayList<>(Arrays.asList(
            new LinkVO("https://www.example.com", "Example"),
            new LinkVO("https://www.google.com", "Google"),
            new LinkVO("https://www.github.com", "GitHub")
    ));

    @Override
    public List<Link> getAll() {

        return this.inMemoryLinks
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Link getByUrlHash(String urlHash) {

        LinkVO linkVO = this.inMemoryLinks
                .stream()
                .filter(link -> link.getLinkId().equals(urlHash))
                .findFirst()
                .orElse(null);

        if(linkVO != null) {
            return new Link(linkVO.getUrl());
        }

        return null;
    }

    @Override
    public Link create(Link newLink) {
        LinkVO newLinkVO = new LinkVO(newLink.getUrl(), newLink.getUrlHash());
        this.inMemoryLinks.add(newLinkVO);
        return newLink;
    }

    private Link mapToDomain(LinkVO linkVO) {
        return new Link(linkVO.getUrl());
    }
}
