package com.detective.models;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "clues")
public class Clue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String content;
    private boolean isRevealed;
    
    @ManyToOne
    @JoinColumn(name = "case_id")
    private Case detectiveCase;
} 