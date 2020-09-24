package com.musicallcommunity.musicallback.payload;

import javax.validation.constraints.NotNull;

public class InstrumentRequest {

    @NotNull
    private String name;

    protected InstrumentRequest() {
    }

    public InstrumentRequest(@NotNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
