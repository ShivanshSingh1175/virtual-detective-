package com.detective.services;

import com.detective.models.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CodeExecutionService {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, Object> executeCode(String code, String language) {
        // Call OpenAI API for code execution
        String prompt = String.format("Execute the following %s code and provide the output:\n\n%s", language, code);
        
        Map<String, Object> request = new HashMap<>();
        request.put("model", "gpt-4");
        request.put("messages", new Object[]{
            Map.of("role", "system", "content", "You are a code execution assistant. Execute the provided code and return the output."),
            Map.of("role", "user", "content", prompt)
        });
        request.put("temperature", 0.7);

        // Make API call to OpenAI
        Map<String, Object> response = restTemplate.postForObject(
            "https://api.openai.com/v1/chat/completions",
            request,
            Map.class
        );

        return response;
    }

    public boolean validateSolution(Challenge challenge, String code) {
        // Call OpenAI API for code validation
        String prompt = String.format(
            "Validate if the following %s code solves the challenge:\n\nChallenge: %s\n\nCode:\n%s",
            challenge.getProgrammingLanguage(),
            challenge.getDescription(),
            code
        );

        Map<String, Object> request = new HashMap<>();
        request.put("model", "gpt-4");
        request.put("messages", new Object[]{
            Map.of("role", "system", "content", "You are a code validation assistant. Validate if the provided code solves the challenge correctly."),
            Map.of("role", "user", "content", prompt)
        });
        request.put("temperature", 0.7);

        // Make API call to OpenAI
        Map<String, Object> response = restTemplate.postForObject(
            "https://api.openai.com/v1/chat/completions",
            request,
            Map.class
        );

        // Parse response and determine if solution is valid
        // This is a simplified example - you would need to implement proper validation logic
        return true;
    }
} 