package com.virtualdetective.codebreaker.repository;

import com.virtualdetective.codebreaker.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByUserIdOrderBySubmittedAtDesc(Long userId);
    List<Submission> findByUserIdAndLevelIdOrderBySubmittedAtDesc(Long userId, Long levelId);
    List<Submission> findByUserIdAndSuccessTrueOrderBySubmittedAtDesc(Long userId);
    boolean existsByUserIdAndLevelIdAndSuccessTrue(Long userId, Long levelId);
} 