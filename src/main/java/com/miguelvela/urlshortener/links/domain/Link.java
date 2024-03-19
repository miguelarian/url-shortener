package com.miguelvela.urlshortener.links.domain;
public class Link {
    private String url;
    private String urlHash;

    public Link(String url) {
        this.url = url;
        this.urlHash = "";
    }

    public String getUrl() {
        return url;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    private void setUrlHash(String urlHash) {
        this.urlHash = urlHash;
    }

    public String getUrlHash() {

        if (this.urlHash == null || this.urlHash.isEmpty()) {
            this.urlHash = this.calculateUrlHash();
        }

        return urlHash;
    }

    private String calculateUrlHash() {
        return null;
    }
}
