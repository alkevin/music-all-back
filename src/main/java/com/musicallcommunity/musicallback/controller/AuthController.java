package com.musicallcommunity.musicallback.controller;

import com.musicallcommunity.musicallback.payload.*;
import com.musicallcommunity.musicallback.dto.util.UserUtil;
import com.musicallcommunity.musicallback.dto.UserDto;
import com.musicallcommunity.musicallback.exception.AlreadyExistException;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.User;
import com.musicallcommunity.musicallback.service.AuthenticationService;
import com.musicallcommunity.musicallback.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

@RestController
@Validated
@RequestMapping("/api/auth")
public class AuthController {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private AuthenticationService authenticationService;
    private UserService userService;
    private MessageSource messages;

    @Autowired
    public AuthController(AuthenticationService authenticationService, UserService userService, MessageSource messages) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.messages = messages;
    }

    @PostMapping(value = "/signin", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ApiResponse<LoginResponse> createJwtAuth(final HttpServletRequest request, @RequestBody @Valid SignInRequest signInRequest) throws ResourceNotFoundException, AlreadyExistException {
        Locale locale = request.getLocale();
        String token = authenticationService.authenticateJwt(signInRequest);
        LOGGER.info("Generate token to authenticate");
        UserDto userDto = userService.fetchUserAfterAuth(signInRequest.getMail());
        userDto.setConnected(true);
        LoginResponse response = new LoginResponse(userDto,token);
        return new ApiResponse<>(HttpStatus.OK.value(), messages.getMessage("token.genSuc", null, locale), response);
    }

    @PostMapping(value = "/signup", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ApiResponse<UserDto> signup(final HttpServletRequest request, @RequestBody @Valid SignUpRequest signup) throws AlreadyExistException, ResourceNotFoundException {
        Locale locale = request.getLocale();
        LOGGER.info("Registering user account with information : {}", signup);
        User user = authenticationService.signUp(signup);
        return new ApiResponse<>(HttpStatus.OK.value(), messages.getMessage("label.successRegister.title", null, locale), UserUtil.toUser(user));
    }
}
