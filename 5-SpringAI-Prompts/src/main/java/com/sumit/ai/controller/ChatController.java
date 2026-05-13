package com.sumit.ai.controller;

import com.sumit.ai.service.*;
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

    @Autowired
    Service_5_CustomAdvisors service5CustomAdvisors;

    @Autowired
    Service_6_ChatOptions service6ChatOptions;

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
    public String advisors(@RequestParam("message") String message){
        return service4Advisors.chat(message);
    }

    @GetMapping("/chat_customAdvisors")
    public String customAdvisors(@RequestParam("message") String message){
        return service5CustomAdvisors.chat(message);
    }

    @GetMapping("/chat_chatOptions")
    public String chatOptions(@RequestParam("message") String message){
        return service6ChatOptions.chat(message);
    }

}