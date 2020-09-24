package com.musicallcommunity.musicallback.controller.admin;

import com.musicallcommunity.musicallback.dto.UserDto;
import com.musicallcommunity.musicallback.dto.util.UserUtil;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.User;
import com.musicallcommunity.musicallback.payload.ApiResponse;
import com.musicallcommunity.musicallback.service.UserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping(value = "/api/admin/users")
@Api(description = "authentication api")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ApiResponse<List<UserDto>> getAllUsers() {
        LOGGER.info("Fetching all users");
        List<User> userList ;
        if (userService.getAll().isEmpty()) {
            LOGGER.error("Unable to fetch an empty list");
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "No users on the DB.", UserUtil.toUsers(userService.getAll()));
        }
        userList = userService.getAll().stream().filter(user -> !Objects.deepEquals(user,userService.getById(1L))).collect(Collectors.toList());

        List<UserDto> usersDto = UserUtil.toUsers(userList);

        return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.", usersDto);
    }

    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ApiResponse<User> getUser(@PathVariable final Long userId) {
        LOGGER.info("Fetching User with id {}", userId);
        User user = userService.getById(userId);
        if (user == null) {
            LOGGER.error("User with id {} is not found.", userId);
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User with id " + userId
                    + " not found.", userService.getById(userId));
        }
        return new ApiResponse<>(HttpStatus.OK.value(), "User with id " + userId
                + "fetched successfully.", userService.getById(userId));
    }

    @PutMapping(value = "/{userId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ApiResponse<User> updateUser(@PathVariable final Long userId, @RequestBody @Valid UserDto user) throws ResourceNotFoundException {
        LOGGER.info("Updating Client with id {}", userId);
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.", userService.updateUser(userId, user));
    }

    @DeleteMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ApiResponse<User> delete(@PathVariable final Long userId) {
        LOGGER.info("Fetching & Deleting User with id {}", userId);
        User user = userService.getById(userId);
        if (user == null) {
            LOGGER.error("Unable to delete. User with id {} not found.", userId);
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Unable to delete. User with id " + userId + " not found.", userService.getById(userId));
        }
        if (userService.isAdmin(userId,user.getMail())) {
            return new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "You can not delete the admin", userService.getById(userId));
        }
        userService.delete(userId);
        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
    }
}