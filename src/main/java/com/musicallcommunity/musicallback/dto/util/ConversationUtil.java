package com.musicallcommunity.musicallback.dto.util;

import com.musicallcommunity.musicallback.dto.ConversationDto;
import com.musicallcommunity.musicallback.model.Conversation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConversationUtil {

    public static ConversationDto toConversation(final com.musicallcommunity.musicallback.model.Conversation conversation) {
        if(conversation == null) {
            return null;
        }

        return ConversationDto.ConversationDtoBuilder.builder()
                .creationDate(conversation.getCreationDate())
                .modificationDate(conversation.getModificationDate())
                .messages(MessageUtil.toMessages(conversation.getMessages()))
                .build();
    }

    public static List<ConversationDto> toConversations(final Collection<Conversation> conversations) {
        final List<ConversationDto> listConversations = new ArrayList<>();
        if(listConversations != null) {
            for(final com.musicallcommunity.musicallback.model.Conversation conversation : conversations) {
                listConversations.add(toConversation(conversation));
            }
        }
        return listConversations;
    }
}
