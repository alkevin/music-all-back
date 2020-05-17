package com.musicallcommunity.musicallback.service;

import com.musicallcommunity.musicallback.payload.SignUpRequest;
import com.musicallcommunity.musicallback.exception.AlreadyExistException;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.User;
import com.musicallcommunity.musicallback.payload.SignInRequest;

public interface AuthenticationService {

    String authenticateJwt(SignInRequest signInRequest) throws AlreadyExistException, ResourceNotFoundException;
    User signUp(SignUpRequest signup) throws AlreadyExistException;
}
