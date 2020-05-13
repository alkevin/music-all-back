package com.musicallcommunity.musicallback.dto;

import com.musicallcommunity.musicallback.model.AuthProvider;
import com.musicallcommunity.musicallback.validation.ValidEmail;
import com.musicallcommunity.musicallback.validation.ValidPassword;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class UserDto {

    @NotNull
    @Size(min = 1, message = "{Size.userDto.firstName}")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "{Size.userDto.lastName}")
    private String lastName;

    @ValidEmail
    @NotNull
    @Size(min = 1, message = "{Size.userDto.email}")
    private String mail;

    private List<RoleDto> roles;

    private AuthProvider provider;

    private String providerId;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<RoleDto> getRoles() { return roles; }

    public void setRoles(List<RoleDto> roles) { this.roles = roles; }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;

    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [firstName=").append(firstName).append(", lastName=").append(lastName)
                .append(", mail=").append(mail).append(", roles=").append(roles).append(", provider=").append(provider)
                .append(", providerId=").append(providerId).append("]");
        return builder.toString();
    }

    public static final class UserDtoBuilder {
        private UserDto userDto;

        private UserDtoBuilder() {
            userDto = new UserDto();
        }

        public static UserDto.UserDtoBuilder builder() {
            return new UserDto.UserDtoBuilder();
        }

        public UserDtoBuilder firstName(String firstName) {
            userDto.setFirstName(firstName);
            return this;
        }

        public UserDtoBuilder lastName(String lastName) {
            userDto.setLastName(lastName);
            return this;
        }

        public UserDtoBuilder mail(String mail) {
            userDto.setMail(mail);
            return this;
        }

        public UserDtoBuilder roles(List<RoleDto> roles) {
            userDto.setRoles(roles);
            return this;
        }

        public UserDtoBuilder provider(AuthProvider provider) {
            userDto.setProvider(provider);
            return this;
        }

        public UserDtoBuilder providerId(String providerId) {
            userDto.setProviderId(providerId);
            return this;
        }

        public UserDto build() {
            return userDto;
        }
    }
}
