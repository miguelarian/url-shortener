package com.miguelvela.urlshortener.links.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Health {
    @GetMapping("/health")
    public ResponseEntity<HealthStatus> health() {
        HealthStatus status = new HealthStatus("Up and running!");
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    private static class HealthStatus {
        private String status;

        public HealthStatus(String message) {
            this.status = message;
        }
    }
}
