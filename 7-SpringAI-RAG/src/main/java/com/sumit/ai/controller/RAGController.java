package com.sumit.ai.controller;

import com.sumit.ai.service.S_01_RAGService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rag")
public class RAGController {

    private final S_01_RAGService s_01_ragService;

    public RAGController(S_01_RAGService s_01_ragService) {
        this.s_01_ragService = s_01_ragService;
    }

    @GetMapping("/random/chat")
    public ResponseEntity<String> randomChat(@RequestParam("message") String message,
                                             @RequestHeader("username") String username) {
        return ResponseEntity.ok(s_01_ragService.chat(message, username));
    }


}