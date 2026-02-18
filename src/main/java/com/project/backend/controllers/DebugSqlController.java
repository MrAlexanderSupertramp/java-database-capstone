package com.project.backend.controllers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DebugSqlController {
    private final JdbcTemplate jdbc;

    public DebugSqlController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/debug/show-tables")
    public List<Map<String, Object>> showTables() {
        return jdbc.queryForList("SHOW TABLES");
    }

    @GetMapping("/debug/patients")
    public List<Map<String, Object>> patients(@RequestParam(defaultValue = "5") int limit) {
        return jdbc.queryForList("SELECT * FROM PATIENT LIMIT " + limit);
    }

    @GetMapping("/debug/daily-appointment-report")
    public List<Map<String, Object>> dailyAppointmentReport(@RequestParam(required = false) String date) {
        // date not used; use current date in SQL
        String sql = "SELECT d.id AS doctor_id, d.name AS doctor_name, CAST(a.appointment_time AS DATE) AS appt_date, COUNT(*) AS appt_count " +
                "FROM appointment a JOIN doctor d ON a.doctor_id = d.id " +
                "WHERE CAST(a.appointment_time AS DATE) = CURRENT_DATE " +
                "GROUP BY d.id, CAST(a.appointment_time AS DATE)";
        return jdbc.queryForList(sql);
    }

    @GetMapping("/debug/doctor-most-patients-by-month")
    public Map<String, Object> doctorWithMostPatientsByMonth(@RequestParam(required = false) String month) {
        // month expected in YYYY-MM, default to current month
        String[] parts = null;
        int m = 0, y = 0;
        if (month != null && month.contains("-")) {
            parts = month.split("-");
            y = Integer.parseInt(parts[0]);
            m = Integer.parseInt(parts[1]);
        } else {
            // fallback to current month/year via H2 functions
            String sql = "SELECT d.id AS doctor_id, d.name AS doctor_name, COUNT(DISTINCT a.patient_id) AS patient_count " +
                    "FROM appointment a JOIN doctor d ON a.doctor_id = d.id " +
                    "WHERE MONTH(a.appointment_time) = MONTH(CURRENT_DATE) AND YEAR(a.appointment_time) = YEAR(CURRENT_DATE) " +
                    "GROUP BY d.id ORDER BY patient_count DESC LIMIT 1";
            List<Map<String, Object>> r = jdbc.queryForList(sql);
            if (r.isEmpty()) return new HashMap<>();
            return r.get(0);
        }

        String sql = String.format("SELECT d.id AS doctor_id, d.name AS doctor_name, COUNT(DISTINCT a.patient_id) AS patient_count FROM appointment a JOIN doctor d ON a.doctor_id = d.id WHERE MONTH(a.appointment_time) = %d AND YEAR(a.appointment_time) = %d GROUP BY d.id ORDER BY patient_count DESC LIMIT 1", m, y);
        List<Map<String, Object>> r = jdbc.queryForList(sql);
        if (r.isEmpty()) return new HashMap<>();
        return r.get(0);
    }
}
