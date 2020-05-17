package com.musicallcommunity.musicallback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ForbiddenException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s forbidden %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}