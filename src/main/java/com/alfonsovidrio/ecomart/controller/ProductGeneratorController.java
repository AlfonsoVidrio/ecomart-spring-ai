package com.alfonsovidrio.ecomart.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product-generator")
public class ProductGeneratorController {

    private final ChatClient chatClient;

    public ProductGeneratorController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping
    public String generateProduct() {
        String userInput = "generate 5 products with the following attributes: name, price and description";
        return this.chatClient.prompt()
                .user(userInput)
                .call()
                .content();
    }
}
