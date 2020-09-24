package com.musicallcommunity.musicallback.repository;

import com.musicallcommunity.musicallback.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> getByConversationId(Long conversationId);
}
