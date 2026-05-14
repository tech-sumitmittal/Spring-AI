package com.sumit.ai.service;

import com.sumit.ai.model.CountryCitiesModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Service_11_StructuredOutput_ListPOJO {

    private final ChatClient chatClient;

    public Service_11_StructuredOutput_ListPOJO(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    public List<CountryCitiesModel> chat(String message){
        return chatClient.prompt()
                         .user(message)
                         .call()
                         .entity(new ParameterizedTypeReference<List<CountryCitiesModel>>() {   });
    }

}