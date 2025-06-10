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
@Table(name = "cases")
@EntityListeners(AuditingEntityListener.class)
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Column(columnDefinition = "TEXT")
    private String conclusion;

    @OneToMany(mappedBy = "case", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Level> levels = new ArrayList<>();

    @Column(nullable = false)
    private int difficulty;

    @Column(nullable = false)
    private boolean active = true;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(name = "case_suspects", joinColumns = @JoinColumn(name = "case_id"))
    private List<Suspect> suspects = new ArrayList<>();

    // Explicit no-args and all-args constructors
    public Case() {}

    public Case(Long id, String title, String introduction, String conclusion, List<Level> levels, int difficulty, boolean active, LocalDateTime createdAt, LocalDateTime updatedAt, List<Suspect> suspects) {
        this.id = id;
        this.title = title;
        this.introduction = introduction;
        this.conclusion = conclusion;
        this.levels = levels;
        this.difficulty = difficulty;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.suspects = suspects;
    }

    // Explicit getters and setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public List<Suspect> getSuspects() {
        return suspects;
    }

    public void setSuspects(List<Suspect> suspects) {
        this.suspects = suspects;
    }

    @Embeddable
    public static class Suspect {
        private String name;
        private String description;
        private String motive;
        private String alibi;

        // Explicit no-args constructor
        public Suspect() {}

        // Add all-args constructor if needed
        public Suspect(String name, String description, String motive, String alibi) {
            this.name = name;
            this.description = description;
            this.motive = motive;
            this.alibi = alibi;
        }

        // Add getters and setters for all fields
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMotive() {
            return motive;
        }

        public void setMotive(String motive) {
            this.motive = motive;
        }

        public String getAlibi() {
            return alibi;
        }

        public void setAlibi(String alibi) {
            this.alibi = alibi;
        }
    }
}