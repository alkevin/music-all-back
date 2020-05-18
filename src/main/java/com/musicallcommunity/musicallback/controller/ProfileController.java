package com.musicallcommunity.musicallback.controller;

import com.musicallcommunity.musicallback.controller.admin.UserController;
import com.musicallcommunity.musicallback.dto.ChangePasswordDto;
import com.musicallcommunity.musicallback.dto.UserDto;
import com.musicallcommunity.musicallback.dto.util.UserUtil;
import com.musicallcommunity.musicallback.exception.InvalidOldPasswordException;
import com.musicallcommunity.musicallback.model.User;
import com.musicallcommunity.musicallback.payload.ApiResponse;
import com.musicallcommunity.musicallback.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/api/user/profile")
public class ProfileController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/me", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ApiResponse<UserDto> getProfile() {
        LOGGER.info("Fetching Profile with user load by contexte {} : ",
                ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        String userMail = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.getByMail(userMail);
        UserDto profile = UserUtil.toUser(user);

        if (user == null) {
            LOGGER.error("Profile with user mail {} is not found.", userMail);
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Profile with user mail " + userMail
                    + " not found.", profile);
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "Profile with user mail " + userMail
                + " fetched successfully.", profile);
    }

    @PutMapping(value = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ApiResponse<UserDto> updateProfile(@Valid @RequestBody UserDto userDto) {
        LOGGER.info("Fetching User with context for Update his profile {}",
                ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        User user = userService.getByMail(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        UserDto userDt = UserUtil.toUser(user);

        if (userService.isAdmin(user.getId(),user.getMail())) {
            return new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "You can not update the admin", userDt);
        } else {
            User userUpdated = userService.updateProfile(user.getId(), userDto);
            userDt = UserUtil.toUser(userUpdated);
        }

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

