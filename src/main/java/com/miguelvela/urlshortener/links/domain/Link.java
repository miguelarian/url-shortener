package com.miguelvela.urlshortener.links.domain;
public class Link {
    private long id;
    private String url;
    private String urlHash;

    public static final String DEFAULT_URL_HASH = "";

    public Link(String url) {
        this.url = url;
        this.urlHash = DEFAULT_URL_HASH;
    }

    public Link(long id, String url, String urlHash) {
        this.url = url;
        this.urlHash = urlHash;
        this.id = id;
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

        if (this.urlHash.equals(DEFAULT_URL_HASH)) {
            this.urlHash = this.calculateUrlHash();
        }

        return this.urlHash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String calculateUrlHash() {

        if (this.url.isEmpty())
        {
            // TODO: This should throw an exception
            return null;
        }

        return String.valueOf(this.url.hashCode());
    }
}
