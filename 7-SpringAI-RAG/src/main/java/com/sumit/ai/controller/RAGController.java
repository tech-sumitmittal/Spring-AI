package com.sumit.ai.controller;

import com.sumit.ai.service.S_01_RAGTextService;
import com.sumit.ai.service.S_02_RAGDocumentService;
import com.sumit.ai.service.S_03_RAGDocumentWithAdvisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rag")
public class RAGController {

    private final S_01_RAGTextService s_01_ragTextService;
    private final S_02_RAGDocumentService s_02_ragDocumentService;
    private final S_03_RAGDocumentWithAdvisorService s_03_ragDocumentWithAdvisorService;

    public RAGController(S_01_RAGTextService s_01_ragTextService, S_02_RAGDocumentService s_02_ragDocumentService, S_03_RAGDocumentWithAdvisorService s_03_ragDocumentWithAdvisorService) {
        this.s_01_ragTextService = s_01_ragTextService;
        this.s_02_ragDocumentService = s_02_ragDocumentService;
        this.s_03_ragDocumentWithAdvisorService = s_03_ragDocumentWithAdvisorService;
    }

    @GetMapping("/text/chat")
    public ResponseEntity<String> textChat(@RequestParam("message") String message,
                                             @RequestHeader("username") String username) {
        return ResponseEntity.ok(s_01_ragTextService.chat(message, username));
    }

    // manually perform vector search
    @GetMapping("/document/chat")
    public ResponseEntity<String> documentChat(@RequestParam("message") String message,
                                             @RequestHeader("username") String username) {
        return ResponseEntity.ok(s_02_ragDocumentService.chat(message, username));
    }

    // automatically perform vector search using RetrievalAugmentationAdvisor
    @GetMapping("/document/chatWithAdvisor")
    public ResponseEntity<String> documentChatWithAdvisor(@RequestParam("message") String message,
                                               @RequestHeader("username") String username) {
        return ResponseEntity.ok(s_03_ragDocumentWithAdvisorService.chat(message, username));
    }


}