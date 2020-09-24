package com.musicallcommunity.musicallback.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "conversation", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
})
public class Conversation extends AuditModel{

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id", nullable = false)
    private Long userId;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conversation")
    private List<Message> messages;


    public Conversation() {
        super();
        this.setCreationDate(new Date());
        this.setModificationDate(new Date());
    }

    public Conversation(User user) {
        super();
        this.user = user;
    }

    public Conversation(User user, List<Message> messages) {
        super();
        this.user = user;
        this.messages = messages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [id=").append(id).append(", user=").append(user)
                .append(", messages=").append(messages)
                .append(", creation_date=").append(getCreationDate())
                .append(", modification_date=").append(getModificationDate()).append("]");
        return builder.toString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conversation)) return false;
        Conversation that = (Conversation) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getMessages(), that.getMessages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getMessages());
    }
}
