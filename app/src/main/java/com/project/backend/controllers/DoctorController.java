package com.project.backend.controllers;

import com.project.backend.services.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) { this.doctorService = doctorService; }

    @GetMapping("/api/doctors/availability")
    public ResponseEntity<?> getAvailability(
            @RequestHeader(name = "Authorization", required = false) String token,
            @RequestParam Long doctorId,
            @RequestParam String date
    ) {
        if (!doctorService.validateToken(token)) {
            return ResponseEntity.status(401).body("Invalid token");
        }
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(doctorService.getAvailableSlots(doctorId, localDate));
    }
}
