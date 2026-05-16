package com.sumit.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class S_01_ChatMemoryService {

    // In-memory

    private final ChatClient chatClient;

    public S_01_ChatMemoryService(@Qualifier("ChatClient_with_in_memory_chat_memory") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String message, String username){
        return chatClient.prompt()
                         .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, username))
                         .user(message)
                         .call().content();
    }

}