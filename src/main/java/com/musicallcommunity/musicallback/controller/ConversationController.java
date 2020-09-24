package com.musicallcommunity.musicallback.controller;

import com.musicallcommunity.musicallback.controller.admin.UserController;
import com.musicallcommunity.musicallback.dto.ConversationDto;
import com.musicallcommunity.musicallback.dto.MessageDto;
import com.musicallcommunity.musicallback.dto.UserDto;
import com.musicallcommunity.musicallback.dto.util.ConversationUtil;
import com.musicallcommunity.musicallback.dto.util.MessageUtil;
import com.musicallcommunity.musicallback.dto.util.UserUtil;
import com.musicallcommunity.musicallback.exception.AlreadyExistException;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.Conversation;
import com.musicallcommunity.musicallback.model.Message;
import com.musicallcommunity.musicallback.model.User;
import com.musicallcommunity.musicallback.payload.MessageRequest;
import com.musicallcommunity.musicallback.service.AuthenticationService;
import com.musicallcommunity.musicallback.service.ConversationService;
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

import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/api/user/conversation")
public class ConversationController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private ConversationService conversationService;

    @Autowired
    public ConversationController(UserService userService, ConversationService conversationService) {
        this.userService = userService;
        this.conversationService = conversationService;
    }

    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<ConversationDto>> getConversations() {
        LOGGER.info("Fetching conversations for user load by contexte {} : ",
                ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        String userMail = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.getByMail(userMail);
        List<Conversation> conversations = conversationService.getByUserId(user.getId());

        List<ConversationDto> conversationsDto = ConversationUtil.toConversations(conversations);

        // List<Conversation> conversations = conversationService.getAll();


        return new ResponseEntity<>(conversationsDto, HttpStatus.OK);
    }
}
