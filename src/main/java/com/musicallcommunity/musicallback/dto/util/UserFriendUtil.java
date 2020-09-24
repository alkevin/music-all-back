package com.musicallcommunity.musicallback.dto.util;

import com.musicallcommunity.musicallback.dto.UserFriendDto;
import com.musicallcommunity.musicallback.model.UserFriend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserFriendUtil {

    public static UserFriendDto toUserFriend(final com.musicallcommunity.musicallback.model.UserFriend userFriend) {
        if(userFriend == null) {
            return null;
        }

        return UserFriendDto.UserFriendDtoBuilder.builder()
                .userId(userFriend.getUser().getId())
                .friendId(userFriend.getFriend().getId())
                .status(userFriend.getStatus())
                .friend(FriendUtil.toFriend(userFriend.getFriend()))
                .build();
    }

    public static List<UserFriendDto> toUserFriends(final Collection<UserFriend> userFriends) {
        final List<UserFriendDto> listUserFriends = new ArrayList<>();
        if(listUserFriends != null) {
            for(final com.musicallcommunity.musicallback.model.UserFriend userFriend : userFriends) {
                listUserFriends.add(toUserFriend(userFriend));
            }
        }
        return listUserFriends;
    }
}
