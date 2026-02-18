package com.project.backend.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    public boolean validateToken(String token) {
        return token != null && token.startsWith("Bearer ");
    }

    public List<String> getAvailableSlots(Long doctorId, LocalDate date) {
        // placeholder implementation returning example slots
        List<String> slots = new ArrayList<>();
        slots.add(date.atTime(9,0).toString());
        slots.add(date.atTime(10,0).toString());
        return slots;
    }

    public boolean validateDoctorLogin(String email, String password) {
        // placeholder: in real app validate against DB
        return email != null && password != null && password.length() > 3;
    }
}
