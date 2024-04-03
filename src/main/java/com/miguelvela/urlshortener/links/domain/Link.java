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

        try {
            if (this.urlHash.equals(DEFAULT_URL_HASH)) {
                this.urlHash = this.calculateUrlHash();
            }
        }
        catch(LinkHashGenerationException ex){
            String errorMessage = String.format("It was not possible to generate the link hash for the link: {0}" + this.id);
            System.err.println(errorMessage);
            System.err.println(ex.getMessage());
            return null;
        }

        return this.urlHash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String calculateUrlHash() throws LinkHashGenerationException {

        if (this.url.isEmpty())
        {
            throw new LinkHashGenerationException("The link URL is null");
        }

        return String.valueOf(this.url.hashCode());
    }
}
