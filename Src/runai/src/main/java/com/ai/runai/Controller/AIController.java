package com.ai.runai.Controller;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class AIController {
    
    private OllamaChatModel chatModel;

    public AIController(OllamaChatModel client) {
        this.chatModel=client;
    }

    private static final String PROMPT = "Explain the UN";

    @GetMapping("/prompt")
    public String promptResponse() {
        String response = chatModel.call(PROMPT);
        return response;
    }
}
