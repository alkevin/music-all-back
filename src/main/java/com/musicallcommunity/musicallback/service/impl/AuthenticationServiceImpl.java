package com.musicallcommunity.musicallback.service.impl;

import com.musicallcommunity.musicallback.payload.SignUpRequest;
import com.musicallcommunity.musicallback.payload.SignInRequest;
import com.musicallcommunity.musicallback.exception.AlreadyExistException;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.AuthProvider;
import com.musicallcommunity.musicallback.model.Role;
import com.musicallcommunity.musicallback.model.RoleName;
import com.musicallcommunity.musicallback.model.User;
import com.musicallcommunity.musicallback.repository.RoleRepository;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service(value = "authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService{

    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenProvider tokenProvider;

    /**
     * Sign in a user into the application, with JWT-enabled authentication
     *
     * @param signInRequest Object that contains authentications
     * @return String Token
     */
    @Override
    public String authenticateJwt(SignInRequest signInRequest) throws ResourceNotFoundException {
        LOGGER.info("New user attempting to sign in");
        if (userRepository.findByMail(signInRequest.getMail()) == null) {
            throw new ResourceNotFoundException("User", "mail", signInRequest.getMail());
        }
        Collection<Role> roles = userRepository.findByMail(signInRequest.getMail()).getRoles();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getMail(), signInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.createToken(authentication, roles);
    }

    @Override
    public User signUp(SignUpRequest signup) throws AlreadyExistException, ResourceNotFoundException {
        if (userRepository.findByMail(signup.getMail()) != null) {
            LOGGER.info("Unable to create. A User with username {} already exists", signup.getMail());
            throw new AlreadyExistException("User", "email", signup.getMail());
        }
        User user = new User(signup);
        Role userRole = roleRepository.findByName(RoleName.USER_ROLE).
                orElseThrow(() -> new ResourceNotFoundException("Role", "name", RoleName.USER_ROLE));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(userRole));
        user.setEnabled(true);
        user.setProvider(AuthProvider.local);
        user.setProviderId("local_ID");
        return userService.save(user);
    }
}
