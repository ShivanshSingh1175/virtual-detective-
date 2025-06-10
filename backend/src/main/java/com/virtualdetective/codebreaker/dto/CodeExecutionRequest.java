package com.virtualdetective.codebreaker.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class CodeExecutionRequest {
    @NotBlank(message = "Language is required")
    private String language;

    @NotBlank(message = "Code is required")
    private String code;

    private String input;

    private List<String> testCases;

    private List<String> expectedOutput;

    private boolean generateHint;

    public CodeExecutionRequest() {}
    public CodeExecutionRequest(String language, String code, String input, List<String> testCases, List<String> expectedOutput, boolean generateHint) {
        this.language = language;
        this.code = code;
        this.input = input;
        this.testCases = testCases;
        this.expectedOutput = expectedOutput;
        this.generateHint = generateHint;
    }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getInput() { return input; }
    public void setInput(String input) { this.input = input; }
    public List<String> getTestCases() { return testCases; }
    public void setTestCases(List<String> testCases) { this.testCases = testCases; }
    public List<String> getExpectedOutput() { return expectedOutput; }
    public void setExpectedOutput(List<String> expectedOutput) { this.expectedOutput = expectedOutput; }
    public boolean isGenerateHint() { return generateHint; }
    public void setGenerateHint(boolean generateHint) { this.generateHint = generateHint; }
}