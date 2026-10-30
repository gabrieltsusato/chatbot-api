package com.tsu.chatbot_api.repository;

import com.tsu.chatbot_api.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
