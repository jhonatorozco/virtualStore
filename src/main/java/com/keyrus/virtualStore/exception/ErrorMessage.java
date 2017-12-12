package com.keyrus.virtualStore.exception;

import org.springframework.http.HttpStatus;

/**
 * The class represents the error message.
 * @author Jhonatan Orozco
 * @version 1
 */
public class ErrorMessage {

    private HttpStatus status;
    private String message;


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
