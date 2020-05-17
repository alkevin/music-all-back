package com.musicallcommunity.musicallback.payload;

public class ApiResponse<T> {
    private int status;
    private String message;
    private String error;
    private T result;

    public ApiResponse(int status, final String message, T result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public ApiResponse(int status, final String message, final String error){
        super();
        this.status = status;
        this.message = message;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}

