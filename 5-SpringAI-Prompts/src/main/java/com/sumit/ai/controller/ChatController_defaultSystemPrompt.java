package com.sumit.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController_DefaultSystemPrompt {

    private final ChatClient chatClient;

    public ChatController_DefaultSystemPrompt(ChatClient.Builder ccBuilder ){
        this.chatClient = ccBuilder
                .defaultSystem("""
                      You are an internal HR assistant. Your role is to help employees with questions related \s
                      to HR policies, such as leave policies, working hours, benefits, and code of conduct. \s
                      If a user asks for help with anything outside of these topics, kindly inform them that \s
                      you can only assist with queries related to HR policies.
                      """)
                .build();
    }

    @GetMapping("/chat_defaultSystemPrompt")
    public String chat(@RequestParam("message") String message){
        return chatClient
                .prompt()
                .user(message)
                .call().content();
    }

}