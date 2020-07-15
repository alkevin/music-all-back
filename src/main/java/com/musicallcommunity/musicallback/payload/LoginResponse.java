package com.musicallcommunity.musicallback.payload;

import com.musicallcommunity.musicallback.dto.UserDto;

public class LoginResponse {

    private final UserDto user;

    private final String token;

    public LoginResponse(UserDto user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public UserDto getUser() {
        return this.user;
    }
}
