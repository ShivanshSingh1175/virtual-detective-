package com.virtualdetective.codebreaker.controller;

import com.virtualdetective.codebreaker.dto.CodeExecutionRequest;
import com.virtualdetective.codebreaker.dto.CodeExecutionResponse;
import com.virtualdetective.codebreaker.service.GptCodeExecutionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/code")
@CrossOrigin(origins = "http://localhost:3000")
public class CodeExecutionController {

    private final GptCodeExecutionService gptCodeExecutionService;

    // Removed Lombok @RequiredArgsConstructor
    // Add explicit constructor for required fields
    public CodeExecutionController(GptCodeExecutionService gptCodeExecutionService) {
        this.gptCodeExecutionService = gptCodeExecutionService;
    }

    @PostMapping("/execute")
    public ResponseEntity<CodeExecutionResponse> executeCode(@Valid @RequestBody CodeExecutionRequest request) {
        return ResponseEntity.ok(gptCodeExecutionService.executeCode(request));
    }

    @PostMapping("/hint")
    public ResponseEntity<String> generateHint(
            @RequestParam String prompt,
            @RequestParam String language) {
        return ResponseEntity.ok(gptCodeExecutionService.generateHint(prompt, language));
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateSolution(
            @RequestParam String code,
            @RequestParam String testCases,
            @RequestParam String language) {
        return ResponseEntity.ok(gptCodeExecutionService.validateSolution(code, testCases, language));
    }
}