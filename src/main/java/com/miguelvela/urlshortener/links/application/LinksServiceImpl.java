package com.miguelvela.urlshortener.links.application;

import com.miguelvela.urlshortener.links.domain.Link;
import com.miguelvela.urlshortener.links.infrastructure.Persistence.LinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinksServiceImpl implements LinksService {

    @Autowired
    private LinksRepository linksRepository;

    @Override
    public List<Link> GetAllLinks() {

        return linksRepository.GetLinks();
    }

    @Override
    public Link GetLinkById(String linkId) {

        return linksRepository.GetByLinkId(linkId);
    }
}
