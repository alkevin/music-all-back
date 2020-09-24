package com.musicallcommunity.musicallback.repository.impl;

import com.musicallcommunity.musicallback.model.Message;
import com.musicallcommunity.musicallback.model.UserFriend;
import com.musicallcommunity.musicallback.repository.UserFriendRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

abstract class UserFriendRepositoryImpl implements UserFriendRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List getByUserIdAndFriendId(Long userId, Long friendId) {

        Query query = entityManager.createNativeQuery("SELECT uf.* FROM user_friend as uf " +

                "WHERE uf.user_id = ? AND uf.friend_id = ?", UserFriend.class);

        query.setParameter(1, userId );
        query.setParameter(2, friendId );

        return query.getResultList();
    }
}
