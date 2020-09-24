package com.musicallcommunity.musicallback.service;

import com.musicallcommunity.musicallback.exception.AlreadyExistException;
import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.Conversation;

import java.util.List;

public interface ConversationService {

    Conversation getById(Long id);

    List<Conversation> getByUserId(Long userId) throws ResourceNotFoundException;

    List<Conversation> getAll();

    Conversation save(Conversation conversation) throws AlreadyExistException;

    /*Conversation createConversation(User user) throws AlreadyExistException;*/

    void delete(Long id);

    Conversation updateConversation(Conversation conversationToUpdate) throws ResourceNotFoundException;
}
