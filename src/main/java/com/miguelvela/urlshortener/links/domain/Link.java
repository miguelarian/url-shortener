package com.miguelvela.urlshortener.links.domain;
public class Link {
    private String Url;
    private String LinkId;

    public Link(String url, String link) {
        Url = url;
        LinkId = link;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getLinkId() {
        return LinkId;
    }

    public void setLinkId(String linkId) {
        LinkId = linkId;
    }
}
