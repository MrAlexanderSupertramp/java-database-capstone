package com.project.backend.services;

import com.project.backend.models.Appointment;
import com.project.backend.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment bookAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsForDoctorOnDate(Long doctorId, LocalDate date) {
        return appointmentRepository.findByDoctorIdAndAppointmentTimeBetween(
                doctorId,
                date.atStartOfDay(),
                date.plusDays(1).atStartOfDay()
        );
    }
}
