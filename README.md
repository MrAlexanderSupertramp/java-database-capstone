# java-database-capstone

## Demo Pages (served from /tmp on port 9000)

Base URL (codespace forwarded): https://supreme-fortnight-q9xvvg94r99cvvw-9000.app.github.dev/

Append the following paths to the base URL to open each demo page in your browser:

- Admin login: https://supreme-fortnight-q9xvvg94r99cvvw-9000.app.github.dev/admin-login.html
- Doctor login: https://supreme-fortnight-q9xvvg94r99cvvw-9000.app.github.dev/doctor-login.html
- Patient login: https://supreme-fortnight-q9xvvg94r99cvvw-9000.app.github.dev/patient-login.html
- Admin — Add doctor: https://supreme-fortnight-q9xvvg94r99cvvw-9000.app.github.dev/admin-add-doctor.html
- Patient — Search doctors: https://supreme-fortnight-q9xvvg94r99cvvw-9000.app.github.dev/patient-search.html
- Doctor — View appointments: https://supreme-fortnight-q9xvvg94r99cvvw-9000.app.github.dev/doctor-appointments.html

If the pages are not visible, start the local HTTP server (serves files from `/tmp`) in the codespace workspace with:

```bash
python3 -m http.server 9000 --directory /tmp
```

Then open the URLs above in your browser. The backend API is available at `http://localhost:8080` inside the codespace; use the endpoints like `/api/doctors` and `/api/patients/1/appointments` if you need API responses.