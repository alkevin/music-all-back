package com.musicallcommunity.musicallback.dto;

import javax.validation.constraints.NotNull;

public class PrivilegeDto {

    @NotNull
    private String name;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PrivilegeDto [name=").append(name).append("]");
        return builder.toString();
    }


    public static final class PrivilegeDtoBuilder {
        private PrivilegeDto privilegeDto;

        private PrivilegeDtoBuilder() {
            privilegeDto = new PrivilegeDto();
        }

        public static PrivilegeDtoBuilder builder() {
            return new PrivilegeDtoBuilder();
        }

        public PrivilegeDtoBuilder name(String name) {
            privilegeDto.setName(name);
            return this;
        }

        public PrivilegeDto build() {
            return privilegeDto;
        }
    }
}

