package com.sumit.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class S_02_RAGDocumentService {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public S_02_RAGDocumentService(@Qualifier("ChatClient_with_jdbc_chat_memory") ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    @Value("classpath:/promptTemplates/HRSystemPromptTemplate.st")
    Resource hrSystemTemplate;

    public String chat(String message, String username){

        // STEP-1: search similar documents in Vector Store
        SearchRequest searchRequest = SearchRequest.builder()
                                                   .query(message)
                                                   .topK(3)
                                                   .similarityThreshold(0.5)
                                                   .build();
        List<Document> similarDocs =  vectorStore.similaritySearch(searchRequest);

        // STEP-2: join all similar documents to prepare 1 string of similar context
        String similarContext = similarDocs.stream()
                                           .map(Document::getText)
                                           .collect(Collectors.joining(System.lineSeparator()));

        // STEP-3: provide calculated similar context to LLM model to fetch the answer
        return chatClient.prompt()
                         .system(
                                 promptSystemSpec -> promptSystemSpec.text(hrSystemTemplate)
                                         .param("documents", similarContext))
                         .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, username))
                         .user(message)
                         .call().content();
    }

}