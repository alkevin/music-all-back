package com.musicallcommunity.musicallback.service.impl;

import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.Conversation;
import com.musicallcommunity.musicallback.repository.ConversationRepository;
import com.musicallcommunity.musicallback.service.ConversationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "conversationService")
public class ConversationServiceImpl implements ConversationService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private ConversationRepository conversationRepository;

    @Autowired
    public ConversationServiceImpl(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Override
    public List<Conversation> getAll() {
        return conversationRepository.findAll();
    }

    @Override
    public Conversation getById(Long id) {
        return conversationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Conversation", "id", id));
    }

    @Override
    public void delete(Long id) {
        conversationRepository.deleteById(id);
    }

    @Override
    public Conversation save(Conversation conversation){
        LOGGER.info("Saving conversation : " + conversation.toString());
        return conversationRepository.save(conversation);
    }

    /*@Override
    public Conversation createConversation(User user) {
        LOGGER.info("Creating conversation for user : " + user);
        Conversation conversation = new Conversation(user);

        return save(conversation);
    }*/

    @Override
    public Conversation updateConversation(Conversation conversationToUpdate) throws ResourceNotFoundException {

        if (getById(conversationToUpdate.getId()) == null){
            throw new ResourceNotFoundException("Conversation", "id", conversationToUpdate.getId());
        }
        conversationToUpdate.setModificationDate(new Date());
        return conversationRepository.save(conversationToUpdate);
    }

    @Override
    public List<Conversation> getByUserId(Long userId) throws ResourceNotFoundException {
        LOGGER.info("Fetching conversations for user : " + userId);
        return conversationRepository.getByUserId(userId);
    }
}
