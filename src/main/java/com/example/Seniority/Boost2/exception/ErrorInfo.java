package com.example.Seniority.Boost2.exception;

import org.springframework.http.HttpStatus;

public class ErrorInfo {
    private String message;
    private Boolean error;
    private int code;
    private String url;

    public ErrorInfo(int code, String message, String url, boolean error) {
        super();
        this.code = code;
        this.message = message;
        this.error = error;
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

