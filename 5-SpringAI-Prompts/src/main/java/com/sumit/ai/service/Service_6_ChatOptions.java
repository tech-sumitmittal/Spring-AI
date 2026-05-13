package com.sumit.ai.service;

import com.sumit.ai.advisors.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Service_6_ChatOptions {

    private final ChatClient chatClient;

    public Service_6_ChatOptions(ChatClient.Builder chatClientBuilder ){
        ChatOptions chatOptions = ChatOptions.builder()
                                    .model("gpt-3.5-turbo")
                                    .temperature(0.3)
                                    .maxTokens(200)
                                    .presencePenalty(0.6)
                                    .stopSequences(List.of("END"))
                                    .build();

        this.chatClient = chatClientBuilder
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor()) )
                .defaultOptions(chatOptions)
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