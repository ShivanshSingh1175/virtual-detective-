package com.virtualdetective.codebreaker.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
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

    @Data
    @Embeddable
    public static class TestCase {
        private String input;
        private String expectedOutput;
        private String description;
    }
} 