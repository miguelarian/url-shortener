package com.miguelvela.urlshortener.links.infrastructure.Persistence;

public class LinkVO {

    private String url;
    private String linkId;

    public LinkVO(String url, String linkId) {
        this.url = url;
        this.linkId = linkId;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
