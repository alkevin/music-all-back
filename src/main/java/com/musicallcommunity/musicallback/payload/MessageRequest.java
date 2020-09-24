package com.musicallcommunity.musicallback.payload;

import javax.validation.constraints.NotNull;

public class MessageRequest {

    @NotNull
    private String content;

    protected MessageRequest() {
    }

    public MessageRequest(@NotNull String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
