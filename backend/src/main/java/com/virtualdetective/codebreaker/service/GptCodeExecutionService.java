package com.virtualdetective.codebreaker.service;

import com.virtualdetective.codebreaker.dto.CodeExecutionRequest;
import com.virtualdetective.codebreaker.dto.CodeExecutionResponse;

public interface GptCodeExecutionService {
    CodeExecutionResponse executeCode(CodeExecutionRequest request);
    String generateHint(String prompt, String language);
    String validateSolution(String code, String testCases, String language);
} 