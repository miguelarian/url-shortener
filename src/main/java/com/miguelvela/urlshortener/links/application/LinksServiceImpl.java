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

        return linksRepository.getLinks();
    }

    @Override
    public Link getLinkById(String linkId) {

        return linksRepository.getByLinkId(linkId);
    }

    @Override
    public Link addLink(String linkUrl) {
        Link newLink = new Link(linkUrl);
        return this.linksRepository.createLink(newLink);
    }
}
