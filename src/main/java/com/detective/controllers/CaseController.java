package com.detective.controllers;

import com.detective.models.Case;
import com.detective.services.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cases")
public class CaseController {

    @Autowired
    private CaseService caseService;

    @GetMapping
    public ResponseEntity<List<Case>> getAllCases() {
        return ResponseEntity.ok(caseService.getAllCases());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCaseById(@PathVariable Long id) {
        return caseService.getCaseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<List<Case>> getCasesByDifficulty(@PathVariable String difficulty) {
        return ResponseEntity.ok(caseService.getCasesByDifficulty(difficulty));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    public ResponseEntity<List<Case>> getCasesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(caseService.getCasesByUserId(userId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCase(@RequestBody Case case_) {
        try {
            Case createdCase = caseService.createCase(case_);
            return ResponseEntity.ok(createdCase);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to create case: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCase(@PathVariable Long id, @RequestBody Case caseDetails) {
        try {
            Case updatedCase = caseService.updateCase(id, caseDetails);
            return ResponseEntity.ok(updatedCase);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to update case: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCase(@PathVariable Long id) {
        try {
            caseService.deleteCase(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Case deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to delete case: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
} 