package com.musicallcommunity.musicallback.controller;

import com.musicallcommunity.musicallback.controller.admin.UserController;
import com.musicallcommunity.musicallback.dto.MessageDto;
import com.musicallcommunity.musicallback.dto.UserDto;
import com.musicallcommunity.musicallback.dto.util.MessageUtil;
import com.musicallcommunity.musicallback.dto.util.UserUtil;
import com.musicallcommunity.musicallback.exception.AlreadyExistException;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.Conversation;
import com.musicallcommunity.musicallback.model.Message;
import com.musicallcommunity.musicallback.model.User;
import com.musicallcommunity.musicallback.model.UserFriend;
import com.musicallcommunity.musicallback.payload.MessageRequest;
import com.musicallcommunity.musicallback.service.UserFriendService;
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

@RestController
@Validated
@RequestMapping(value = "/api/user/friend")
public class UserFriendController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private UserFriendService userFriendService;

    @Autowired
    public UserFriendController(UserService userService, UserFriendService userFriendService) {
        this.userService = userService;
        this.userFriendService = userFriendService;
    }

    @PostMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<UserDto> createFriendRequest(@RequestParam("friendId") final Long friendId) throws ResourceNotFoundException {
        LOGGER.info("Creating friend request for user_id {} ",
                ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        User userOwner = loadUserByContext();

        userFriendService.createFriendRequest(userOwner, friendId);

        UserDto userOwnerDto = UserUtil.toUser(userService.getByMail(userOwner.getMail()));

        return new ResponseEntity<>(userOwnerDto, HttpStatus.OK);
    }

    @PostMapping(value = "/accepte", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<UserDto> accepteFriendRequest(@RequestParam("friendId") final Long friendId) throws ResourceNotFoundException {
        LOGGER.info("Accepte friend request for user_id {} ",
                ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        User userOwner = loadUserByContext();

        userFriendService.accepteFriendRequest(userOwner, friendId);

        UserDto userOwnerDto = UserUtil.toUser(userService.getByMail(userOwner.getMail()));

        return new ResponseEntity<>(userOwnerDto, HttpStatus.OK);
    }

    @PostMapping(value = "/block", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<UserDto> blockFriendRequest(@RequestParam("friendId") final Long friendId) throws ResourceNotFoundException {
        LOGGER.info("Block friend request for user_id {} ",
                ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        User userOwner = loadUserByContext();

        userFriendService.blockFriendRequest(userOwner, friendId);

        UserDto userOwnerDto = UserUtil.toUser(userService.getByMail(userOwner.getMail()));

        return new ResponseEntity<>(userOwnerDto, HttpStatus.OK);
    }

    private User loadUserByContext() {
        String userMail = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User userOwner = userService.getByMail(userMail);
        return userOwner;
    }

}
