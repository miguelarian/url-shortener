package com.miguelvela.urlshortener.links.infrastructure.Persistence;

public class LinkVO {

    private String url;
    private String urlHash;

    public LinkVO(String url, String urlHash) {
        this.url = url;
        this.urlHash = urlHash;
    }

    public String getUrlHash() {
        return urlHash;
    }

    public void setUrlHash(String urlHash) {
        this.urlHash = urlHash;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
