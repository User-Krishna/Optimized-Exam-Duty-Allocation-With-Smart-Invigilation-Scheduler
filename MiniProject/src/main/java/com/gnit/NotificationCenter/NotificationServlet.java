package com.gnit.NotificationCenter;

import com.gnit.DatabaseFile.DbConnection;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String SMTP_USER = "dasp49083@gmail.com";
    private static final String SMTP_PASS = "rynvyuqshmsppkdf";

    private static final String SELECT_FACULTY_QUERY = 
        "SELECT fa.room_number, fa.duration, fa.timing, fa.faculty_id, f.name, f.email " +
        "FROM faculty_allocation fa " +
        "JOIN faculty f ON fa.faculty_id = f.id " +
        "WHERE fa.archived = 0";
        
    private static final String SELECT_ROOM_DETAILS_QUERY = 
        "SELECT block, floor FROM room_details WHERE room_code = ?";

    private static final String UPDATE_ARCHIVED_QUERY = 
        "UPDATE faculty_allocation SET archived = 1 WHERE faculty_id = ? AND room_number = ?";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement psFaculty = null;
        PreparedStatement psRoom = null;
        PreparedStatement psArchive = null;
        ResultSet rsFaculty = null;
        ResultSet rsRoom = null;

        // Get user-provided values
        String examDate = request.getParameter("exam_date");
        String examTime = request.getParameter("exam_time");
        String messageTemplate = request.getParameter("message");

        try {
            conn = DbConnection.getConnection();

            psFaculty = conn.prepareStatement(SELECT_FACULTY_QUERY);
            rsFaculty = psFaculty.executeQuery();

            while (rsFaculty.next()) {
                String roomCode = rsFaculty.getString("room_number");
                int duration = rsFaculty.getInt("duration");
                String timing = rsFaculty.getString("timing"); // Current time value from database
                String facultyName = rsFaculty.getString("name");
                String email = rsFaculty.getString("email");
                int facultyId = rsFaculty.getInt("faculty_id");

                // Format timing as '5:57 PM' if not provided by user
                SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
                String formattedTiming = (examTime != null && !examTime.isEmpty()) ? examTime : timeFormat.format(rsFaculty.getTime("timing"));

                // Format duration as '1 hour' or '1 hour 30 minutes'
                String formattedDuration = formatDuration(duration);

                psRoom = conn.prepareStatement(SELECT_ROOM_DETAILS_QUERY);
                psRoom.setString(1, roomCode);
                rsRoom = psRoom.executeQuery();

                String block = "";
                int floor = 0;

                if (rsRoom.next()) {
                    block = rsRoom.getString("block");
                    floor = rsRoom.getInt("floor");
                }

                // Replace placeholders in the message template
                String emailContent = messageTemplate
                        .replace("[Faculty Name]", facultyName)
                        .replace("[exam_date]", examDate)
                        .replace("[room_number]", roomCode)
                        .replace("[block]", block)
                        .replace("[floor]", String.valueOf(floor))
                        .replace("[exam_time]", formattedTiming)
                        .replace("[duration]", formattedDuration);

                // Format the email content with HTML tags
                String htmlContent = String.format(
                    "<p>Dear %s,</p>" +
                    "<p>This is a notification regarding your Exam invigilator duty allocation:</p>" +
                    "<p>Exam Date: %s<br>" +
                    "Room Number: %s<br>" +
                    "Block: %s<br>" +
                    "Floor: %d<br>" +
                    "Timing: %s<br>" +
                    "Duration: %s</p>" +
                    "<p>Best regards,<br>" +
                    "Faculty Management Team</p>",
                    facultyName, examDate, roomCode, block, floor, formattedTiming, formattedDuration
                );

                // Send the email
                sendEmail(email, "Faculty Allocation Notification", htmlContent);

                // Archive the allocation after the email is sent
                psArchive = conn.prepareStatement(UPDATE_ARCHIVED_QUERY);
                psArchive.setInt(1, facultyId);
                psArchive.setString(2, roomCode);
                psArchive.executeUpdate();
            }

            // Redirect to a success page after processing
            response.sendRedirect("notification_success.jsp");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Redirect to an error page or show an error message
            response.sendRedirect("notification_error.jsp");
        } finally {
            try {
                if (rsFaculty != null) rsFaculty.close();
                if (rsRoom != null) rsRoom.close();
                if (psFaculty != null) psFaculty.close();
                if (psRoom != null) psRoom.close();
                if (psArchive != null) psArchive.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String formatDuration(int duration) {
        int hours = duration / 60;
        int minutes = duration % 60;
        if (hours > 0 && minutes > 0) {
            return hours + " hour " + minutes + " minutes";
        } else if (hours > 0) {
            return hours + " hour";
        } else {
            return minutes + " minutes";
        }
    }

    private void sendEmail(String to, String subject, String content) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USER, SMTP_PASS);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_USER));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(content, "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("Email sent successfully to " + to);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
