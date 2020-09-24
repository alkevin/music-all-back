package com.musicallcommunity.musicallback.dto.util;

import com.musicallcommunity.musicallback.dto.FriendDto;
import com.musicallcommunity.musicallback.dto.UserDto;
import com.musicallcommunity.musicallback.model.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FriendUtil {

    public static FriendDto toFriend(final com.musicallcommunity.musicallback.model.User user) {
        if(user == null) {
            return null;
        }

        return FriendDto.FriendDtoBuilder.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .connected(user.getConnected())
                .build();
    }

    public static List<FriendDto> toFriends(final Collection<com.musicallcommunity.musicallback.model.User> users) {
        final List<FriendDto> listFriends = new ArrayList<>();
        if(listFriends != null) {
            for(final com.musicallcommunity.musicallback.model.User user : users) {
                listFriends.add(toFriend(user));
            }
        }
        return listFriends;
    }
}
