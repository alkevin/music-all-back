package com.musicallcommunity.musicallback.dto;

import com.musicallcommunity.musicallback.validation.ValidEmail;

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

    private ProfileDto profile;

    private List<RoleDto> roles;

    private boolean connected;

    private List<UserFriendDto> friends;

    private List<ConversationDto> conversations;

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

    public boolean getConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public List<UserFriendDto> getUserFriends() {
        return friends;
    }

    public void setUserFriends(List<UserFriendDto> friends) {
        this.friends = friends;
    }

    public List<ConversationDto> getConversations() { return conversations; }

    public void setConversations(List<ConversationDto> conversations) { this.conversations = conversations; }

    public ProfileDto getProfile() { return profile; }

    public void setProfile(ProfileDto profile) { this.profile = profile; }

    public boolean isConnected() { return connected; }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [firstName=").append(firstName).append(", lastName=").append(lastName)
                .append(", mail=").append(mail).append(", roles=").append(roles).append("]");
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

        public UserDtoBuilder profile(ProfileDto profile) {
            userDto.setProfile(profile);
            return this;
        }

        public UserDtoBuilder roles(List<RoleDto> roles) {
            userDto.setRoles(roles);
            return this;
        }

        public UserDtoBuilder friends(List<UserFriendDto> friends) {
            userDto.setUserFriends(friends);
            return this;
        }

        public UserDtoBuilder conversations(List<ConversationDto> conversations) {
            userDto.setConversations(conversations);
            return this;
        }

        public UserDtoBuilder connected(boolean connected) {
            userDto.setConnected(connected);
            return this;
        }

        public UserDto build() {
            return userDto;
        }
    }
}
