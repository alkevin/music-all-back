package com.musicallcommunity.musicallback.service.impl;

import com.musicallcommunity.musicallback.dto.UserDto;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.*;
import com.musicallcommunity.musicallback.payload.SignUpRequest;
import com.musicallcommunity.musicallback.repository.ConversationRepository;
import com.musicallcommunity.musicallback.repository.MessageRepository;
import com.musicallcommunity.musicallback.service.MessageService;
import com.musicallcommunity.musicallback.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service(value = "messageService")
public class MessageServiceImpl implements MessageService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserService userService;
    private MessageRepository messageRepository;
    private ConversationRepository conversationRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ConversationRepository conversationRepository
                             , UserService userService) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.userService = userService;
    }

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> getByConversationId(Long conversationId) throws ResourceNotFoundException {
        LOGGER.info("Fetching messages for conversation : " + conversationId);
        return messageRepository.getByConversationId(conversationId);
    }

    @Override
    public void createMessageInNewConversation(User me, Long contactId, String content) {

        User contact = userService.getById(contactId);

        Conversation conversationMe = new Conversation();
        conversationMe.setUserId(me.getId());
        conversationMe.setUser(me);
        conversationMe = conversationRepository.save(conversationMe);
        Message message = createMessage(conversationMe, me, content);
        List<Message> listMe = new ArrayList<>();
        listMe.add(message);
        conversationMe.setMessages(listMe);
        conversationRepository.save(conversationMe);

        Conversation conversationContact = new Conversation();
        conversationContact.setUserId(contact.getId());
        conversationContact.setUser(contact);
        conversationContact = conversationRepository.save(conversationContact);
        message = createMessage(conversationContact, me, content);
        List<Message> listContact = new ArrayList<>();
        listContact.add(message);
        conversationMe.setMessages(listContact);
        conversationRepository.save(conversationContact);
    }

    @Override
    public void createMessageInExistingConversation(User me, Long conversationId, Long contactId, String content) {

        User contact = userService.getById(contactId);

        Conversation conversationMe = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ResourceNotFoundException("Conversation", "id", conversationId));
        Message message = createMessage(conversationMe, me, content);
        List<Message> listMessage = new ArrayList<>();
        listMessage.addAll(conversationMe.getMessages());
        listMessage.add(message);
        conversationRepository.save(conversationMe);

        List<Conversation> conversationsContact = conversationRepository.getByUserId(contactId);

        for (Conversation conversation : conversationsContact) {
            for (Message messageConv : conversation.getMessages()) {
                if(messageConv.getSenderId().equals(me.getId())) {
                    Conversation conversationContact = conversationRepository.findById(conversation.getId())
                            .orElseThrow(() -> new ResourceNotFoundException("ConversationContact", "id", conversation.getId()));
                    message = createMessage(conversationContact, contact, content);
                    List<Message> listMessageContact = new ArrayList<>();
                    listMessageContact.addAll(conversationMe.getMessages());
                    listMessageContact.add(message);
                    conversationRepository.save(conversationContact);
                }
            }
        }
    }


    @Override
    public Message createMessage(Conversation conversation, User sender, String content) {
        LOGGER.info("Creating message conversation {} and sender_id {} ", conversation.getId(), sender.getId());
        Message message = new Message();
        message.setConversationId(conversation.getId());
        message.setSenderId(sender.getId());
        message.setConversation(conversation);
        message.setUser(sender);
        message.setContent(content);
        return save(message);
    }

    @Override
    public Message save(Message message){
        LOGGER.info("Saving message : " + message.toString());
        return messageRepository.save(message);
    }

    @Override
    public Message messageUpdateStatus(Message message) {
        LOGGER.info("Update status message : " + message.toString());

        String status = message.getStatus().name();

        if(status.equals("CREATED")) {
            LOGGER.info("Set status message to : " + MessageStatus.SEND.toString());
            message.setStatus(MessageStatus.SEND);
        }
        else {
            LOGGER.info("Set status message to : " + MessageStatus.READ.toString());
            message.setStatus(MessageStatus.READ);
        }

        return messageRepository.save(message);
    }

    @Override
    public void delete(Long messageId) {
        messageRepository.deleteById(messageId);
    }
}
