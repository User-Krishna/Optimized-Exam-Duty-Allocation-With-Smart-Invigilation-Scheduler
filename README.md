# Exam Duty Allocation System

## Overview
The **Exam Duty Allocation System** is a web-based application designed to automate the process of allocating faculty members for exam invigilation. The system allows HODs and Exam Administrators to log in (using username/password or face recognition) and perform various tasks, such as checking faculty availability, managing faculty details, and sending email notifications regarding exam duties.

---

## Features
- **HOD Login**: Secure login with username/password or face recognition.
- **Exam Administrator Login**: Similar secure login options.
- **Faculty Management**: Search, edit, and delete faculty details.
- **Faculty Availability**: Mark faculty as on leave, busy, or available.
- **Automated Allocation**: Random allocation of available faculty for invigilation duties.
- **PDF Report Generation**: Generate detailed reports of faculty availability and allocation.
- **Email Notifications**: Notify faculty about their assigned duties with automated emails.

---

## Technology Stack
- **Backend**: Java Servlets
- **Frontend**: HTML, CSS, JavaScript
- **Database**: MySQL
- **Libraries/Tools**:
  - OpenCV for face recognition
  - Apache PDFBox for PDF generation
- **Server**: Apache Tomcat 10.1.28

---

## Setup Instructions
### Prerequisites
1. Java Development Kit (JDK 17+)
2. Apache Tomcat Server (10.1.28 or compatible)
3. MySQL Database
4. Maven for dependency management

### Steps to Set Up
<h2> Clone the repository:</h2>
   ```bash
   git clone https://github.com/your-username/exam-duty-allocation.git
   cd exam-duty-allocation
