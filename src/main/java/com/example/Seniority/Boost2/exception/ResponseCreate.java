package com.example.Seniority.Boost2.exception;

public class ResponseCreate {

    private Long id;
    private String url;

    public ResponseCreate(Long id, String url) {
        this.id = id;
        this.url = url;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
