package com.gnit.DatabaseFile;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/DbConnectionTest")
public class DbConnectionTestServlet extends HttpServlet {

    // Handling GET request
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleDatabaseUpdate(request, response);
    }

    // Handling POST request
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleDatabaseUpdate(request, response);
    }

    // This method handles the database update logic for both GET and POST requests
    private void handleDatabaseUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Establish the database connection
            conn = DbConnection.getConnection();
            if (conn != null) {
                // Turn off safe update mode
                String turnOffSafeUpdate = "SET SQL_SAFE_UPDATES = 0";
                pstmt = conn.prepareStatement(turnOffSafeUpdate);
                pstmt.executeUpdate();

                // Update availability date
                String updateAvailability = "UPDATE availability SET available_date = CURDATE()";
                pstmt = conn.prepareStatement(updateAvailability);
                pstmt.executeUpdate();

                // Update status column to 'Available'
                String updateStatus = "UPDATE availability SET status = 'Available'";
                pstmt = conn.prepareStatement(updateStatus);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            // Handle SQL exceptions if necessary
            e.printStackTrace();
        } catch (Exception e) {
            // Handle other exceptions if necessary
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Redirect to the faculty-availability.jsp page after the update
        response.sendRedirect("faculty-availability.jsp");
    }
}
