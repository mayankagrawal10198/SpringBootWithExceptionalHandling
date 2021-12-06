package com.example.userList.exceptions;

import org.springframework.stereotype.Component;

@Component
public class NoUserFoundExceptions extends RuntimeException{
    private String statusCode;
    private String statusMessage;

    public NoUserFoundExceptions(String statusCode, String statusMessage) {
        super();
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public NoUserFoundExceptions() {

    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
