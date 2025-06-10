package com.virtualdetective.codebreaker.dto;

import java.util.List;

public class CodeExecutionResponse {
    private String output;
    private String error;
    private List<TestCaseResult> testCaseResults;

    // Explicit no-args and all-args constructors
    public CodeExecutionResponse() {}

    public CodeExecutionResponse(String output, String error, List<TestCaseResult> testCaseResults) {
        this.output = output;
        this.error = error;
        this.testCaseResults = testCaseResults;
    }

    // Explicit getters and setters for all fields
    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<TestCaseResult> getTestCaseResults() {
        return testCaseResults;
    }

    public void setTestCaseResults(List<TestCaseResult> testCaseResults) {
        this.testCaseResults = testCaseResults;
    }

    public static class TestCaseResult {
        private String input;
        private String expectedOutput;
        private String actualOutput;
        private boolean passed;
        private String error;

        // Explicit no-args and all-args constructors
        public TestCaseResult() {}

        public TestCaseResult(String input, String expectedOutput, String actualOutput, boolean passed, String error) {
            this.input = input;
            this.expectedOutput = expectedOutput;
            this.actualOutput = actualOutput;
            this.passed = passed;
            this.error = error;
        }

        // Explicit getters and setters for all fields
        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }

        public String getExpectedOutput() {
            return expectedOutput;
        }

        public void setExpectedOutput(String expectedOutput) {
            this.expectedOutput = expectedOutput;
        }

        public String getActualOutput() {
            return actualOutput;
        }

        public void setActualOutput(String actualOutput) {
            this.actualOutput = actualOutput;
        }

        public boolean isPassed() {
            return passed;
        }

        public void setPassed(boolean passed) {
            this.passed = passed;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}