package com.sumit.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class S_01_ChatMemoryService {

    private final ChatClient chatClient;

    public S_01_ChatMemoryService(@Qualifier("ChatClient_with_in_memory_chat_memory") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String message){
        return chatClient.prompt()
                         .user(message)
                         .call().content();
    }

}