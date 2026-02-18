package com.project.backend.controllers;

import com.project.backend.services.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DoctorController {
    private final DoctorService doctorService;
    private final com.project.backend.repositories.DoctorRepository doctorRepository;

    public DoctorController(DoctorService doctorService, com.project.backend.repositories.DoctorRepository doctorRepository) {
        this.doctorService = doctorService;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/api/doctors/{user}/{doctorId}/{date}/{token}/availability")
    public ResponseEntity<Map<String, Object>> getAvailability(
            @PathVariable String user,
            @PathVariable Long doctorId,
            @PathVariable String date,
            @PathVariable String token
    ) {
        Map<String, Object> response = new HashMap<>();
        
        if (!doctorService.validateToken(token)) {
            response.put("success", false);
            response.put("message", "Invalid or expired token");
            response.put("user", user);
            return ResponseEntity.status(401).body(response);
        }
        
        try {
            LocalDate localDate = LocalDate.parse(date);
            response.put("success", true);
            response.put("user", user);
            response.put("doctorId", doctorId);
            response.put("date", date);
            response.put("availableSlots", doctorService.getAvailableSlots(doctorId, localDate));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Invalid date format. Use YYYY-MM-DD");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/api/doctors")
    public ResponseEntity<Map<String, Object>> listDoctors(@RequestParam(required = false) String speciality) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (speciality == null || speciality.isBlank()) {
                response.put("success", true);
                response.put("data", doctorRepository.findAll());
                response.put("count", doctorRepository.count());
            } else {
                response.put("success", true);
                response.put("speciality", speciality);
                response.put("data", doctorRepository.findBySpecialityIgnoreCase(speciality));
                response.put("count", doctorRepository.countBySpecialityIgnoreCase(speciality));
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error retrieving doctors");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/api/doctors/{user}/list")
    public ResponseEntity<Map<String, Object>> listDoctorsByUser(
            @PathVariable String user,
            @RequestParam(required = false) String speciality
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (speciality == null || speciality.isBlank()) {
                response.put("success", true);
                response.put("user", user);
                response.put("data", doctorRepository.findAll());
                response.put("count", doctorRepository.count());
            } else {
                response.put("success", true);
                response.put("user", user);
                response.put("speciality", speciality);
                response.put("data", doctorRepository.findBySpecialityIgnoreCase(speciality));
                response.put("count", doctorRepository.countBySpecialityIgnoreCase(speciality));
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error retrieving doctors");
            response.put("error", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
