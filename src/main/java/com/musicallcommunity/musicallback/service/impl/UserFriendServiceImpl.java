package com.musicallcommunity.musicallback.service.impl;

import com.musicallcommunity.musicallback.exception.ResourceNotFoundException;
import com.musicallcommunity.musicallback.model.*;
import com.musicallcommunity.musicallback.repository.UserFriendRepository;
import com.musicallcommunity.musicallback.repository.UserRepository;
import com.musicallcommunity.musicallback.service.UserFriendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value="userFriendService")
public class UserFriendServiceImpl implements UserFriendService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    private UserFriendRepository userFriendRepository;

    @Autowired
    public UserFriendServiceImpl(UserRepository userRepository, UserFriendRepository userFriendRepository) {
        this.userRepository = userRepository;
        this.userFriendRepository = userFriendRepository;
    }

    @Override
    public void save(UserFriend userFriend){
        LOGGER.info("Saving userFriend : " + userFriend.toString());
        userFriendRepository.save(userFriend);
    }

    @Override
    public void createFriendRequest(User user, Long friendId) {
        LOGGER.info("Creating friendRequest for users {} & {}", user.getId(), friendId);
        User userRequested = userRepository.findById(friendId).orElseThrow(() -> new ResourceNotFoundException("User", "id", friendId));
        UserFriend userFriend = new UserFriend();
        UserFriendKey key = new UserFriendKey(user.getId(),userRequested.getId());
        userFriend.setId(key);
        userFriend.setUser(user);
        userFriend.setFriend(userRequested);
        save(userFriend);
        key = new UserFriendKey(userRequested.getId(), user.getId());
        userFriend.setId(key);
        userFriend.setUser(userRequested);
        userFriend.setFriend(user);
        save(userFriend);
    }

    @Override
    public void accepteFriendRequest(User user, Long friendId) {
        LOGGER.info("Accepting friendRequest for users id {} & {}", user.getId(), friendId);

        List<UserFriend> userFriends = userFriendRepository.getByUserIdAndFriendId(user.getId(),friendId);
        UserFriend userFriend = new UserFriend();

        for (UserFriend friend : userFriends) {
            userFriend = friend;
        }

        userFriend.setStatus(FriendStatus.ACCEPTED);
        save(userFriend);
    }

    @Override
    public void blockFriendRequest(User user, Long friendId) {
        LOGGER.info("Accepting friendRequest for users id {} & {}", user.getId(), friendId);

        List<UserFriend> userFriends = userFriendRepository.getByUserIdAndFriendId(user.getId(),friendId);
        UserFriend userFriend = new UserFriend();

        for (UserFriend friend : userFriends) {
            userFriend = friend;
        }

        userFriend.setStatus(FriendStatus.BLOCKED);
        save(userFriend);
    }
}
