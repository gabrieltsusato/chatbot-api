package com.tsu.chatbot_api.repository;

import com.tsu.chatbot_api.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
