package com.miguelvela.urlshortener.links.application;

import com.miguelvela.urlshortener.links.domain.Link;
import com.miguelvela.urlshortener.links.domain.LinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinksServiceImpl implements LinksService {

    @Autowired
    private LinksRepository linksRepository;

    @Override
    public List<Link> getAll() {

        return linksRepository.getAll();
    }

    @Override
    public Link getByUrlHash(String urlHash) {

        return linksRepository.getByUrlHash(urlHash);
    }

    @Override
    public Link addLink(String url) {
        Link newLink = new Link(url);
        return this.linksRepository.create(newLink);
    }
}
