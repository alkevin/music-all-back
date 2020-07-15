package com.musicallcommunity.musicallback.dto.util;

import com.musicallcommunity.musicallback.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {

    public static UserDto toUser(final com.musicallcommunity.musicallback.model.User user) {
        if(user == null) {
            return null;
        }

        return UserDto.UserDtoBuilder.builder().firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mail((user.getMail()))
                .roles(RoleUtil.toRoles(user.getRoles()))
                .provider((user.getProvider()))
                .providerId(user.getProviderId())
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
