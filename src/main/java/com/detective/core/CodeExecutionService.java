package com.detective.core;

import com.detective.models.Code;
import com.detective.events.CodeExecutionEvent;
import com.detective.events.CodeExecutionListener;
import com.detective.validation.CodeValidator;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CodeExecutionService {
    private final List<CodeExecutionListener> listeners = new ArrayList<>();
    private final CodeValidator codeValidator;

    public CodeExecutionService(CodeValidator codeValidator) {
        this.codeValidator = codeValidator;
    }

    public void addListener(CodeExecutionListener listener) {
        listeners.add(listener);
    }

    public void removeListener(CodeExecutionListener listener) {
        listeners.remove(listener);
    }

    public String executeCode(Code code) {
        try {
            codeValidator.validate(code);
            notifyListeners(new CodeExecutionEvent("STARTED", "", ""));

            Path tempDir = Files.createTempDirectory("code-execution-");
            String fileName = UUID.randomUUID().toString() + getFileExtension(code.getLanguage());
            File codeFile = new File(tempDir.toFile(), fileName);
            Files.write(codeFile.toPath(), code.getSourceCode().getBytes());

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.directory(tempDir.toFile());
            
            switch (code.getLanguage().toLowerCase()) {
                case "python":
                    processBuilder.command("python3", fileName);
                    break;
                case "java":
                    Process compileProcess = new ProcessBuilder("javac", fileName).start();
                    if (compileProcess.waitFor() == 0) {
                        String className = fileName.substring(0, fileName.length() - 5);
                        processBuilder.command("java", className);
                    } else {
                        String error = readStream(compileProcess.getErrorStream());
                        notifyListeners(new CodeExecutionEvent("ERROR", "", error));
                        return "Compilation failed: " + error;
                    }
                    break;
                case "javascript":
                    processBuilder.command("node", fileName);
                    break;
                default:
                    String error = "Unsupported language: " + code.getLanguage();
                    notifyListeners(new CodeExecutionEvent("ERROR", "", error));
                    return error;
            }

            Process process = processBuilder.start();
            String output = readStream(process.getInputStream());
            String error = readStream(process.getErrorStream());
            
            int exitCode = process.waitFor();
            Files.deleteIfExists(codeFile.toPath());
            Files.deleteIfExists(tempDir);
            
            String status = exitCode == 0 ? "COMPLETED" : "ERROR";
            String result = (exitCode == 0 ? "SUCCESS\n" : "ERROR\n") + output;
            if (!error.isEmpty()) {
                result += "\nError: " + error;
            }
            
            notifyListeners(new CodeExecutionEvent(status, output, error));
            return result;
        } catch (Exception e) {
            String error = "ERROR: " + e.getMessage();
            notifyListeners(new CodeExecutionEvent("ERROR", "", error));
            return error;
        }
    }

    private String getFileExtension(String language) {
        switch (language.toLowerCase()) {
            case "python": return ".py";
            case "java": return ".java";
            case "javascript": return ".js";
            default: throw new IllegalArgumentException("Unsupported language: " + language);
        }
    }

    private String readStream(java.io.InputStream stream) throws Exception {
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }
        return output.toString();
    }

    private void notifyListeners(CodeExecutionEvent event) {
        for (CodeExecutionListener listener : listeners) {
            listener.onCodeExecution(event);
        }
    }
} 