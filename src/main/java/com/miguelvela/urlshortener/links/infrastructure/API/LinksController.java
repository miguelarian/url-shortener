package com.miguelvela.urlshortener.links.infrastructure.API;

import com.miguelvela.urlshortener.links.application.LinksService;
import com.miguelvela.urlshortener.links.domain.Link;
import com.miguelvela.urlshortener.links.infrastructure.API.dto.LinkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
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
    public ResponseEntity<LinkDto> getByUrlHash(@PathVariable String urlHash) {

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

        if(linkDto == null
            || linkDto.url().isEmpty()
            || !validateUrl(linkDto.url()))
        {
            return  ResponseEntity
                    .badRequest()
                    .build();
        }

        Link linkCreated = this.linksService.addLink(linkDto.url());

        LinkDto responseLink = LinkDto.fromLink(linkCreated);

        return ResponseEntity
                .created(URI.create("/links/" + responseLink.hash()))
                .body(responseLink);
    }

    private boolean validateUrl(String url) {
        final String URL_PATTERN = "^((https?|ftp)://)?(www\\.)?[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,}){1,}(\\/\\S*)?$";
        Pattern pattern = Pattern.compile(URL_PATTERN);

        return pattern.matcher(url).matches();
    }
}
