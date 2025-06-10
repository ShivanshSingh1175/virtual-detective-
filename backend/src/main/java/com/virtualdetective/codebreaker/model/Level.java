package com.virtualdetective.codebreaker.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Removed Lombok @Data
// Add explicit getters and setters for all fields below
@Entity
@Table(name = "levels")
@EntityListeners(AuditingEntityListener.class)
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id", nullable = false)
    private Case detectiveCase;

    @Column(nullable = false)
    private int levelNumber;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String prompt;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private int difficulty;

    @ElementCollection
    @CollectionTable(name = "level_test_cases", joinColumns = @JoinColumn(name = "level_id"))
    private List<TestCase> testCases = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String hint;

    @Column(columnDefinition = "TEXT")
    private String clue;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // Explicit no-args and all-args constructors
    public Level() {}

    public Level(Long id, Case detectiveCase, int levelNumber, String prompt, String language, int difficulty, List<TestCase> testCases, String hint, String clue, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.detectiveCase = detectiveCase;
        this.levelNumber = levelNumber;
        this.prompt = prompt;
        this.language = language;
        this.difficulty = difficulty;
        this.testCases = testCases;
        this.hint = hint;
        this.clue = clue;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Explicit getters and setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Case getDetectiveCase() {
        return detectiveCase;
    }

    public void setDetectiveCase(Case detectiveCase) {
        this.detectiveCase = detectiveCase;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getClue() {
        return clue;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Remove @Data annotation
    // Add explicit getters, setters, and constructors for all fields
    @Embeddable
    public static class TestCase {
        private String input;
        private String expectedOutput;
        private String description;

        // Explicit no-args constructor
        public TestCase() {}

        // Add all-args constructor if needed
        public TestCase(String input, String expectedOutput, String description) {
            this.input = input;
            this.expectedOutput = expectedOutput;
            this.description = description;
        }

        // Getters and setters
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}