package com.musicallcommunity.musicallback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "message", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
})
public class Message extends AuditModel{

    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="conversation_id", nullable=false)
    private Long conversationId;

    @Column(name="sender_id", nullable=false)
    private Long senderId;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conversation_id", insertable=false, updatable=false)
    private Conversation conversation;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id", insertable=false, updatable=false)
    private User user;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private MessageStatus status;

    public Message() {
        super();
        this.status = MessageStatus.CREATED;
        this.setCreationDate(new Date());
        this.setModificationDate(new Date());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Message [id=").append(id).append(", conversation_id=").append(conversationId)
                .append(", sender_id=").append(senderId).append(", content=").append(content)
                .append(", status=").append(status)
                .append(", creation_date=").append(getCreationDate())
                .append(", modification_date=").append(getModificationDate()).append("]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(getId(), message.getId()) &&
                Objects.equals(getConversation(), message.getConversation()) &&
                Objects.equals(getUser(), message.getUser()) &&
                Objects.equals(getContent(), message.getContent()) &&
                getStatus() == message.getStatus() &&
                Objects.equals(getCreationDate(), message.getCreationDate()) &&
                Objects.equals(getModificationDate(), message.getModificationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getConversation(), getUser(), getContent(), getStatus(), getCreationDate(), getModificationDate());
    }
}
