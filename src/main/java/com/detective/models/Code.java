package com.detective.models;

public class Code {
    private String sourceCode;
    private String language;

    public Code() {}

    public Code(String sourceCode, String language) {
        this.sourceCode = sourceCode;
        this.language = language;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
} 