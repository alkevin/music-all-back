package com.musicallcommunity.musicallback.service;

import com.musicallcommunity.musicallback.payload.LoginResponse;
import com.musicallcommunity.musicallback.payload.SignUpRequest;
import com.musicallcommunity.musicallback.exception.AlreadyExistException;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.payload.SignInRequest;

public interface AuthenticationService {

    LoginResponse authenticateJwt(SignInRequest signInRequest) throws AlreadyExistException, ResourceNotFoundException;
    LoginResponse signUp(SignUpRequest signup) throws AlreadyExistException;
}
