package com.miguelvela.urlshortener.links.infrastructure.Persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "LINKS")
public class LinkVO {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "URL")
    private String url;
    @Column(name = "URL_HASH")
    private String urlHash;

    public LinkVO() {
    }

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

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }
}
