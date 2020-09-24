package com.musicallcommunity.musicallback.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "user_friend")
public class UserFriend extends AuditModel {

    @EmbeddedId
    private UserFriendKey id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, optional = false)
    @MapsId("userId")
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER ,cascade = {CascadeType.ALL}, optional = false)
    @MapsId("friendId")
    @JoinColumn(name="friend_id", insertable=false, updatable=false)
    private User friend;

    @Column(nullable = false)
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private FriendStatus status;

    public UserFriend() {
        super();
        this.status = FriendStatus.PENDING;
    }

    public UserFriendKey getId() { return id; }

    public void setId(UserFriendKey id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public User getFriend() { return friend; }

    public void setFriend(User friend) { this.friend = friend; }

    public FriendStatus getStatus() { return status; }

    public void setStatus(FriendStatus status) { this.status = status; }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserFriend [id=").append(id)
                .append(", status=").append(status)
                .append("]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserFriend)) return false;
        UserFriend that = (UserFriend) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getFriend(), that.getFriend()) &&
                getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getFriend(), getStatus());
    }
}
