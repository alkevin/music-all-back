package com.musicallcommunity.musicallback.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserFriendKey implements Serializable {

    @Column(name="user_id", nullable=false)
    @NotNull
    private Long userId;

    @Column(name="friend_id,", nullable=false)
    @NotNull
    private Long friendId;

    public UserFriendKey() {
    }

    public UserFriendKey(Long userId, Long friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public Long getFriendId() { return friendId; }

    public void setFriendId(Long friendId) { this.friendId = friendId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserFriendKey)) return false;
        UserFriendKey that = (UserFriendKey) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getFriendId(), that.getFriendId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getFriendId());
    }
}
