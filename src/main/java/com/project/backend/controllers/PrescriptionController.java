package com.project.backend.controllers;

import com.project.backend.models.Prescription;
import com.project.backend.repositories.PrescriptionRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PrescriptionController {
    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionController(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @PostMapping("/api/prescriptions")
    public ResponseEntity<Map<String, Object>> savePrescription(
            @RequestHeader(name = "Authorization", required = false) String token,
            @Valid @RequestBody Prescription prescription) {
        Map<String, Object> response = new HashMap<>();
        
        if (token == null || token.isBlank()) {
            response.put("success", false);
            response.put("message", "Missing or invalid token");
            return ResponseEntity.status(401).body(response);
        }
        
        try {
            Prescription saved = prescriptionRepository.save(prescription);
            response.put("success", true);
            response.put("message", "Prescription saved successfully");
            response.put("data", saved);
            response.put("id", saved.getId());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error saving prescription");
            response.put("error", e.getMessage());
            return ResponseEntity.status(400).body(response);
        }
    }
}
