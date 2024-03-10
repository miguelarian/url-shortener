package com.miguelvela.urlshortener.links.infrastructure.API;

import com.miguelvela.urlshortener.links.application.LinksService;
import com.miguelvela.urlshortener.links.domain.Link;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {

    @Autowired
    private LinksService linksService;

    @RequestMapping(value = "/redirect/{linkId}", method = RequestMethod.GET)
    public ResponseEntity redirect(HttpServletResponse httpServletResponse, @PathVariable String linkId) {

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

        return redirectTo(linkRequested, httpServletResponse);
    }

    private ResponseEntity redirectTo(Link link,HttpServletResponse httpServletResponse) {

        String destinationUrl = link.getUrl();
        httpServletResponse.setHeader("Location", destinationUrl);
        httpServletResponse.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());

        return  ResponseEntity
                .status(HttpStatus.TEMPORARY_REDIRECT)
                .build();
    }
}