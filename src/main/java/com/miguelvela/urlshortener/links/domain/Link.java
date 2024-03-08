package com.miguelvela.urlshortener.links.domain;
public class Link {
    private String Url;
    private String Link;

    public Link(String url, String link) {
        Url = url;
        Link = link;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }
}
