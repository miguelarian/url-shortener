package com.miguelvela.urlshortener.authentication;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AuthenticationService {

    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
    private static String AUTH_TOKEN_HEADER_VALUE;

    public static Authentication getAuthentication(HttpServletRequest request) {

        if (AUTH_TOKEN_HEADER_VALUE == null) {
            ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
            AUTH_TOKEN_HEADER_VALUE = ctx.getEnvironment().getProperty("api.auth.token");
        }

        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if (apiKey == null || !apiKey.equals(AUTH_TOKEN_HEADER_VALUE)) {
            throw new BadCredentialsException("Authentication failed");
        }

        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}