package com.sumit.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Service_9_StructuredOutput_List {

    private final ChatClient chatClient;

    public Service_9_StructuredOutput_List(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    public List<String> chat(String message){
        return chatClient.prompt()
                         .user(message)
                         .call().entity(new ListOutputConverter());
    }

}