package com.musicallcommunity.musicallback.controller;

import com.musicallcommunity.musicallback.payload.*;
import com.musicallcommunity.musicallback.exception.AlreadyExistException;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/auth")
public class AuthController {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private AuthenticationService authenticationService;
    // private MessageSource messages;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/signin", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<LoginResponse> createJwtAuth(final HttpServletRequest request, @RequestBody @Valid SignInRequest signInRequest) throws ResourceNotFoundException, AlreadyExistException {
        //Locale locale = request.getLocale();
        LOGGER.info("Generate token to authenticate");
        LoginResponse response = authenticationService.authenticateJwt(signInRequest);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping(value = "/signup", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<LoginResponse> signup(final HttpServletRequest request, @RequestBody @Valid SignUpRequest signup) throws AlreadyExistException, ResourceNotFoundException {
        //Locale locale = request.getLocale();
        LOGGER.info("Registering user account with information : {}", signup);
        LoginResponse response = authenticationService.signUp(signup);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(value= "/logout")
    public ResponseEntity logout(final HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Logout user account");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    /*@PostMapping(value = "/signup", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ApiResponse<UserDto> signup(final HttpServletRequest request, @RequestBody @Valid SignUpRequest signup) throws AlreadyExistException, ResourceNotFoundException {
        Locale locale = request.getLocale();
        LOGGER.info("Registering user account with information : {}", signup);
        User user = authenticationService.signUp(signup);
        return new ApiResponse<>(HttpStatus.OK.value(), messages.getMessage("label.successRegister.title", null, locale), UserUtil.toUser(user));
    }*/
}
