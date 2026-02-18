# User Stories

## Doctor User Stories

### Story 1: View Daily Schedule
**Title:** View Daily Appointments  
**Priority:** High  
**Story Points:** 5  
**Description:** As a Doctor, I want to view my daily schedule so that I can manage appointments effectively.

**Acceptance Criteria:**
- Doctor can log in with email and password
- Dashboard displays all appointments for the current date
- Each appointment shows patient name, time, and duration
- Appointments are sorted chronologically
- Doctor can navigate to different dates using a date picker
- Mobile-responsive design for viewing on tablets

**Definition of Done:**
- Code reviewed and approved
- Unit tests cover all appointment retrieval scenarios
- API endpoint `/api/doctors/{doctorId}/appointments/{date}` returns JSON
- No sensitive patient data is exposed in the response

---

### Story 2: Update Availability Times
**Title:** Manage Schedule Availability  
**Priority:** High  
**Story Points:** 5  
**Description:** As a Doctor, I want to update my availability times so patients can book appropriate slots.

**Acceptance Criteria:**
- Doctor can select available time slots for each day of the week
- Available slots are displayed in 1-hour increments (e.g., 9:00 AM, 10:00 AM)
- Doctor can set weekly recurring availability or customize per date
- System prevents overlapping time slots
- Doctor can remove availability for vacation/sick days
- Changes take effect immediately in the system

**Definition of Done:**
- Database schema updated to store availability
- API endpoint `/api/doctors/{doctorId}/availability` supports PUT requests
- Validation prevents past dates from being modified
- Audit log tracks all availability changes

---

### Story 3: View Patient Medical History
**Title:** Access Patient Records  
**Priority:** Medium  
**Story Points:** 8  
**Description:** As a Doctor, I want to view patient medical history so I can provide informed care.

**Acceptance Criteria:**
- Doctor can search for a patient by name or ID
- Patient profile displays contact information
- Complete list of past appointments with notes is visible
- Previous prescriptions are displayed with dates
- Doctor can add notes to the current appointment
- System displays medication allergies and contraindications
- HIPAA compliance is maintained with encryption

**Definition of Done:**
- Patient data is encrypted at rest
- Audit trail logs all data access for compliance
- API enforces authentication and authorization
- Patient consent for data access is verified

---

### Story 4: Generate Appointment Reports
**Title:** Export Appointment Statistics  
**Priority:** Medium  
**Story Points:** 5  
**Description:** As a Doctor, I want to view my appointment statistics so I can track productivity.

**Acceptance Criteria:**
- Doctor can view appointment count and duration statistics
- Reports show no-show rates and cancellation rates
- Data can be filtered by date range
- Reports can be exported as PDF or CSV
- Dashboard displays charts for visual analysis
- Doctor can compare this month to previous months

**Definition of Done:**
- Stored procedure or efficient query for report generation
- PDF export includes doctor name, clinic logo, and report period
- CSV format is compatible with Excel and Google Sheets
- Performance tested for reports covering 1 year+ of data

---

### Story 5: Receive Appointment Reminders
**Title:** Appointment Notification System  
**Priority:** Low  
**Story Points:** 3  
**Description:** As a Doctor, I want to receive reminders for upcoming appointments so I don't miss them.

**Acceptance Criteria:**
- Doctor receives email notification 24 hours before appointment
- Doctor receives SMS reminder 2 hours before appointment
- Reminders can be customized per doctor preference
- Doctor can mute notifications for specific dates
- System resends reminder if appointment is modified
- Reminder includes patient name and location

**Definition of Done:**
- Email and SMS templates defined
- Notification service tested with mock providers
- Timezone handling verified for international doctors
- Unsubscribe option available in all notifications

---

## Patient User Stories

### Story 1: Search Doctors by Speciality
**Title:** Find Specialists  
**Priority:** High  
**Story Points:** 5  
**Description:** As a Patient, I want to search doctors by speciality so I can find the right healthcare provider.

**Acceptance Criteria:**
- Search field filters doctors by speciality (Cardiology, Dermatology, etc.)
- Results display doctor name, speciality, and rating
- Patient can sort results by rating, experience, or distance
- Available appointment slots are shown for each doctor
- Doctor profile includes education and certifications
- Search results include average wait time

**Definition of Done:**
- API endpoint `/api/doctors?speciality=Cardiology` implemented
- Full-text search optimized for performance
- Search results cached for frequently accessed specialities
- Mobile UI responsive and touch-friendly

---

### Story 2: Book Appointments Online
**Title:** Schedule Appointment  
**Priority:** High  
**Story Points:** 8  
**Description:** As a Patient, I want to book appointments online so I don't need to call the clinic.

**Acceptance Criteria:**
- Patient can select a doctor and view available times
- Booking confirmation email is sent immediately
- Patient receives appointment details (date, time, location, doctor name)
- Booking can be cancelled up to 24 hours before appointment
- System prevents double-booking same time slot
- Appointment reminder email sent 24 hours before

**Definition of Done:**
- POST `/api/patients/{patientId}/appointments` endpoint created
- Transaction handling ensures atomic booking operations
- Database constraints prevent overbooking
- Email service integration tested with mock provider
- Calendar integration (Google Calendar, Outlook) supported

---

### Story 3: View and Manage Prescriptions
**Title:** Access Prescribed Medications  
**Priority:** High  
**Story Points:** 5  
**Description:** As a Patient, I want to view my prescriptions so I can access medication information and refill requests.

**Acceptance Criteria:**
- Patient can see all current and past prescriptions
- Each prescription shows medication name, dosage, and duration
- Patient can request prescription refills online
- Refill requests are sent to the prescribing doctor
- Patient receives notification when prescription is ready
- Prescription can be printed or sent to pharmacy

