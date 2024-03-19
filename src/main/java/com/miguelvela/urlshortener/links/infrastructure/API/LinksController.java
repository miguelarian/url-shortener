package com.miguelvela.urlshortener.links.infrastructure.API;

import com.miguelvela.urlshortener.links.application.LinksService;
import com.miguelvela.urlshortener.links.domain.Link;
import com.miguelvela.urlshortener.links.infrastructure.API.dto.LinkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class LinksController {
    @Autowired
    private LinksService linksService;

    @GetMapping("/links")
    public ResponseEntity<List<Link>> getAll() {

        return ResponseEntity
                .ok()
                .body(this.linksService.getAllLinks());
    }

    @GetMapping("/links/{linkId}")
    public ResponseEntity<Link> getById(@PathVariable String linkId) {

        if(linkId == null || linkId.isEmpty()) {
            return  ResponseEntity
                    .badRequest()
                    .build();
        }

        Link linkRequested = this.linksService.getLinkById(linkId);

        if (linkRequested == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity
                .ok()
                .body(linkRequested);
    }

    @PostMapping("/links")
    public ResponseEntity<LinkDto> createLink(@RequestBody LinkDto newLink) {

        Link linkCreated = this.linksService.addLink(newLink.url());

        LinkDto responseLink = new LinkDto(linkCreated.getUrl(), linkCreated.getUrlHash());

        return ResponseEntity
                .created(URI.create("/links/" + linkCreated.getUrlHash()))
                .body(responseLink);
    }
}
