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

## Technologies Used

- **Backend**: Java, Servlets, JSP
- **Database**: MySQL
- **Email**: Jakarta Mail
- **Frontend**: HTML, CSS, JavaScript

## Database Structure

The system uses two primary tables in **MySQL**:

1. **`faculty_allocation`**:
   - Contains faculty details such as name, room allocation, and other metadata.

2. **`room_details`**:
   - Stores information about available rooms (block, floor, room number, room code).

## How It Works

1. **Faculty Allocation**:
   - Faculty members are added to the system and are randomly assigned rooms.
   - A servlet processes the faculty data and allocates them to available rooms using a genetic algorithm (if applicable) or a randomization logic.

2. **Room Management**:
   - Rooms are pre-defined in the database and can be fetched dynamically to assign faculty.
   - Room codes (block + floor + room number) ensure unique identification for each room.

3. **Email Notification**:
   - After allocation, emails are sent to each faculty member with the allocation details.
   - The email includes exam date, room number, block, floor, and other important details.

## Email Configuration

The system uses **Jakarta Mail** to send email notifications to faculty members. To set up email notifications, you need to configure your Gmail SMTP server credentials, including the **secret key**.

### Steps to Configure Gmail Credentials:

1. **Update the NotificationServlet**:
   - Open the `NotificationServlet.java` file in your project.
   - Locate the part of the code that handles email sending (likely using `Session.getDefaultInstance()` for mail properties).
   - Update the Gmail credentials (SMTP settings) with your **Gmail secret key** and other necessary credentials.

2. **Generate Gmail Secret Key**:
   - If you haven’t done so already, generate an **App Password** for your Gmail account by following these steps:
     - Go to your **Google Account**: https://myaccount.google.com/
     - Navigate to **Security**.
     - Under **App passwords**, generate a password for your application.
     - Use this generated **App Password** instead of your Gmail account password.

3. **Update the Email Configuration** in the `NotificationServlet`:
   - Modify the `email.properties` or the corresponding section in the `NotificationServlet` to use your Gmail SMTP server details:
   
   ```java
   String host = "smtp.gmail.com";
   String port = "587";  // or 465 if using SSL
   String username = "your_email@gmail.com";
   String password = "your_app_password";  // Use the Gmail App Password here

   Properties props = new Properties();
   props.put("mail.smtp.host", host);
   props.put("mail.smtp.port", port);
   props.put("mail.smtp.auth", "true");
   props.put("mail.smtp.starttls.enable", "true");

   Session session = Session.getInstance(props, new Authenticator() {
       @Override
       protected PasswordAuthentication getPasswordAuthentication() {
           return new PasswordAuthentication(username, password);
       }
   });
## Contributions

We welcome contributions from the community! Whether it's a bug fix, new feature, or improvement, your help is appreciated. Here's how you can contribute:

1. **Fork the Repository**  
   Click on the "Fork" button at the top right of this repository to create a copy in your GitHub account.

2. **Clone the Repository**  
   Clone your forked repository to your local system:
   ```bash
   git clone https://github.com/SubediBinod/exam-duty-allocation-system.git
   ```

3. **Create a New Branch**  
   Use a meaningful name for your branch to describe the feature or issue:
   ```bash
   git checkout -b feature-name
   ```

4. **Make Changes**  
   Implement your changes and commit them:
   ```bash
   git add .
   git commit -m "Brief description of your changes"
   ```

5. **Push Changes**  
   Push your changes to your forked repository:
   ```bash
   git push origin feature-name
   ```

6. **Submit a Pull Request**  
   Go to the original repository and submit a pull request explaining your changes.

Contributions to improve functionality or enhance features are welcome. For major changes, please open an issue to discuss your ideas first.

