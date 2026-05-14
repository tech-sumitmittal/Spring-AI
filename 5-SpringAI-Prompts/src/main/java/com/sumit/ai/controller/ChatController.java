package com.sumit.ai.controller;

import com.sumit.ai.model.CountryCitiesModel;
import com.sumit.ai.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

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
    private final Service_8_StructuredOutput_POJO service8StructuredOutputPojo;
    private final Service_9_StructuredOutput_List service9StructuredOutputList;

    public ChatController(Service_1_DefaultPrompt service1DefaultPrompt, Service_2_PromptTemplates service2PromptTemplates, Service_3_PromptStuffing service3PromptStuffing, Service_4_Advisors service4Advisors, Service_5_CustomAdvisors service5CustomAdvisors, Service_6_ChatOptions service6ChatOptions, Service_7_Stream service7Stream, Service_8_StructuredOutput_POJO service8StructuredOutputPojo, Service_9_StructuredOutput_List service9StructuredOutputList) {
        this.service1DefaultPrompt = service1DefaultPrompt;
        this.service2PromptTemplates = service2PromptTemplates;
        this.service3PromptStuffing = service3PromptStuffing;
        this.service4Advisors = service4Advisors;
        this.service5CustomAdvisors = service5CustomAdvisors;
        this.service6ChatOptions = service6ChatOptions;
        this.service7Stream = service7Stream;
        this.service8StructuredOutputPojo = service8StructuredOutputPojo;
        this.service9StructuredOutputList = service9StructuredOutputList;
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

    @GetMapping("/chat_structuredPOJO")
    public ResponseEntity<CountryCitiesModel> structuredPOJO(@RequestParam("message") String message){
        CountryCitiesModel countryCitiesModel = service8StructuredOutputPojo.chat(message);
        return ResponseEntity.ok(countryCitiesModel);
    }

    @GetMapping("/chat_structuredList")
    public ResponseEntity<List<String>> structuredList(@RequestParam("message") String message){
        List<String> cities = service9StructuredOutputList.chat(message);
        return ResponseEntity.ok(cities);
    }


}