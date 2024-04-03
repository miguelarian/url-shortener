package com.miguelvela.urlshortener.links.domain;

public class LinkHashGenerationException extends Exception {
    public LinkHashGenerationException() {
        super();
    }

    public LinkHashGenerationException(String message) {
        super(message);
    }

    public LinkHashGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public LinkHashGenerationException(Throwable cause) {
        super(cause);
    }

    protected LinkHashGenerationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
