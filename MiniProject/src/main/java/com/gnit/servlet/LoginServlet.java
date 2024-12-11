package com.gnit.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.gnit.DatabaseFile.DbConnection;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(true);

        // Store the username in the session
        session.setAttribute("username", username);

        // Check if the request is for face login
        String faceLogin = request.getParameter("faceLogin");

        // Authentication logic for username and password
        if ("true".equals(faceLogin)) {
            // Redirect to face login URL
            response.sendRedirect("http://localhost:5173/user-select");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish a database connection
            conn = DbConnection.getConnection();

            // Prepare the SQL query to validate the user
            String query = "SELECT * FROM user WHERE user_Name = ? AND password = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            // Check if any record matches the username and password
            if (rs.next()) {
                // Redirect to welcome.jsp after successful login
                response.sendRedirect("welcome.jsp");
            } else {
                // Redirect back to login.jsp with an error
                response.sendRedirect("login.jsp?error=1");
                System.out.println("Error! Please enter the correct username and password.");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error during database authentication", e);
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
