package com.miguelvela.urlshortener.links.infrastructure;

import com.miguelvela.urlshortener.links.domain.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController

public class LinksController {

    private List<Link> inMemoryLinks = new ArrayList<>(Arrays.asList(
            new Link("https://www.example.com", "Example"),
            new Link("https://www.google.com", "Google"),
            new Link("https://www.github.com", "GitHub")
    ));

    @GetMapping("/links")
    public List<Link> getAll() {

        return this.inMemoryLinks;
    }
}
