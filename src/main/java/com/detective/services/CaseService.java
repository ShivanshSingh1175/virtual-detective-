package com.detective.services;

import com.detective.models.Case;
import com.detective.repositories.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaseService {

    @Autowired
    private CaseRepository caseRepository;

    public List<Case> getAllCases() {
        return caseRepository.findAll();
    }

    public Optional<Case> getCaseById(Long id) {
        return caseRepository.findById(id);
    }

    public List<Case> getCasesByDifficulty(String difficulty) {
        return caseRepository.findByDifficulty(difficulty);
    }

    public List<Case> getCasesByUserId(Long userId) {
        return caseRepository.findByUserId(userId);
    }

    public Case createCase(Case case_) {
        return caseRepository.save(case_);
    }

    public Case updateCase(Long id, Case caseDetails) {
        Case case_ = caseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Case not found with id: " + id));

        case_.setTitle(caseDetails.getTitle());
        case_.setDescription(caseDetails.getDescription());
        case_.setDifficulty(caseDetails.getDifficulty());
        case_.setPoints(caseDetails.getPoints());
        case_.setStatus(caseDetails.getStatus());

        return caseRepository.save(case_);
    }

    public void deleteCase(Long id) {
        Case case_ = caseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Case not found with id: " + id));
        caseRepository.delete(case_);
    }
} 