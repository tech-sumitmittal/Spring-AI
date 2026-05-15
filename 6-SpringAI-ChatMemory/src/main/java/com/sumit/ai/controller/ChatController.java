package com.sumit.ai.controller;

import com.sumit.ai.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final S_01_ChatMemoryService s_01_chatMemoryService;
    public ChatController(S_01_ChatMemoryService s_01_chatMemoryService) {
        this.s_01_chatMemoryService = s_01_chatMemoryService;
    }

    @GetMapping("/chatMemory-in-memory")
    public ResponseEntity<String> chatMemory(@RequestParam("message") String message) {
        return ResponseEntity.ok(s_01_chatMemoryService.chat(message));
    }

}