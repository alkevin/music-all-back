package com.musicallcommunity.musicallback.dto;

import com.sun.istack.NotNull;

import java.util.List;

public class RoleDto {

    @NotNull
    private List<PrivilegeDto> privileges;

    @NotNull
    private String name;

    @NotNull
    private String authority;

    public List<PrivilegeDto> getPrivileges() { return privileges; }

    public void setPrivileges(List<PrivilegeDto> privileges) { this.privileges = privileges; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getAuthority() { return authority; }

    public void setAuthority(String authority) { this.authority = authority; }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("RoleDto [privileges=").append(privileges).append(", name=").append(name)
                .append(", authority=").append(authority).append("]");
        return builder.toString();
    }


    public static final class RoleDtoBuilder {
        private RoleDto roleDto;

        private RoleDtoBuilder() {
            roleDto = new RoleDto();
        }

        public static RoleDtoBuilder builder() {
            return new RoleDtoBuilder();
        }

        public RoleDtoBuilder privileges(List<PrivilegeDto> privileges) {
            roleDto.setPrivileges(privileges);
            return this;
        }

        public RoleDtoBuilder name(String name) {
            roleDto.setName(name);
            return this;
        }

        public RoleDtoBuilder authority(String authority) {
            roleDto.setAuthority(authority);
            return this;
        }

        public RoleDto build() {
            return roleDto;
        }
    }
}
