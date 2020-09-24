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
import com.musicallcommunity.musicallback.payload.LoginResponse;
import com.musicallcommunity.musicallback.payload.MessageRequest;
import com.musicallcommunity.musicallback.payload.SignUpRequest;
import com.musicallcommunity.musicallback.service.ConversationService;
import com.musicallcommunity.musicallback.service.MessageService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/api/user/conversation")
public class MessageController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private ConversationService conversationService;
    private MessageService messageService;

    @Autowired
    public MessageController(UserService userService
                           , ConversationService conversationService
                           , MessageService messageService) {
        this.userService = userService;
        this.conversationService = conversationService;
        this.messageService = messageService;
    }

    @GetMapping(value = "/message", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List<MessageDto>> getMessages(@RequestParam("conversationId") final Long conversationId) {
        LOGGER.info("Fetching message for conversation with id {} : ", conversationId);

        List<Message> messages = messageService.getByConversationId(conversationId);
        List<MessageDto> messagesDto = MessageUtil.toMessages(messages);

        return new ResponseEntity<>(messagesDto, HttpStatus.OK);
    }

    @PostMapping(value = "/message", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<MessageDto> postMessage(@RequestParam("conversationId") final Long conversationId, @RequestBody MessageRequest messageRequest) throws AlreadyExistException, ResourceNotFoundException {
        LOGGER.info("Saving message for conversation_id : " + conversationId);

        Conversation conversation = conversationService.getById(conversationId);
        User user = userService.getByMail(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());

        Message message = messageService.createMessage(conversation, user, messageRequest.getContent());

        MessageDto messageDto = MessageUtil.toMessage(message);

        return new ResponseEntity<>(messageDto,HttpStatus.OK);
    }

    @PostMapping(value = "/new", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<UserDto> postMessageInNewConversation(@RequestParam("userIdToContact") final Long userIdToContact, @RequestBody MessageRequest messageRequest) throws AlreadyExistException, ResourceNotFoundException {
        LOGGER.info("Creating new conversation and create message for userIdToContact : " + userIdToContact);

        String meMail = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User me = userService.getByMail(meMail);

        messageService.createMessageInNewConversation(me, userIdToContact, messageRequest.getContent());

        me = userService.getByMail(meMail);
        UserDto meDto = UserUtil.toUser(me);

        return new ResponseEntity<>(meDto,HttpStatus.OK);
    }

    @PostMapping(value = "/existing", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<UserDto> postMessageInExistingConversation(@RequestParam("userIdToContact") final Long userIdToContact, @RequestParam("conversationId") final Long conversationId, @RequestBody MessageRequest messageRequest) throws AlreadyExistException, ResourceNotFoundException {
        LOGGER.info("Creating new conversation and create message for userIdToContact : " + userIdToContact);

        String meMail = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User me = userService.getByMail(meMail);

        messageService.createMessageInExistingConversation(me, conversationId, userIdToContact, messageRequest.getContent());

        me = userService.getByMail(meMail);
        UserDto meDto = UserUtil.toUser(me);

        return new ResponseEntity<>(meDto,HttpStatus.OK);
    }
}
