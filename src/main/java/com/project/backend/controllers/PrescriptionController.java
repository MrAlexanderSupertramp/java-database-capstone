package com.project.backend.controllers;

import com.project.backend.models.Prescription;
import com.project.backend.repositories.PrescriptionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrescriptionController {
    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionController(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @PostMapping("/api/prescriptions")
    public ResponseEntity<?> savePrescription(@RequestHeader(name = "Authorization", required = false) String token,
                                              @RequestBody Prescription prescription) {
        if (token == null || token.isBlank()) {
            return ResponseEntity.status(401).body("Missing token");
        }
        Prescription saved = prescriptionRepository.save(prescription);
        return ResponseEntity.ok(saved);
    }
}
