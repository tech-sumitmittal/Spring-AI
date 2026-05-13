package com.sumit.ai.service;

import com.sumit.ai.advisors.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Service_5_CustomAdvisors {

    private final ChatClient chatClient;

    public Service_5_CustomAdvisors(ChatClient.Builder ccBuilder ){
        this.chatClient = ccBuilder
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor()) )
                .build();
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