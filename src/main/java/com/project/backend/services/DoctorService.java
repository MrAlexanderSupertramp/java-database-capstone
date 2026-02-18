package com.project.backend.services;

import com.project.backend.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    
    public boolean validateToken(String token) {
        return token != null && !token.isBlank() && token.startsWith("Bearer ");
    }

    public List<String> getAvailableSlots(Long doctorId, LocalDate date) {
        List<String> slots = new ArrayList<>();
        
        // Validate doctorId and date
        if (doctorId == null || doctorId <= 0 || date == null) {
            return slots;
        }
        
        // Check if doctor exists
        if (doctorRepository.findById(doctorId).isEmpty()) {
            return slots;
        }
        
        // Generate available time slots: 9 AM to 5 PM in 1-hour intervals
        for (int hour = 9; hour < 17; hour++) {
            LocalDateTime slotTime = LocalDateTime.of(date, LocalTime.of(hour, 0));
            // Filter: only show future times
            if (slotTime.isAfter(LocalDateTime.now())) {
                slots.add(slotTime.toString());
            }
        }
        
        return slots;
    }

    public Map<String, Object> validateDoctorLogin(String email, String password) {
        Map<String, Object> result = new HashMap<>();
        
        // Validate inputs
        if (email == null || email.isBlank()) {
            result.put("valid", false);
            result.put("message", "Email is required");
            return result;
        }
        
        if (password == null || password.isBlank()) {
            result.put("valid", false);
            result.put("message", "Password is required");
            return result;
        }
        
        if (password.length() < 6) {
            result.put("valid", false);
            result.put("message", "Password must be at least 6 characters");
            return result;
        }
        
        // In a real application, check credentials against database
        // For now, basic validation
        boolean isValid = email.contains("@") && password.length() >= 6;
        
        result.put("valid", isValid);
        result.put("message", isValid ? "Login successful" : "Invalid credentials");
        result.put("email", email);
        
        return result;
    }
}
