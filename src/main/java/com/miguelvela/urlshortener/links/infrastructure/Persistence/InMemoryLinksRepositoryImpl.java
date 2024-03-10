package com.miguelvela.urlshortener.links.infrastructure.Persistence;

import com.miguelvela.urlshortener.links.domain.Link;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class InMemoryLinksRepositoryImpl implements LinksRepository {

    private List<Link> inMemoryLinks = new ArrayList<>(Arrays.asList(
            new Link("https://www.example.com", "Example"),
            new Link("https://www.google.com", "Google"),
            new Link("https://www.github.com", "GitHub")
    ));

    @Override
    public List<Link> GetLinks() {

        return this.inMemoryLinks;
    }

    @Override
    public Link GetByLinkId(String linkId) {

        return this.inMemoryLinks
                .stream()
                .filter(link -> link.getLinkId().equals(linkId))
                .findFirst()
                .orElse(null);
    }
}
