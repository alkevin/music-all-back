package com.musicallcommunity.musicallback.dto.util;

import com.musicallcommunity.musicallback.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {

    public static UserDto toUser(final com.musicallcommunity.musicallback.model.User user) {
        if(user == null) {
            return null;
        }

        return UserDto.UserDtoBuilder.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mail((user.getMail()))
                .profile(ProfileUtil.toProfile(user.getProfile()))
                .roles(RoleUtil.toRoles(user.getRoles()))
                .friends(UserFriendUtil.toUserFriends(user.getUserFriends()))
                .conversations(ConversationUtil.toConversations(user.getConversations()))
                .connected(user.getConnected())
                .build();
    }

    public static List<UserDto> toUsers(final List<com.musicallcommunity.musicallback.model.User> users) {
        final List<UserDto> listUsers = new ArrayList<>();
        if(listUsers != null) {
            for(final com.musicallcommunity.musicallback.model.User user : users) {
                listUsers.add(toUser(user));
            }
        }
        return listUsers;
    }
}
