package com.sumit.ai.controller;

import com.sumit.ai.service.Service_1_DefaultPrompt;
import com.sumit.ai.service.Service_2_PromptTemplates;
import com.sumit.ai.service.Service_3_PromptStuffing;
import com.sumit.ai.service.Service_4_Advisors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    Service_1_DefaultPrompt service1DefaultPrompt;

    @Autowired
    Service_2_PromptTemplates service2PromptTemplates;

    @Autowired
    Service_3_PromptStuffing service3PromptStuffing;

    @Autowired
    Service_4_Advisors service4Advisors;

    @GetMapping("/chat_defaultPrompt")
    public String defaultPrompts(@RequestParam("message") String message){
        return service1DefaultPrompt.chat(message);
    }

    @GetMapping("/chat_promptTemplates")
    public String promptTemplates(@RequestParam("customerName") String customerName,
                                  @RequestParam("customerMessage") String customerMessage){
        return service2PromptTemplates.chat(customerName, customerMessage);
    }

    @GetMapping("/chat_promptStuffing")
    public String promptStuffing(@RequestParam("message") String message){
        return service3PromptStuffing.chat(message);
    }

    @GetMapping("/chat_advisors")
    public String chat(@RequestParam("message") String message){
        return service4Advisors.chat(message);
    }

}