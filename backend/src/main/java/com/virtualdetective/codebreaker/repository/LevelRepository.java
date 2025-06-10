package com.virtualdetective.codebreaker.repository;

import com.virtualdetective.codebreaker.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    List<Level> findByDetectiveCaseIdOrderByLevelNumberAsc(Long caseId);
    Optional<Level> findByDetectiveCaseIdAndLevelNumber(Long caseId, int levelNumber);
    List<Level> findByDetectiveCaseIdAndDifficultyLessThanEqual(Long caseId, int maxDifficulty);
} 