package com.sumit.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class Service_3_PromptStuffing {

    private final ChatClient chatClient;

    public Service_3_PromptStuffing(ChatClient.Builder chatClientBuilder ){
        this.chatClient = chatClientBuilder.build();
    }

    @Value("classpath:/promptTemplates/systemPromptTemplate.st")
    Resource systemPromptTemplate;

    public String chat(String message){
        return chatClient
                .prompt()
                .system(systemPromptTemplate)
                .user(message)
                .call().content();
    }

}