package com.sumit.ai.service;

import com.sumit.ai.model.CountryCitiesModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.stereotype.Service;

@Service
public class Service_8_StructuredOutput_POJO {

    private final ChatClient chatClient;

    public Service_8_StructuredOutput_POJO(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    public CountryCitiesModel chat(String message){
        return chatClient.prompt()
                         .user(message)
                         .call().entity(CountryCitiesModel.class);
    }

}