# MySQL Schema Design

Tables:

- `Doctor` (id PK, name, speciality)
- `Patient` (id PK, name, email, phoneNumber)
- `Appointment` (id PK, doctor_id FK, patient_id FK, appointmentTime)
- `Prescription` (id PK, appointment_id FK, medication)

Relationships:
- `Appointment.doctor_id` -> `Doctor.id`
- `Appointment.patient_id` -> `Patient.id`
