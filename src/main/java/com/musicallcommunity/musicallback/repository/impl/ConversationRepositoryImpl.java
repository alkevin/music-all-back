package com.musicallcommunity.musicallback.repository.impl;

import com.musicallcommunity.musicallback.model.Conversation;
import com.musicallcommunity.musicallback.repository.ConversationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


abstract class ConversationRepositoryImpl implements ConversationRepository {

    @PersistenceContext

    EntityManager entityManager;

    @Override
    public List getByUserId(Long userId) {

        Query query = entityManager.createNativeQuery("SELECT c.* FROM conversation as c " +

                "WHERE c.user_id = ?", Conversation.class);

        query.setParameter(1, userId );

        return query.getResultList();
    }

    /*@Override
    public List getByIdAndSenderId(Long id, Long sender_id) {

        Query query = entityManager.createNativeQuery(
                "SELECT c.* FROM conversation as c " + "WHERE c.user_id IN " +
                        "(SELECT m.sender_id FROM ? as m " + "WHERE m.sender_id = ?) " +
                        "AND c.id = ?"
                , Conversation.class);
        query.setParameter(1, Message.class);
        query.setParameter(1, sender_id );
        query.setParameter(3, id );

        return query.getResultList();
    }*/

}
