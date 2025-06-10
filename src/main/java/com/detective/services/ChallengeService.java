package com.detective.services;

import com.detective.models.Challenge;
import com.detective.repositories.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    public Challenge getChallengeById(Long id) {
        Optional<Challenge> challenge = challengeRepository.findById(id);
        if (challenge.isPresent()) {
            return challenge.get();
        }
        throw new RuntimeException("Challenge not found with id: " + id);
    }

    public List<Challenge> getChallengesByCaseId(Long caseId) {
        return challengeRepository.findByCaseId(caseId);
    }

    public Challenge createChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    public Challenge updateChallenge(Long id, Challenge challengeDetails) {
        Challenge challenge = getChallengeById(id);
        
        challenge.setTitle(challengeDetails.getTitle());
        challenge.setDescription(challengeDetails.getDescription());
        challenge.setProgrammingLanguage(challengeDetails.getProgrammingLanguage());
        challenge.setInitialCode(challengeDetails.getInitialCode());
        challenge.setDifficulty(challengeDetails.getDifficulty());
        challenge.setPoints(challengeDetails.getPoints());
        
        return challengeRepository.save(challenge);
    }

    public void deleteChallenge(Long id) {
        Challenge challenge = getChallengeById(id);
        challengeRepository.delete(challenge);
    }
} 