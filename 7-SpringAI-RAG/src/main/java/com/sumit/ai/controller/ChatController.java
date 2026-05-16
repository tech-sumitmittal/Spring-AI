package com.sumit.ai.controller;

import com.sumit.ai.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final S_01_ChatMemoryService s_01_chatMemoryService;
    private final S_02_ChatMemoryService s_02_chatMemoryService;

    public ChatController(S_01_ChatMemoryService s_01_chatMemoryService, S_02_ChatMemoryService s_02_chatMemoryService) {
        this.s_01_chatMemoryService = s_01_chatMemoryService;
        this.s_02_chatMemoryService = s_02_chatMemoryService;
    }

    @GetMapping("/chatMemory-in-memory")
    public ResponseEntity<String> chatMemoryInMemory(@RequestParam("message") String message,
                                             @RequestHeader("username") String username) {
        return ResponseEntity.ok(s_01_chatMemoryService.chat(message, username));
    }

    @GetMapping("/chatMemory-jdbc")
    public ResponseEntity<String> chatMemoryJDBC(@RequestParam("message") String message,
                                                 @RequestHeader("username") String username) {
        return ResponseEntity.ok(s_02_chatMemoryService.chat(message, username));
    }

}