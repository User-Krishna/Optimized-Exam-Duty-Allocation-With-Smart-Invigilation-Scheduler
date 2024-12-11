<%@ page import="com.gnit.servlet.FacultyAllocationServlet.Allocation" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.*, com.gnit.DatabaseFile.DbConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Faculty Allocation Results</title>
        <link rel="stylesheet" type="text/css" href="CSS/display-allocations.css">
        <style>
        body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background: rgba(248, 249, 250, 0.8);
    background-image: url('images/5.jpg');
    background-size: cover;
    background-position: center;
    background-attachment: fixed;
}


table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

th, td {
    padding: 10px;
    border: 1px solid #ddd;
    text-align: center;
}

th {
    background-color: #49243E;
    color: white;
}

tr:nth-child(even) {
    background-color: #f2f2f2;
}

.container {
    max-width: 1000px;
     margin: 0 auto;
    margin-top: 100px;
    background: rgba(255, 255, 255, 0.9); /* White background with 90% opacity */
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.edit-form {
    display: inline-flex;
    align-items: center;
    gap: 5px;
}

.edit-form input {
    width: 100px;
    padding: 5px;
    text-align: center;
}

.edit-form button {
    background: url('edit-icon.png') no-repeat center;
    background-size: 20px 20px;
    border: none;
    cursor: pointer;
    padding: 0;
}

.edit-form button:focus {
    outline: none;
}

.error {
    color: red;
}

.success {
    color: green;
}

.buttons {
    margin-top: 20px;
}

.button {
    display: inline-block;
    padding: 10px 20px;
    margin: 0 10px;
    text-decoration: none;
    color: white;
    background-color: #331729;
    border-radius: 5px;
    font-weight: bold;
    text-align: center;
}

.button:hover {
    background-color: #49243E;
}

</style>
</head>
<body>
    <div class="container">
        <h1>Faculty Allocation Results</h1>

        <%
            String examDate = request.getParameter("examDate");
            List<Allocation> allocations = null;
            String errorMessage = "";
            String successMessage = "";

            // Handle room number update
            if ("POST".equalsIgnoreCase(request.getMethod()) && request.getParameter("updateRoomNumber") != null) {
                String facultyIdStr = request.getParameter("facultyId");
                String oldRoomNumber = request.getParameter("oldRoomNumber");
                String newRoomNumber = request.getParameter("newRoomNumber");

                Connection conn = null;
                PreparedStatement stmt = null;

                try {
                    conn = DbConnection.getConnection();
                    String updateQuery = "UPDATE faculty_allocation SET room_number = ? WHERE faculty_id = ? AND room_number = ? AND exam_date = ?";
                    stmt = conn.prepareStatement(updateQuery);
                    stmt.setString(1, newRoomNumber);
                    stmt.setInt(2, Integer.parseInt(facultyIdStr));
                    stmt.setString(3, oldRoomNumber);
                    stmt.setDate(4, java.sql.Date.valueOf(examDate));
                    int rowsAffected = stmt.executeUpdate();

                    if (rowsAffected > 0) {
                        successMessage = "Room number updated successfully!";
                    } else {
                        errorMessage = "No records updated. Please check the details.";
                    }
                } catch (SQLException e) {
                    errorMessage = "Error updating room number: " + e.getMessage();
                } finally {
                    try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
                    try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
                }
            }

            // Fetch updated allocations
            if (examDate != null) {
                try (Connection conn = DbConnection.getConnection()) {
                    String sql = "SELECT a.room_number, f.name, a.faculty_id FROM faculty_allocation a JOIN faculty f ON a.faculty_id = f.id WHERE a.exam_date = ? AND a.archived = false";
                    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setDate(1, java.sql.Date.valueOf(examDate));
                        try (ResultSet rs = stmt.executeQuery()) {
                            allocations = new ArrayList<>();
                            while (rs.next()) {
                                String roomNumber = rs.getString("room_number"); // Handle room_number as String
                                int facultyId = rs.getInt("faculty_id");
                                allocations.add(new Allocation(roomNumber, facultyId)); // Use String for room_number
                            }
                        }
                    }
                } catch (SQLException e) {
                    errorMessage = "Error fetching allocations: " + e.getMessage();
                }
            }
        %>

        <div class="<%= errorMessage.isEmpty() ? "" : "error" %>">
            <%= errorMessage %>
        </div>
        <div class="<%= successMessage.isEmpty() ? "" : "success" %>">
            <%= successMessage %>
        </div>

        <table>
            <thead>
                <tr>
                    <th>Room Number</th>
                    <th>Faculty ID</th>
                    <th>Faculty Name</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    if (allocations != null && !allocations.isEmpty()) {
                        for (Allocation allocation : allocations) {
                            String roomNumber = allocation.getRoomCode();
                            int facultyId = allocation.getFacultyId();
                            
                            // Query to get faculty name
                            Connection connForName = null;
                            PreparedStatement stmtForName = null;
                            ResultSet rsForName = null;
                            String facultyName = "";

                            try {
                                connForName = DbConnection.getConnection();
                                String nameQuery = "SELECT name FROM faculty WHERE id = ?";
                                stmtForName = connForName.prepareStatement(nameQuery);
                                stmtForName.setInt(1, facultyId);
                                rsForName = stmtForName.executeQuery();
                                if (rsForName.next()) {
                                    facultyName = rsForName.getString("name");
                                }
                            } catch (SQLException e) {
                                out.println("<p>Error fetching faculty names: " + e.getMessage() + "</p>");
                            } finally {
                                try { if (rsForName != null) rsForName.close(); } catch (SQLException e) { e.printStackTrace(); }
                                try { if (stmtForName != null) stmtForName.close(); } catch (SQLException e) { e.printStackTrace(); }
                                try { if (connForName != null) connForName.close(); } catch (SQLException e) { e.printStackTrace(); }
                            }
                %>
                <tr>
                    <td>
                        <!-- Display room number with edit option -->
                        <form method="post" action="display-allocations.jsp" class="edit-form">
                            <input type="hidden" name="facultyId" value="<%= facultyId %>">
                            <input type="hidden" name="oldRoomNumber" value="<%= roomNumber %>">
                            <input type="text" name="newRoomNumber" value="<%= roomNumber %>" required>
                            <input type="hidden" name="examDate" value="<%= examDate %>">
                            <button type="submit" name="updateRoomNumber" 
    style="background: none; color: #333; border: none; font-size: 16px; cursor: pointer; padding: 8px 16px; transition: color 0.3s ease, text-decoration 0.3s ease;"
    onmouseover="this.style.color='#007bff'; this.style.textDecoration='underline';"
    onmouseout="this.style.color='#333'; this.style.textDecoration='none';">
    Update
</button>
                            
                        </form>
                    </td>
                    <td><%= facultyId %></td>
                    <td><%= facultyName %></td>
                </tr>
                <% 
                        }
                    } else {
                        out.println("<p>No allocations to display.</p>");
                    }
                %>
            </tbody>
        </table>
           <div class="buttons">
                <a href="faculty-allocation-form.jsp" class="button">Back to Allocation Form</a>
                <a href="notification-center.jsp" class="button">Back to Notification Center</a>
                <a href="landing2.html" class="button">Back to Home</a>
                
</div>
    </div>
</body>
</html>
