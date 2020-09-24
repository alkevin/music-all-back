package com.musicallcommunity.musicallback.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class ConversationDto {

    @NotNull
    private Date creationDate;

    @NotNull
    private Date modificationDate;

    @NotNull
    private List<MessageDto> messages;

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

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("FriendDto creationDate=").append(creationDate)
                .append(", modificationDate=").append(modificationDate)
                .append(", messages=").append(messages).append("]");
        return builder.toString();
    }

    public static final class ConversationDtoBuilder {
        private ConversationDto conversationDto;

        private ConversationDtoBuilder() {
            conversationDto = new ConversationDto();
        }

        public static ConversationDto.ConversationDtoBuilder builder() {
            return new ConversationDto.ConversationDtoBuilder();
        }

        public ConversationDto.ConversationDtoBuilder creationDate(Date creationDate) {
            conversationDto.setCreationDate(creationDate);
            return this;
        }

        public ConversationDto.ConversationDtoBuilder modificationDate(Date modificationDate) {
            conversationDto.setModificationDate(modificationDate);
            return this;
        }

        public ConversationDto.ConversationDtoBuilder messages(List<MessageDto> message) {
            conversationDto.setMessages(message);
            return this;
        }

        public ConversationDto build() {
            return conversationDto;
        }
    }
}
