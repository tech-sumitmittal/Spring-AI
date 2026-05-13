package com.sumit.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class Service_2_PromptTemplates {

    private final ChatClient chatClient;

    public Service_2_PromptTemplates(ChatClient.Builder chatClientBuilder ){
        this.chatClient = chatClientBuilder
                            .defaultSystem("""
                                You are a professional customer service assistant which helps drafting email
                                responses to improve the productivity of the customer support team
                                """)
                            .build();
    }

    @Value("classpath:/promptTemplates/userPromptTemplate.st")
    Resource userPromptTemplate;

    public String chat(String customerName, String customerMessage){
        return chatClient
                .prompt()
                .user(pts -> pts.text(userPromptTemplate)
                                               .param("customerName", customerName)
                                               .param("customerMessage", customerMessage))
                .call().content();
    }

}