package com.musicallcommunity.musicallback.dto.util;

import com.musicallcommunity.musicallback.dto.MessageDto;
import com.musicallcommunity.musicallback.model.Message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MessageUtil {

    public static MessageDto toMessage(final com.musicallcommunity.musicallback.model.Message message) {
        if(message == null) {
            return null;
        }

        return MessageDto.MessageDtoBuilder.builder()
                .conversationId(message.getConversation().getId())
                .senderId(message.getUser().getId())
                .content(message.getContent())
                .status(message.getStatus())
                .creationDate(message.getCreationDate())
                .modificationDate(message.getModificationDate())
                .build();
    }

    public static List<MessageDto> toMessages(final Collection<Message> messages) {
        final List<MessageDto> listMessages = new ArrayList<>();
        if(listMessages != null) {
            for(final com.musicallcommunity.musicallback.model.Message message : messages) {
                listMessages.add(toMessage(message));
            }
        }
        return listMessages;
    }
}
