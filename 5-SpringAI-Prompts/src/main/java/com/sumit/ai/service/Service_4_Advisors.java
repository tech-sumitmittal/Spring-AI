package com.sumit.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class Service_4_Advisors {

    private final ChatClient chatClient;

    public Service_4_Advisors(ChatClient.Builder ccBuilder ){
        this.chatClient = ccBuilder.build();
    }

    @Value("classpath:/promptTemplates/systemPromptTemplate.st")
    Resource systemPromptTemplate;

    public String chat(String message){
        return chatClient
                .prompt()
                .advisors(new SimpleLoggerAdvisor())
                .system(systemPromptTemplate)
                .user(message)
                .call().content();
    }

}