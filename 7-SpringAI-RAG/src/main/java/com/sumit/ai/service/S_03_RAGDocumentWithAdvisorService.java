package com.sumit.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class S_03_RAGDocumentWithAdvisorService {

    private final ChatClient chatClient;

    public S_03_RAGDocumentWithAdvisorService(@Qualifier("ChatClient_with_jdbc_chat_memory") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String message, String username){

        // RetrievalAugmentationAdvisor will perform below 3 steps
        // STEP-1: search similar documents in Vector Store
        // STEP-2: join all similar documents to prepare 1 string of similar context
        // STEP-3: provide calculated similar context to LLM model to fetch the answer

        return chatClient.prompt()
                         .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, username))
                         .user(message)
                         .call().content();
    }

}