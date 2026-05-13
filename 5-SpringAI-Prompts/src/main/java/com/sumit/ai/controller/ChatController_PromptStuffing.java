package com.sumit.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController_PromptStuffing {

    private final ChatClient chatClient;

    public ChatController_PromptStuffing(ChatClient.Builder ccBuilder ){
        this.chatClient = ccBuilder.build();
    }

    @Value("classpath:/promptTemplates/systemPromptTemplate.st")
    Resource systemPromptTemplate;

    @GetMapping("/chat_promptStuffing")
    public String chat(@RequestParam("message") String message){
        return chatClient
                .prompt()
                .system(systemPromptTemplate)
                .user(message)
                .call().content();
    }

}