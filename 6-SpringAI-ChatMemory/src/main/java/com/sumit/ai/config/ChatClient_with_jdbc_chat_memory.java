package com.sumit.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClient_with_jdbc_chat_memory {

    @Bean("ChatClient_with_jdbc_chat_memory")
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder,
                                 @Qualifier("jdbcChatMemory") ChatMemory chatMemory) {
        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();

        return chatClientBuilder
                .defaultAdvisors(List.of(loggerAdvisor, memoryAdvisor))
                .build();
    }

    // to override default behavior of MessageWindowCM
    @Bean("jdbcChatMemory")
    ChatMemory chatMemory(JdbcChatMemoryRepository jdbcCMR) {
        return MessageWindowChatMemory.builder()
                .maxMessages(3)                     // will remember last 3 messages
                .chatMemoryRepository(jdbcCMR)
                .build();
    }

}