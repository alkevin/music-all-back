package com.musicallcommunity.musicallback.controller;

import com.musicallcommunity.musicallback.controller.admin.UserController;
import com.musicallcommunity.musicallback.dto.ChangePasswordDto;
import com.musicallcommunity.musicallback.dto.MessageDto;
import com.musicallcommunity.musicallback.dto.ProfileDto;
import com.musicallcommunity.musicallback.dto.UserDto;
import com.musicallcommunity.musicallback.dto.util.MessageUtil;
import com.musicallcommunity.musicallback.dto.util.UserUtil;
import com.musicallcommunity.musicallback.exception.AlreadyExistException;
import com.musicallcommunity.musicallback.exception.InvalidOldPasswordException;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.Conversation;
import com.musicallcommunity.musicallback.model.Message;
import com.musicallcommunity.musicallback.model.Profile;
import com.musicallcommunity.musicallback.model.User;
import com.musicallcommunity.musicallback.payload.ApiResponse;
import com.musicallcommunity.musicallback.payload.LoginResponse;
import com.musicallcommunity.musicallback.payload.MessageRequest;
import com.musicallcommunity.musicallback.payload.SignInRequest;
import com.musicallcommunity.musicallback.service.AuthenticationService;
import com.musicallcommunity.musicallback.service.ProfileService;
import com.musicallcommunity.musicallback.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/api/user/profile")
public class ProfileController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private AuthenticationService authenticationService;
    private ProfileService profileService;

    @Autowired
    public ProfileController(UserService userService, AuthenticationService authenticationService
                            , ProfileService profileService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.profileService = profileService;
    }

    @GetMapping(value = "/me", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<UserDto> getProfile() {
        LOGGER.info("Fetching Profile with user load by contexte {} : ",
                ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        String userMail = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        UserDto userDto = userService.fetchUserAfterAuth(userMail);


        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @PutMapping(value = "/update-user", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ApiResponse<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {
        LOGGER.info("Fetching User with context for Update his profile {}",
                ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        User user = userService.getByMail(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        UserDto userDt = UserUtil.toUser(user);

        if (userService.isAdmin(user.getId(),user.getMail())) {
            return new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "You can not update the admin", userDt);
        } else {
            User userUpdated = userService.updateUser(user.getId(), userDto);
            userDt = UserUtil.toUser(userUpdated);
        }

        return new ApiResponse<>(HttpStatus.OK.value(), "User updated with success", userDt);
    }

    @PutMapping(value = "/update-profile", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ApiResponse<UserDto> updateProfile(@Valid @RequestBody ProfileDto profileDto) {
        LOGGER.info("Fetching User with context for Update his profile {}",
                ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        User user = userService.getByMail(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        UserDto userDt = new UserDto();
        if (userService.isAdmin(user.getId(),user.getMail())) {
            return new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "You can not update the admin", userDt);
        } else {
            profileService.updateProfile(user.getProfile(), profileDto);
        }

        user = userService.getByMail(user.getMail());
        userDt = UserUtil.toUser(user);

        return new ApiResponse<>(HttpStatus.OK.value(), "User updated with success", userDt);
    }

    @DeleteMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ApiResponse<UserDto> delete() {
        LOGGER.info("Fetching & Deleting User Profile load by contexte {} : ",
                ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        String userMail = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.getByMail(userMail);
        UserDto userDt = UserUtil.toUser(user);

        if (user == null) {
            LOGGER.error("Unable to delete. User with mail {} not found.", userMail);
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Unable to delete. User with mail " + userMail + " not found.", null);
        }
        if (userService.isAdmin(user.getId(),user.getMail())) {
            return new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "You can not delete the admin", userDt);
        }
        userService.delete(user.getId());
        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
    }

    @PostMapping(value= "/update-password", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ApiResponse<UserDto> changeUserPassword(@RequestBody ChangePasswordDto passwordDto){
        LOGGER.info("Fetching Profile with user load by contexte {} : ", ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        User user = userService.getByMail(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        UserDto userDt = UserUtil.toUser(userService.getByMail(user.getMail()));

        if(!userService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
            throw new InvalidOldPasswordException("password send", "old password");
        }
        if (userService.isAdmin(user.getId(),user.getMail())) {
            return new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "You can not update password of the admin", userDt);
        }
        else {
            userService.changeUserPassword(user, passwordDto.getNewPassword());
            userDt = UserUtil.toUser(userService.getByMail(user.getMail()));
        }

        return new ApiResponse<>(HttpStatus.OK.value(), "User password change successfully", userDt);
    }
}

