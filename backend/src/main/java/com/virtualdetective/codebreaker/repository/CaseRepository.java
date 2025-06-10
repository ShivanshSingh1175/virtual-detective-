package com.virtualdetective.codebreaker.repository;

import com.virtualdetective.codebreaker.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {
    List<Case> findByActiveTrue();
    List<Case> findByActiveTrueAndDifficultyLessThanEqual(int maxDifficulty);
    List<Case> findByTitleContainingIgnoreCase(String title);
} 