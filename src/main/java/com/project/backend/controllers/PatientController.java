package com.project.backend.controllers;

import com.project.backend.repositories.AppointmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {
    private final AppointmentRepository appointmentRepository;

    public PatientController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("/api/patients/{id}/appointments")
    public ResponseEntity<?> getPatientAppointments(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentRepository.findByPatientId(id));
    }
}
