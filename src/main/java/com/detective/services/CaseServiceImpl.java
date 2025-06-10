package com.detective.services;

import com.detective.models.Case;
import com.detective.repositories.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseRepository caseRepository;

    @Override
    public Case save(Case caseEntity) {
        return caseRepository.save(caseEntity);
    }

    @Override
    public Optional<Case> findById(Long id) {
        return caseRepository.findById(id);
    }

    @Override
    public List<Case> findAll() {
        return caseRepository.findAll();
    }

    @Override
    public List<Case> findByUserId(Long userId) {
        return caseRepository.findBySolvedById(userId);
    }

    @Override
    public List<Case> findByDifficulty(String difficulty) {
        return caseRepository.findByDifficulty(difficulty);
    }

    @Override
    public void deleteById(Long id) {
        caseRepository.deleteById(id);
    }
} 