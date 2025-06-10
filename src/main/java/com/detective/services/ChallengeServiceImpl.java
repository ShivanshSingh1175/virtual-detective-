package com.detective.services;

import com.detective.models.Challenge;
import com.detective.repositories.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Override
    public Challenge save(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    @Override
    public Optional<Challenge> findById(Long id) {
        return challengeRepository.findById(id);
    }

    @Override
    public List<Challenge> findByCaseId(Long caseId) {
        return challengeRepository.findByCaseEntityId(caseId);
    }

    @Override
    public void deleteById(Long id) {
        challengeRepository.deleteById(id);
    }
} 