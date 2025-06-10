package com.virtualdetective.codebreaker.dto;

import lombok.Data;

@Data
public class CodeExecutionRequest {
    private String code;
    private String language;
    private String testCases;
    private String prompt;
    private String expectedOutput;
    private boolean generateHint;
} 