# 🎓 Exam Duty Allocation System

## 📌 Overview
The **Exam Duty Allocation System** is a web-based application designed to **automate the allocation of faculty members for invigilation duties**. It allows **HODs and Exam Administrators** to log in securely (via **username/password or face recognition**) and efficiently manage faculty availability, duty assignments, and notifications.

---

## ✨ Features
✅ **HOD & Exam Admin Login** – Secure login via username/password or **Face Recognition**.  
✅ **Faculty Management** – Add, search, edit, and delete faculty records.  
✅ **Faculty Availability** – Mark faculty as **Available, On Leave, or Busy**.  
✅ **Automated Allocation** – Smart/randomized allocation of faculty for invigilation duties.  
✅ **PDF Report Generation** – Generate detailed reports of allocations.  
✅ **Email Notifications** – Automated emails notify faculty of their assigned duties.  

---
## 📸 Screenshots & UI Preview

Here are the **screenshots** of the application showcasing various features and UI components.

### 🏠 Index Page
![Index Page](https://i.imgur.com/9iDLQJo.png)

### 🔐 Login System
**1️⃣ Login with ID**  
![Login with ID](https://i.imgur.com/uSXb364.png)

**2️⃣ Login with Face Recognition**  
![Login with Face](https://i.imgur.com/BvViM4z.png)

**3️⃣ Result after Face Recognition Login**  
![Face Login Result](https://i.imgur.com/0PbtecU.png)

### 🌍 Landing Page
![Landing Page](https://i.imgur.com/AAy19ws.png)

### 🏫 Faculty Management
![Faculty Management](https://i.imgur.com/26Yyo0J.png)

### 📋 Faculty Availability
![Faculty Availability](https://i.imgur.com/OgVmaEP.png)

### 🔄 Faculty Allocation
![Faculty Allocation](https://i.imgur.com/ihLHZKh.png)

### 📩 Notification Center
![Notification Center](https://i.imgur.com/4YNJxw9.png)

### ✉️ Final Email Result
![Final Email Result](https://i.imgur.com/9hhfprx.png)

## 🛠️ Technology Stack
| Component      | Technology Used         |
|---------------|------------------------|
| **Backend**   | Java Servlets, JSP      |
| **Frontend**  | HTML, CSS, JavaScript   |
| **Database**  | MySQL                   |
| **Face Recognition** | OpenCV         |
| **PDF Generation** | Apache PDFBox   |
| **Email Service** | Jakarta Mail      |
| **Server**    | Apache Tomcat 10.1.28  |

---

## 📂 Database Structure

The system uses two primary **MySQL tables**:

1. **`faculty_allocation`** – Stores faculty details (name, assigned room, availability status).  
2. **`room_details`** – Stores exam room details (block, floor, room number, room code).

---

## 🚀 How It Works

### 🏫 Faculty Allocation
- Faculty members are added and randomly assigned rooms.
- A **servlet processes faculty data** and allocates them dynamically.

### 📌 Room Management
- Pre-defined rooms in the database are dynamically fetched for assignments.
- Unique room codes ensure proper mapping.

### 📧 Email Notification
- After allocation, **automated emails** are sent to faculty members.
- Emails include **exam date, room number, block, floor, and additional details**.

---

## 📧 Email Configuration (Jakarta Mail)

To configure **email notifications**, follow these steps:

1. **Update the `NotificationServlet.java` file**:
   - Locate the email configuration section.
   - Update the **Gmail SMTP settings** with your **App Password**.

2. **Generate Gmail Secret Key**:
   - Go to **[Google Account Security](https://myaccount.google.com/)**.
   - Navigate to **App Passwords** and generate a password.
   - Use this **App Password** instead of your regular Gmail password.

3. **Modify `NotificationServlet` Configuration**:

```java
String host = "smtp.gmail.com";
String port = "587";  // or 465 for SSL
String username = "your_email@gmail.com";
String password = "your_app_password";  // Use Gmail App Password

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
```
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

