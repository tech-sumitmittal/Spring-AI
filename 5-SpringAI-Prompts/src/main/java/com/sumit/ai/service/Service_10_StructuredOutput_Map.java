package com.sumit.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Service_10_StructuredOutput_Map {

    private final ChatClient chatClient;

    public Service_10_StructuredOutput_Map(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    public Map<String, Object> chat(String message){
        return chatClient.prompt()
                         .user(message)
                         .call().entity(new MapOutputConverter());
    }

}