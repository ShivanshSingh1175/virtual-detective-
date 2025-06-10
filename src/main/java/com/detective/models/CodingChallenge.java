package com.detective.models;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "coding_challenges")
public class CodingChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String difficulty;
    private String language;
    private String initialCode;
    private String testCases;
    private String expectedOutput;
    
    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case case;
} 