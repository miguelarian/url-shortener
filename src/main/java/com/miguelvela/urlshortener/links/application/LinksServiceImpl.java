package com.miguelvela.urlshortener.links.application;

import com.miguelvela.urlshortener.links.domain.Link;
import com.miguelvela.urlshortener.links.domain.LinksRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinksServiceImpl implements LinksService {

    @Autowired
    @Qualifier("MySqlLinksRepository")
    private LinksRepository linksRepository;

    @Override
    public List<Link> getAll() {

        return linksRepository.getAll();
    }

    @Override
    public Link getByUrlHash(String urlHash) {

        if(urlHash == null || urlHash.isEmpty()){
            return null;
        }

        return linksRepository.getByUrlHash(urlHash);
    }

    @Override
    @Transactional
    public Link addLink(String url) {
        if(url == null || url.isEmpty()){
            return null;
        }

        Link newLink = new Link(url);
        return this.linksRepository.create(newLink);
    }
}
