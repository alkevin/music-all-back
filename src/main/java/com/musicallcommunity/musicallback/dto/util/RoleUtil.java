package com.musicallcommunity.musicallback.dto.util;

import com.musicallcommunity.musicallback.dto.RoleDto;
import com.musicallcommunity.musicallback.model.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RoleUtil {

    public static RoleDto toRole(final com.musicallcommunity.musicallback.model.Role role) {
        if(role == null) {
            return null;
        }

        return RoleDto.RoleDtoBuilder.builder().privileges(PrivilegeUtil.toPrivileges(role.getPrivileges()))
                .name(role.getName().name()).authority(role.getAuthority()).build();
    }

    public static List<RoleDto> toRoles(final Collection<Role> roles) {
        final List<RoleDto> listRoles = new ArrayList<>();
        if(roles != null) {
            for(final com.musicallcommunity.musicallback.model.Role role : roles) {
                listRoles.add(toRole(role));
            }
        }
        return listRoles;
    }
}