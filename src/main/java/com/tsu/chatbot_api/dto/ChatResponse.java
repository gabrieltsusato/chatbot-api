package com.tsu.chatbot_api.dto;

public record ChatResponse(
        Long conversationId,
        String pergunta,
        String resposta
) {
}
