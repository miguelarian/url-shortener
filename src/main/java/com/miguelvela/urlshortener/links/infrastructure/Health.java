package com.miguelvela.urlshortener.links.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Health {
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("Up and running!", HttpStatus.OK);
    }
}
