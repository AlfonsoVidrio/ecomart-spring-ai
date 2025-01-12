package com.alfonsovidrio.ecomart.controller;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.ModelType;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorizer")
public class ProductCategorizerController {

    private final ChatClient chatClient;

    public ProductCategorizerController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultOptions(ChatOptionsBuilder.builder()
                        .withModel("gpt-4o-mini")
                        .build())
                .build();
    }

    @GetMapping
    public String categorizeProduct(String product) {
        var system = """
                Actúa como un categorizador de productos y debes responder solo el nombre de la categoría a la que pertenece el producto.
                
                Escoge una categoría de la siguiente lista:
                1. Higiene personal
                2. Electrónicos
                3. Deportes
                4. Otros
                
                Ejemplo de uso:
                
                Producto: Pelota de fútbol
                Respuesta: Deportes
                """;
        var tokens = tokenCount(system, product);
        System.out.println(tokens);

        return this.chatClient.prompt()
                .system(system)
                .user(product)
                .options(ChatOptionsBuilder
                        .builder()
                        .withModel("gpt-4o-mini")
                        .withTemperature(0.82)
                        .build())
                .advisors(new SimpleLoggerAdvisor())
                .call()
                .content();
    }

    private int tokenCount(String system, String user) {
        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
        Encoding enc = registry.getEncodingForModel(ModelType.GPT_4O_MINI);
        return enc.countTokens(system+user);
    }
}
