package com.detective.repositories;

import com.detective.models.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {
    List<Case> findByUserId(Long userId);
    List<Case> findByDifficulty(String difficulty);
    List<Case> findByStatus(String status);
} 