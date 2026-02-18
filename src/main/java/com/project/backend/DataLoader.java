package com.project.backend;

import com.project.backend.models.Appointment;
import com.project.backend.models.Doctor;
import com.project.backend.models.Patient;
import com.project.backend.repositories.AppointmentRepository;
import com.project.backend.repositories.DoctorRepository;
import com.project.backend.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner loadData(DoctorRepository doctorRepository, PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        return args -> {
            Doctor d1 = new Doctor();
            d1.setName("Dr. Alice");
            d1.setSpeciality("Cardiology");
            doctorRepository.save(d1);

            Doctor d2 = new Doctor();
            d2.setName("Dr. Bob");
            d2.setSpeciality("Dermatology");
            doctorRepository.save(d2);

            Patient p1 = new Patient();
            p1.setName("John Doe");
            p1.setEmail("john@example.com");
            p1.setPhoneNumber("1234567890");
            patientRepository.save(p1);

            Appointment a1 = new Appointment();
            a1.setDoctor(d1);
            a1.setPatient(p1);
            a1.setAppointmentTime(LocalDateTime.now().plusDays(1).withHour(9).withMinute(0));
            appointmentRepository.save(a1);

            Appointment a2 = new Appointment();
            a2.setDoctor(d2);
            a2.setPatient(p1);
            a2.setAppointmentTime(LocalDateTime.now().plusDays(2).withHour(11).withMinute(0));
            appointmentRepository.save(a2);
        };
    }
}
