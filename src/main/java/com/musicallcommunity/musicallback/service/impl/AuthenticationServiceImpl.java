package com.musicallcommunity.musicallback.service.impl;

import com.musicallcommunity.musicallback.dto.UserDto;
import com.musicallcommunity.musicallback.model.Privilege;
import com.musicallcommunity.musicallback.payload.LoginResponse;
import com.musicallcommunity.musicallback.payload.SignUpRequest;
import com.musicallcommunity.musicallback.payload.SignInRequest;
import com.musicallcommunity.musicallback.exception.AlreadyExistException;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.Role;
import com.musicallcommunity.musicallback.repository.UserRepository;
import com.musicallcommunity.musicallback.security.TokenProvider;
import com.musicallcommunity.musicallback.service.AuthenticationService;
import com.musicallcommunity.musicallback.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service(value = "authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService{

    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenProvider tokenProvider;

    /**
     * Sign in a user into the application, with JWT-enabled authentication
     *
     * @param signInRequest Object that contains authentications
     * @return String Token
     */
    @Override
    public LoginResponse authenticateJwt(SignInRequest signInRequest) throws ResourceNotFoundException {
        LOGGER.info("New user attempting to sign in");
        if (userRepository.findByMail(signInRequest.getMail()) == null) {
            throw new ResourceNotFoundException("User", "mail", signInRequest.getMail());
        }
        Collection<Role> roles = userRepository.findByMail(signInRequest.getMail()).getRoles();

        /*List privileges = userRepository.findByMail(signInRequest.getMail()).getRoles().stream().map(Role::getPrivileges).filter(Objects::nonNull)
                .collect(Collectors.toList());
        LOGGER.info("test roles : " + privileges);*/

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getMail(), signInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication, roles);
        UserDto userDto = userService.fetchUserAfterAuth(signInRequest.getMail());
        LoginResponse response = new LoginResponse(userDto,token);
        return response;
    }

    @Override
    public LoginResponse signUp(SignUpRequest signup) throws AlreadyExistException, ResourceNotFoundException {
        if (userRepository.findByMail(signup.getMail()) != null) {
            LOGGER.info("Unable to create. A User with username {} already exists", signup.getMail());
            throw new AlreadyExistException("User", "email", signup.getMail());
        }
        userService.createUser(signup);
        SignInRequest signin = new SignInRequest(signup.getMail(), signup.getPassword());
        return authenticateJwt(signin);
    }
}
