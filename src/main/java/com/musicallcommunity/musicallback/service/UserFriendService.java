package com.musicallcommunity.musicallback.service;

import com.musicallcommunity.musicallback.exception.AlreadyExistException;
import com.musicallcommunity.musicallback.model.User;
import com.musicallcommunity.musicallback.model.UserFriend;

public interface UserFriendService {

    void createFriendRequest(User user, Long friendId);

    void save(UserFriend userFriend) throws AlreadyExistException;

    void accepteFriendRequest(User user, Long friendId);

    void blockFriendRequest(User user, Long friendId);
}
