package com.tsu.chatbot_api.service;

import com.tsu.chatbot_api.dto.ChatResponse;
import com.tsu.chatbot_api.entity.Conversation;
import com.tsu.chatbot_api.entity.Message;
import com.tsu.chatbot_api.entity.MessageRole;
import com.tsu.chatbot_api.repository.ConversationRepository;
import com.tsu.chatbot_api.repository.MessageRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;

    public ChatService(ChatClient.Builder builder,
                       ConversationRepository conversationRepository,
                       MessageRepository messageRepository){
        this.chatClient = builder.build();
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
    }

    public ChatResponse responder(String mensagem, Long conversationId){

        Conversation conversation;

        if(conversationId == null){
            conversation = new Conversation();

            String titulo = mensagem.length() > 50
                    ? mensagem.substring(0,50)
                    : mensagem;

            conversation.setTitle(titulo);

            conversation = conversationRepository.save(conversation);

        }else{
            conversation = conversationRepository.findById(conversationId)
                    .orElseThrow(()->
                            new IllegalArgumentException("Conversa não encontrada"));
        }

        Message pergunta = new Message();
        pergunta.setContent(mensagem);
        pergunta.setRole(MessageRole.USER);
        pergunta.setConversation(conversation);

        messageRepository.save(pergunta);


        String resposta =  chatClient.prompt()
                .system("""
                Responda em português brasileiro.
                Seja direto e didático.
                Use no máximo 5 frases, exceto quando
                o usuário pedir uma explicação detalhada.
                """)
                .user(mensagem)
                .call()
                .content();

        Message respostaAssistente = new Message();
        respostaAssistente.setContent(resposta);
        respostaAssistente.setRole(MessageRole.ASSISTANT);
        respostaAssistente.setConversation(conversation);

        messageRepository.save(respostaAssistente);

        return new ChatResponse(
                conversation.getId(),
                mensagem,
                resposta
        );


    }


}
