package com.detective.controllers;

import com.detective.models.Challenge;
import com.detective.services.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;

    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        return ResponseEntity.ok(challengeService.getAllChallenges());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getChallengeById(@PathVariable Long id) {
        return challengeService.getChallengeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<Challenge>> getChallengesByCaseId(@PathVariable Long caseId) {
        return ResponseEntity.ok(challengeService.getChallengesByCaseId(caseId));
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<Challenge>> getChallengesByDifficulty(@PathVariable String difficulty) {
        return ResponseEntity.ok(challengeService.getChallengesByDifficulty(difficulty));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createChallenge(@RequestBody Challenge challenge) {
        try {
            Challenge createdChallenge = challengeService.createChallenge(challenge);
            return ResponseEntity.ok(createdChallenge);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to create challenge: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateChallenge(@PathVariable Long id, @RequestBody Challenge challengeDetails) {
        try {
            Challenge updatedChallenge = challengeService.updateChallenge(id, challengeDetails);
            return ResponseEntity.ok(updatedChallenge);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to update challenge: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteChallenge(@PathVariable Long id) {
        try {
            challengeService.deleteChallenge(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Challenge deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to delete challenge: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
} 