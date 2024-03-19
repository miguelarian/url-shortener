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
    public List<Link> getAllLinks() {

        return linksRepository.getAll();
    }

    @Override
    public Link getLinkById(String linkId) {

        return linksRepository.getByUrlHash(linkId);
    }

    @Override
    public Link addLink(String url) {
        Link newLink = new Link(url);
        return this.linksRepository.create(newLink);
    }
}
