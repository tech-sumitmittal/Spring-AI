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
public class ChatController_PromptTemplates {

    private final ChatClient chatClient;

    public ChatController_PromptTemplates(ChatClient.Builder ccBuilder ){
        this.chatClient = ccBuilder
                            .defaultSystem("""
                                You are a professional customer service assistant which helps drafting email
                                responses to improve the productivity of the customer support team
                                """)
                            .build();
    }

    @Value("classpath:/promptTemplates/userPromptTemplate.st")
    Resource userPromptTemplate;

    @GetMapping("/chat_promptTemplates")
    public String chat(@RequestParam("customerName") String customerName,
                       @RequestParam("customerMessage") String customerMessage){
        return chatClient
                .prompt()
                .user(pts -> pts.text(userPromptTemplate)
                                               .param("customerName", customerName)
                                               .param("customerMessage", customerMessage))
                .call().content();
    }

}