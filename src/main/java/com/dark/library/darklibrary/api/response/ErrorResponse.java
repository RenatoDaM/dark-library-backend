package com.dark.library.darklibrary.api.response;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ErrorResponse {
    LocalDateTime timestamp;
    int code;
    String message;

    public ErrorResponse() {

    }

    public ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
        timestamp = LocalDateTime.now();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
