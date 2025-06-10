package com.detective.repositories;

import com.detective.models.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findByCaseId(Long caseId);
    List<Challenge> findByDifficulty(String difficulty);
} 