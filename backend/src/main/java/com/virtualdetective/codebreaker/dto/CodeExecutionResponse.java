package com.virtualdetective.codebreaker.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CodeExecutionResponse {
    private boolean success;
    private String output;
    private String error;
    private String hint;
    private List<TestCaseResult> testResults;
    private String explanation;

    @Data
    @Builder
    public static class TestCaseResult {
        private String input;
        private String expectedOutput;
        private String actualOutput;
        private boolean passed;
        private String error;
    }
} 