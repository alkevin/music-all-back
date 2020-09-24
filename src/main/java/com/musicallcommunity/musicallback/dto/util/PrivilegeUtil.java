package com.musicallcommunity.musicallback.dto.util;

import com.musicallcommunity.musicallback.dto.PrivilegeDto;
import com.musicallcommunity.musicallback.model.Privilege;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PrivilegeUtil {

    public static PrivilegeDto toPrivilege(final com.musicallcommunity.musicallback.model.Privilege privilege) {
        if(privilege == null) {
            return null;
        }

        return PrivilegeDto.PrivilegeDtoBuilder.builder()
                .name(privilege.getName().name()).build();
    }

    public static List<PrivilegeDto> toPrivileges(final Collection<Privilege> privileges) {
        final List<PrivilegeDto> listPrivileges = new ArrayList<>();
        if(privileges != null) {
            for(final com.musicallcommunity.musicallback.model.Privilege privilege : privileges) {
                listPrivileges.add(toPrivilege(privilege));
            }
        }
        return listPrivileges;
    }
}