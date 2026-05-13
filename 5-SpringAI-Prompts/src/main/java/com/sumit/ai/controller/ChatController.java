package com.sumit.ai.controller;

import com.sumit.ai.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final Service_1_DefaultPrompt service1DefaultPrompt;
    private final Service_2_PromptTemplates service2PromptTemplates;
    private final Service_3_PromptStuffing service3PromptStuffing;
    private final Service_4_Advisors service4Advisors;
    private final Service_5_CustomAdvisors service5CustomAdvisors;
    private final Service_6_ChatOptions service6ChatOptions;
    private final Service_7_Stream service7Stream;

    public ChatController(Service_1_DefaultPrompt service1DefaultPrompt, Service_2_PromptTemplates service2PromptTemplates, Service_3_PromptStuffing service3PromptStuffing, Service_4_Advisors service4Advisors, Service_5_CustomAdvisors service5CustomAdvisors, Service_6_ChatOptions service6ChatOptions, Service_7_Stream service7Stream) {
        this.service1DefaultPrompt = service1DefaultPrompt;
        this.service2PromptTemplates = service2PromptTemplates;
        this.service3PromptStuffing = service3PromptStuffing;
        this.service4Advisors = service4Advisors;
        this.service5CustomAdvisors = service5CustomAdvisors;
        this.service6ChatOptions = service6ChatOptions;
        this.service7Stream = service7Stream;
    }

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

    @GetMapping("/chat_stream")
    public Flux<String> stream(@RequestParam("message") String message){
        return service7Stream.chat(message);
    }


}