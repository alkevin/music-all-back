package com.musicallcommunity.musicallback.service;

import com.musicallcommunity.musicallback.exception.AlreadyExistException;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.Conversation;
import com.musicallcommunity.musicallback.model.Message;
import com.musicallcommunity.musicallback.model.User;

import java.util.List;

public interface MessageService {

    List<Message> getAll();

    List<Message> getByConversationId(Long conversationId) throws ResourceNotFoundException;

    Message save(Message message) throws AlreadyExistException;

    Message createMessage(Conversation conversation, User sender, String content) throws AlreadyExistException;

    void createMessageInNewConversation(User me, Long contactId, String content) throws  AlreadyExistException;
    void createMessageInExistingConversation(User me, Long conversationId, Long contactId, String content);

    Message messageUpdateStatus(Message message);

    void delete(Long messageId);
}
