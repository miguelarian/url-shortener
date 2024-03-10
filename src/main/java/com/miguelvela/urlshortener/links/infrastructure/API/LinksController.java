package com.miguelvela.urlshortener.links.infrastructure.API;

import com.miguelvela.urlshortener.links.application.LinksService;
import com.miguelvela.urlshortener.links.domain.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LinksController {
    @Autowired
    private LinksService linksService;

    @GetMapping("/links")
    public List<Link> getAll() {

        return this.linksService.GetAllLinks();
    }

    @GetMapping("/links/{linkId}")
    public ResponseEntity<Link> getById(@PathVariable String linkId) {

        if(linkId == null || linkId.isEmpty()) {
            return  ResponseEntity
                    .badRequest()
                    .build();
        }

        Link linkRequested = this.linksService.GetLinkById(linkId);

        if (linkRequested == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity
                .ok()
                .body(linkRequested);
    }
}