**Definition of Done:**
- GET `/api/patients/{patientId}/prescriptions` endpoint returns list
- Prescription status tracking (Active, Expired, Filled)
- Integration with pharmacy management system
- HIPAA-compliant handling of sensitive health data

---

### Story 4: Manage Health Records
**Title:** Maintain Medical History  
**Priority:** Medium  
**Story Points:** 5  
**Description:** As a Patient, I want to manage my health records so I can keep updates synchronized.

**Acceptance Criteria:**
- Patient can upload vaccination records
- Allergy information can be updated by patient
- Emergency contact information can be managed
- Medical insurance details can be added
- Patient can view all doctors who have accessed their records
- Patient can grant and revoke access to specific doctors

**Definition of Done:**
- File upload with virus scanning
- Data validation for format and content
- Audit log for all record access and modifications
- Encryption for all sensitive data

---

### Story 5: Provide Feedback and Ratings
**Title:** Rate Doctor and Appointment Experience  
**Priority:** Low  
**Story Points:** 3  
**Description:** As a Patient, I want to rate doctors and provide feedback so other patients can make informed choices.

**Acceptance Criteria:**
- Patient can rate doctor on 1-5 star scale after appointment
- Written review field (optional) with character limit
- Rating reflects appointment experience, not just doctor quality
- Ratings are aggregated and displayed on doctor profile
- Patient can update or delete their own review
- Inappropriate reviews are moderated by admin
- Average rating is prominently displayed

**Definition of Done:**
- POST `/api/patients/{patientId}/ratings` endpoint for new ratings
- PUT endpoint for updating existing ratings
- Review moderation workflow with admin approval
- Verification that review author had actual appointment
- Filtering for helpful/unhelpful review votes

---

## Admin User Stories

### Story 1: Add and Remove Doctors
**Title:** Manage Doctor Registry  
**Priority:** High  
**Story Points:** 5  
**Description:** As an Admin, I want to add and remove doctors so the system reflects staffing changes.

**Acceptance Criteria:**
- Admin can add new doctor with name, speciality, and credentials
- Doctor email must be unique in the system
- Doctors can be marked as inactive instead of deleted
- Bulk import of doctors via CSV file is supported
- System validates medical license numbers
- Doctor removal triggers notification to patients with pending appointments
- Audit log records all doctor additions and removals

**Definition of Done:**
- POST `/api/admin/doctors` endpoint for adding doctors
- DELETE `/api/admin/doctors/{doctorId}` endpoint for removal
- CSV import with error reporting for invalid records
- Email notification service tested
- Database constraints enforce data integrity

---

### Story 2: View Appointment Reports
**Title:** Generate Clinic Reports  
**Priority:** High  
**Story Points:** 8  
**Description:** As an Admin, I want to view appointment reports so I can manage clinic operations effectively.

**Acceptance Criteria:**
- Admin can view total appointments by date range
- Reports show no-show and cancellation rates
- Data can be filtered by doctor, patient, or speciality
- Reports display revenue metrics and financial summaries
- Peak hours and trends are visualized in charts
- Reports can be exported as PDF or Excel
- Ability to drill down into individual appointment details

**Definition of Done:**
- Stored procedures optimized for large datasets
- Reports generated in under 5 seconds for 1-year data
- Dashboard displays key metrics with real-time updates
- PDF/Excel export formatting professional and complete
- Scheduled report generation via cron job

---

### Story 3: Manage System Users
**Title:** Control User Access  
**Priority:** High  
**Story Points:** 5  
**Description:** As an Admin, I want to manage system users so I can control who can access the clinic system.

**Acceptance Criteria:**
- Admin can create user accounts for doctors and staff
- Admin can assign roles (Doctor, Patient, Admin, Staff)
- Password reset functionality available for locked accounts
- Two-factor authentication can be enabled for users
- Inactive accounts can be deactivated without deletion
- Login audit log tracks all user access
- Admin can set session timeout policies

**Definition of Done:**
- POST `/api/admin/users` endpoint for user creation
- Role-based access control (RBAC) implemented
- Password policy enforced (min 8 chars, complexity)
- OAuth/SSO integration available for enterprise use
- Audit log stored in tamper-proof database

---

### Story 4: Configure Clinic Settings
**Title:** Manage Clinic Configuration  
**Priority:** Medium  
**Story Points:** 5  
**Description:** As an Admin, I want to configure clinic settings so operations run smoothly.

**Acceptance Criteria:**
- Admin can set appointment slot duration (15, 30, 60 minutes)
- Clinic hours can be configured per day of the week
- Holiday dates can be added to prevent bookings
- Email templates can be customized
- SMS provider settings can be configured
- Payment method settings can be updated
- System notifications can be toggled on/off

**Definition of Done:**
- Settings stored in database with version history
- UI for editing settings without code changes
- Settings applied immediately without restart
- Configuration exported for backup purposes
- Changes logged with timestamp and admin user

---

### Story 5: Send Bulk Notifications
**Title:** Clinic Communication  
**Priority:** Low  
**Story Points:** 5  
**Description:** As an Admin, I want to send bulk notifications to patients or doctors so I can communicate important announcements.

**Acceptance Criteria:**
- Admin can create notification message for doctors or patients
- Notifications can be sent via email, SMS, or in-app
- Recipients can be filtered by speciality, status, or date range
- Notification scheduling allows delayed sending
- Admin can preview message before sending
- Delivery status and read receipts tracked
- Unsubscribe option provided for recipients

**Definition of Done:**
- Notification queue system with retry logic
- Email and SMS templates customizable
- Delivery status dashboard for admin monitoring
- Opt-out preferences respected for all users
- Bulk notification tested with 10,000+ recipients
