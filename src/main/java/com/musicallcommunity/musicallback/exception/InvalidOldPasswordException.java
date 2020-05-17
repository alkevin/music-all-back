package com.musicallcommunity.musicallback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOldPasswordException extends RuntimeException {
    private String resourceName;
    private String fieldName;

    public InvalidOldPasswordException(String resourceName, String fieldName) {
        super(String.format("%s doesn't matche %s", resourceName, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }

}
