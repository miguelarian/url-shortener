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
    public ResponseEntity<List<LinkDto>> getAll() {

        List<LinkDto> resultList = this.linksService
                .getAll()
                .stream()
                .map(LinkDto::fromLink)
                .toList();

        return ResponseEntity
                .ok()
                .body(resultList);
    }

    @GetMapping("/links/{urlHash}")
    public ResponseEntity<LinkDto> getById(@PathVariable String urlHash) {

        if(urlHash == null || urlHash.isEmpty()) {
            return  ResponseEntity
                    .badRequest()
                    .build();
        }

        Link linkRequested = this.linksService.getByUrlHash(urlHash);

        if (linkRequested == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity
                .ok()
                .body(LinkDto.fromLink(linkRequested));
    }

    @PostMapping("/links")
    public ResponseEntity<LinkDto> createLink(@RequestBody LinkDto linkDto) {

        Link linkCreated = this.linksService.addLink(linkDto.url());

        LinkDto responseLink = LinkDto.fromLink(linkCreated);

        return ResponseEntity
                .created(URI.create("/links/" + responseLink.hash()))
                .body(responseLink);
    }
}
