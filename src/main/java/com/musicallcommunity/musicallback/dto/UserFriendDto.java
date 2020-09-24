package com.musicallcommunity.musicallback.dto;

import com.musicallcommunity.musicallback.model.FriendStatus;

import javax.validation.constraints.NotNull;

public class UserFriendDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long friendId;

    @NotNull
    private FriendStatus status;

    private FriendDto friend;

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public Long getFriendId() { return friendId; }

    public void setFriendId(Long friendId) { this.friendId = friendId; }

    public FriendStatus getStatus() { return status; }

    public void setStatus(FriendStatus status) { this.status = status; }

    public FriendDto getFriend() { return friend; }

    public void setFriend(FriendDto friend) { this.friend = friend; }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserFriendDto [user_id=").append(userId)
                .append(", friend_id=").append(friendId)
                .append(", status=").append(status)
                .append("]");
        return builder.toString();
    }

    public static final class UserFriendDtoBuilder {
        private UserFriendDto userFriendDto;

        private UserFriendDtoBuilder() {
            userFriendDto = new UserFriendDto();
        }

        public static UserFriendDto.UserFriendDtoBuilder builder() {
            return new UserFriendDto.UserFriendDtoBuilder();
        }

        public UserFriendDto.UserFriendDtoBuilder userId(Long userId) {
            userFriendDto.setUserId(userId);
            return this;
        }

        public UserFriendDto.UserFriendDtoBuilder friendId(Long friendId) {
            userFriendDto.setFriendId(friendId);
            return this;
        }

        public UserFriendDto.UserFriendDtoBuilder status(FriendStatus status) {
            userFriendDto.setStatus(status);
            return this;
        }

        public UserFriendDto.UserFriendDtoBuilder friend(FriendDto friend) {
            userFriendDto.setFriend(friend);
            return this;
        }

        public UserFriendDto build() {
            return userFriendDto;
        }
    }
}
