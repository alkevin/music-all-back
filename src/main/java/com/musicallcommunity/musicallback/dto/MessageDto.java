package com.musicallcommunity.musicallback.dto;

import com.musicallcommunity.musicallback.model.MessageStatus;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class MessageDto {

    @NotNull
    private Long conversationId;

    @NotNull
    private Long senderId;

    @NotNull
    private String content;

    @NotNull
    private MessageStatus status;

    @NotNull
    private Date creationDate;

    @NotNull
    private Date modificationDate;

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [conversationId=").append(conversationId)
                .append(", senderId=").append(senderId)
                .append(", content=").append(content)
                .append(", status=").append(status)
                .append(", creationDate=").append(creationDate)
                .append(", modificationDate=").append(modificationDate).append("]");
        return builder.toString();
    }

    public static final class MessageDtoBuilder {
        private MessageDto messageDto;

        private MessageDtoBuilder() {
            messageDto = new MessageDto();
        }

        public static MessageDto.MessageDtoBuilder builder() {
            return new MessageDto.MessageDtoBuilder();
        }

        public MessageDto.MessageDtoBuilder conversationId(Long conversationId) {
            messageDto.setConversationId(conversationId);
            return this;
        }

        public MessageDto.MessageDtoBuilder senderId(Long senderId) {
            messageDto.setSenderId(senderId);
            return this;
        }

        public MessageDto.MessageDtoBuilder content(String content) {
            messageDto.setContent(content);
            return this;
        }

        public MessageDto.MessageDtoBuilder status(MessageStatus status) {
            messageDto.setStatus(status);
            return this;
        }

        public MessageDto.MessageDtoBuilder creationDate(Date creationDate) {
            messageDto.setCreationDate(creationDate);
            return this;
        }

        public MessageDto.MessageDtoBuilder modificationDate(Date modificationDate) {
            messageDto.setModificationDate(modificationDate);
            return this;
        }

        public MessageDto build() {
            return messageDto;
        }
    }
}
