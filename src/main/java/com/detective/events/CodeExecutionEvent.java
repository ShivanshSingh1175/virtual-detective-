package com.detective.events;

public class CodeExecutionEvent {
    private String status;
    private String output;
    private String error;

    public CodeExecutionEvent() {}

    public CodeExecutionEvent(String status, String output, String error) {
        this.status = status;
        this.output = output;
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
} 