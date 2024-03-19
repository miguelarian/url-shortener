package com.miguelvela.urlshortener.links.domain;
public class Link {
    private String url;
    private String linkId;

    public Link(String url) {
        this.url = url;
    }
    public Link(String url, String link) {
        this.url = url;
        linkId = link;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getUrlHash() {
        return null;
    }
}
