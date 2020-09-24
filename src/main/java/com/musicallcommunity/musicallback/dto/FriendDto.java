package com.musicallcommunity.musicallback.dto;

import javax.validation.constraints.NotNull;

public class FriendDto {

    /*@NotNull
    private Long id;*/

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private boolean connected;

    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

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

    public boolean getConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [firstName=").append(firstName).append(", lastName=").append(lastName)
                .append(", connected=").append(connected).append("]");
        return builder.toString();
    }

    public static final class FriendDtoBuilder {
            private FriendDto friendDto;

        private FriendDtoBuilder() {
            friendDto = new FriendDto();
        }

        public static FriendDto.FriendDtoBuilder builder() {
            return new FriendDto.FriendDtoBuilder();
        }

        /*public FriendDto.FriendDtoBuilder id(Long id) {
            friendDto.setId(id);
            return this;
        }*/

        public FriendDto.FriendDtoBuilder firstName(String firstName) {
            friendDto.setFirstName(firstName);
            return this;
        }

        public FriendDto.FriendDtoBuilder lastName(String lastName) {
            friendDto.setLastName(lastName);
            return this;
        }

        public FriendDto.FriendDtoBuilder connected(boolean connected) {
            friendDto.setConnected(connected);
            return this;
        }

        public FriendDto build() {
            return friendDto;
        }
    }
}
