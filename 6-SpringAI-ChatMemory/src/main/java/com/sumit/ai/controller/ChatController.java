package com.sumit.ai.controller;

import com.sumit.ai.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final S_01_ChatMemoryService s_01_chatMemoryService;
    public ChatController(S_01_ChatMemoryService s_01_chatMemoryService) {
        this.s_01_chatMemoryService = s_01_chatMemoryService;
    }

    @GetMapping("/chatMemory-in-memory")
    public ResponseEntity<String> chatMemory(@RequestParam("message") String message,
                                             @RequestHeader("username") String username) {
        return ResponseEntity.ok(s_01_chatMemoryService.chat(message, username));
    }

}