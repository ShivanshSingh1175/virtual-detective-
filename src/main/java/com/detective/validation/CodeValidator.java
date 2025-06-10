package com.detective.validation;

import com.detective.exceptions.InvalidCodeException;
import com.detective.models.Code;

public class CodeValidator {
    public void validate(Code code) {
        if (code == null) {
            throw new InvalidCodeException("Code cannot be null");
        }
        if (code.getSourceCode() == null || code.getSourceCode().trim().isEmpty()) {
            throw new InvalidCodeException("Source code cannot be empty");
        }
        if (code.getLanguage() == null || code.getLanguage().trim().isEmpty()) {
            throw new InvalidCodeException("Language must be specified");
        }
        
        // Validate language support
        String language = code.getLanguage().toLowerCase();
        if (!language.equals("python") && !language.equals("java") && !language.equals("javascript")) {
            throw new InvalidCodeException("Unsupported language: " + language);
        }
    }
} 