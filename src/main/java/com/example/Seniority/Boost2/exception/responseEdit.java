package com.example.Seniority.Boost2.exception;

public class responseEdit {

    private Long id;
    private String url;

    public responseEdit(Long id, String url) {
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