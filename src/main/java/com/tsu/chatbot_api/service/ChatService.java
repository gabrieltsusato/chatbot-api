package com.tsu.chatbot_api.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    public String responder(String mensagem){
        return chatClient.prompt()
                .system("""
                Responda em português brasileiro.
                Seja direto e didático.
                Use no máximo 5 frases, exceto quando
                o usuário pedir uma explicação detalhada.
                """)
                .user(mensagem)
                .call()
                .content();




    }


}
