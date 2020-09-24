package com.musicallcommunity.musicallback.repository;

import com.musicallcommunity.musicallback.model.Message;
import com.musicallcommunity.musicallback.model.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFriendRepository extends JpaRepository<UserFriend, Long>  {

    List<UserFriend> getByUserIdAndFriendId(Long userId, Long friendId);

}
