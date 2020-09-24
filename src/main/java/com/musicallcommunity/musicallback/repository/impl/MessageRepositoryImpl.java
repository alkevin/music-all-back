package com.musicallcommunity.musicallback.repository.impl;

import com.musicallcommunity.musicallback.model.Message;
import com.musicallcommunity.musicallback.repository.MessageRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

abstract class MessageRepositoryImpl implements MessageRepository {

    @PersistenceContext

    EntityManager entityManager;

    @Override
    public List getByConversationId(Long conversationId) {

        Query query = entityManager.createNativeQuery("SELECT m.* FROM message as m " +

                "WHERE m.conversation_id = ?", Message.class);

        query.setParameter(1, conversationId );

        return query.getResultList();
    }
}
