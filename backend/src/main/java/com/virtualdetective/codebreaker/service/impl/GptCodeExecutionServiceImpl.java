package com.virtualdetective.codebreaker.service.impl;

import com.virtualdetective.codebreaker.dto.CodeExecutionRequest;
import com.virtualdetective.codebreaker.dto.CodeExecutionResponse;
import com.virtualdetective.codebreaker.service.GptCodeExecutionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GptCodeExecutionServiceImpl implements GptCodeExecutionService {

    private final RestTemplate restTemplate;

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    public GptCodeExecutionServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CodeExecutionResponse executeCode(CodeExecutionRequest request) {
        String prompt = buildExecutionPrompt(request);
        String gptResponse = callGptApi(prompt);
        return parseGptResponse(gptResponse, request);
    }

    @Override
    public String generateHint(String prompt, String language) {
        String hintPrompt = String.format(
            "Generate a helpful hint for the following programming challenge in %s. " +
            "The hint should guide the user without giving away the solution: %s",
            language, prompt
        );
        return callGptApi(hintPrompt);
    }

    @Override
    public String validateSolution(String code, String testCases, String language) {
        String validationPrompt = String.format(
            "Validate the following %s code against these test cases. " +
            "Provide detailed feedback on correctness and potential improvements:\n\n" +
            "Code:\n%s\n\nTest Cases:\n%s",
            language, code, testCases
        );
        return callGptApi(validationPrompt);
    }

    private String buildExecutionPrompt(CodeExecutionRequest request) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Execute and validate the following code:\n\n");
        prompt.append("Language: ").append(request.getLanguage()).append("\n\n");
        prompt.append("Code:\n").append(request.getCode()).append("\n\n");
        
        if (request.getTestCases() != null && !request.getTestCases().isEmpty()) {
            prompt.append("Test Cases:\n").append(request.getTestCases()).append("\n\n");
        }
        
        if (request.getExpectedOutput() != null && !request.getExpectedOutput().isEmpty()) {
            prompt.append("Expected Output:\n").append(request.getExpectedOutput()).append("\n\n");
        }

        prompt.append("Please provide:\n");
        prompt.append("1. Execution results\n");
        prompt.append("2. Test case results\n");
        prompt.append("3. Any errors or issues\n");
        prompt.append("4. A brief explanation of the solution\n");

        if (request.isGenerateHint()) {
            prompt.append("5. A helpful hint for improvement\n");
        }

        return prompt.toString();
    }

    private String callGptApi(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4");
        requestBody.put("messages", List.of(Map.of("role", "user", "content", prompt)));
        requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", 2000);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        
        try {
            Map<String, Object> response = restTemplate.postForObject(
                apiUrl + "/v1/chat/completions",
                request,
                Map.class
            );

            if (response != null && response.containsKey("choices")) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
                if (!choices.isEmpty()) {
                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                    return (String) message.get("content");
                }
            }
            return "Error: Invalid response from GPT API";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private CodeExecutionResponse parseGptResponse(String gptResponse, CodeExecutionRequest request) {
        // Parse the GPT response and extract relevant information
        // This is a simplified implementation - you might want to make it more robust
        List<CodeExecutionResponse.TestCaseResult> testResults = new ArrayList<>();
        // Extract test results from GPT response
        // This is where you'd parse the GPT response to get actual test results
        return new CodeExecutionResponse(gptResponse, null, testResults);
    }
}