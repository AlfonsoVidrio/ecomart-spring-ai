package com.alfonsovidrio.ecomart.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/images-generator")
public class ImagesGeneratorController {

    private final ImageModel imageModel;

    public ImagesGeneratorController(ImageModel imageModel) {
        this.imageModel = imageModel;
    }
    // localhost:8080/api/images-generator?prompt=cute cat
    @GetMapping
    public String generateImages(String prompt) {
        var options = ImageOptionsBuilder.builder()
                .withHeight(256)
                .withWidth(256)
                .build();
        var response = imageModel.call(new ImagePrompt(prompt, options));
        return response.getResult().getOutput().getUrl();
    }
}
