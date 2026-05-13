package com.sumit.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class Service_1_DefaultPrompt {

    private final ChatClient chatClient;

    public Service_1_DefaultPrompt(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder
                .defaultSystem("""
                      You are an internal HR assistant. Your role is to help employees with questions related \s
                      to HR policies, such as leave policies, working hours, benefits, and code of conduct. \s
                      If a user asks for help with anything outside of these topics, kindly inform them that \s
                      you can only assist with queries related to HR policies.
                      """)
                .build();
    }

    public String chat(String message){
        return chatClient
                .prompt()
                .user(message)
                .call().content();
    }

}