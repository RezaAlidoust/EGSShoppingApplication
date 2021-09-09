package com.egs.shoppingapplication.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;

    public ErrorMessage(HttpStatus statusCode, String message, String description) {
        this.statusCode = statusCode.value();
        this.timestamp = new Date();
        this.message = message;
        this.description = description;
    }
}
