package com.detective.controllers;

import com.detective.models.Challenge;
import com.detective.services.ChallengeService;
import com.detective.services.CodeExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CodeExecutionController {

    @Autowired
    private CodeExecutionService codeExecutionService;

    @Autowired
    private ChallengeService challengeService;

    @PostMapping("/execute")
    public ResponseEntity<?> executeCode(@RequestBody Map<String, String> request) {
        try {
            String code = request.get("code");
            String language = request.get("language");
            
            Map<String, Object> result = codeExecutionService.executeCode(code, language);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to execute code: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PostMapping("/challenges/{challengeId}/validate")
    public ResponseEntity<?> validateSolution(
            @PathVariable Long challengeId,
            @RequestBody Map<String, String> request) {
        try {
            Challenge challenge = challengeService.getChallengeById(challengeId);
            String code = request.get("code");
            
            boolean isValid = codeExecutionService.validateSolution(challenge, code);
            
            Map<String, Object> response = new HashMap<>();
            response.put("valid", isValid);
            response.put("message", isValid ? "Solution is correct!" : "Solution is incorrect. Try again!");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to validate solution: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
} 