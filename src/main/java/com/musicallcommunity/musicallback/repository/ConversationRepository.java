package com.musicallcommunity.musicallback.repository;

import com.musicallcommunity.musicallback.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    List<Conversation> getByUserId(Long userId);
}
