<%@ page import="java.sql.*, com.gnit.DatabaseFile.DbConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Faculty Management</title>
    <style>
       /* General styles */
body {
    margin: 0px;
    padding: 0px;
    font-family: 'Arial', sans-serif; /* Set a modern font family */
    background-image: url('images/faculty-availability.jpg');
    background-size: cover; /* Ensure the background covers the entire area */
    background-position: center;
    background-attachment: fixed;
}

/* Box styling for the container */
.container {
    background-color: rgba(228, 224, 225, 0.7); /* Decreased transparency of box background */
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1); /* Soft shadow for the box */
    margin: 20px; /* Margin to give space around the box */
    max-width: 800px; /* Decreased width of the container */
    margin-left: auto; /* Center the container */
    margin-right: auto; /* Center the container */
}

/* Heading styles */
h1, h2 {
    color: black; /* Set text color to black */
    text-align: center;
    font-size: 28px; /* Normal font size */
    transition: font-size 0.3s ease; /* Transition for smooth effect */
}

/* Enlarge text on heading hover */
h1:hover, h2:hover {
    font-size: 32px; /* Increase font size on hover */
}

/* Form input fields */
input[type="number"], input[type="text"], input[type="email"], select {
    padding: 12px;
    margin-bottom: 15px;
    width: 100%;
    box-sizing: border-box;
    border: 2px solid #D5B4B4; /* Updated border color */
    border-radius: 8px;
    font-size: 18px; /* Normal font size */
    background-color: #F5EBEB; /* Light background for form fields */
    transition: border-color 0.3s ease, box-shadow 0.3s ease;
}

input[type="number"]:focus, input[type="text"]:focus, input[type="email"]:focus, select:focus {
    border-color: #80bdff;
    box-shadow: 0 0 8px rgba(0, 123, 255, 0.5); /* Blue shadow on focus */
}

/* Button styling */
input[type="submit"] {
    background-color: #49243E; /* Dark color for buttons */
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    padding: 8px 12px; /* Smaller padding for button size */
    font-size: 14px; /* Normal button font size */
    transition: background-color 0.3s ease; /* Adding transition effects */
}

input[type="submit"]:hover {
    background-color: #331729; /* Darker shade on hover */
}

/* Table styling */
table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

th, td {
    border: 1px solid #D5B4B4; /* Updated border color */
    padding: 12px; /* Increased padding for better spacing */
    text-align: left;
    color: black; /* Set text color to black */
    font-size: 18px; /* Normal font size for table cells */
    transition: color 0.3s ease, transform 0.3s ease; /* Adding transition effects */
}

/* Table header styles */
th {
    background-color: #49243E; /* Dark color for table header */
    color: white;
}

/* Hover effect only on text in table */
td:hover {
    color: #867070; /* Change text color on hover */
    transform: scale(1.1); /* Slightly enlarges text on hover */
}

/* Styling for the button container */
.button-container {
    text-align: center;
    margin-top: 20px;
}

/* Styling for the back button */
.back-btn {
    background-color: #49243E; /* Dark color for back button */
    color: white;
    padding: 10px 15px; /* Normal padding for back button size */
    text-decoration: none;
    border-radius: 4px;
    display: inline-block;
    transition: background-color 0.3s ease; /* Adding transition effects */
}

.back-btn:hover {
    background-color: #7E6363; /* Darker shade on hover */
}

    </style>
</head>
<body>
    <div class="container">
        <h1>Faculty Management</h1>

        <!-- Form for Adding Faculty -->
        <form method="post" action="faculty-management.jsp">
            <h2>Add Faculty</h2>
            <input type="number" name="id" placeholder="ID" required>
            <input type="text" name="name" placeholder="Name" required>
            <input type="text" name="department" placeholder="Department" required>
            <input type="email" name="email" placeholder="Email" required>
            <input type="submit" name="action" value="Add Faculty">
        </form>

        <!-- Form for Searching and Editing Faculty -->
        <form method="get" action="faculty-management.jsp">
            <h2>Search and Edit Faculty</h2>
            <input type="text" name="searchId" placeholder="Search by ID">
            <input type="submit" value="Search">
        </form>

        <% 
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String action = request.getParameter("action");
            try {
                // Get database connection
                conn = DbConnection.getConnection();

                // Handle Add Faculty
                if ("Add Faculty".equals(action)) {
                    String id = request.getParameter("id");
                    String name = request.getParameter("name");
                    String department = request.getParameter("department");
                    String email = request.getParameter("email");
                    
                    // Check if ID already exists
                    String checkIdQuery = "SELECT COUNT(*) FROM faculty WHERE id = ?";
                    stmt = conn.prepareStatement(checkIdQuery);
                    stmt.setInt(1, Integer.parseInt(id));
                    rs = stmt.executeQuery();
                    if (rs.next() && rs.getInt(1) > 0) {
                        out.println("<p>ID already exists!</p>");
                    } else {
                        String insertQuery = "INSERT INTO faculty (id, name, department, email) VALUES (?, ?, ?, ?)";
                        stmt = conn.prepareStatement(insertQuery);
                        stmt.setInt(1, Integer.parseInt(id));
                        stmt.setString(2, name);
                        stmt.setString(3, department);
                        stmt.setString(4, email);
                        stmt.executeUpdate();
                        out.println("<p>Faculty added successfully!</p>");
                    }
                }

                // Handle Search and Edit Faculty
                String searchId = request.getParameter("searchId");
                if (searchId != null && !searchId.isEmpty()) {
                    String selectQuery = "SELECT * FROM faculty WHERE id = ?";
                    stmt = conn.prepareStatement(selectQuery);
                    stmt.setInt(1, Integer.parseInt(searchId));
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        String id = rs.getString("id");
                        String name = rs.getString("name");
                        String department = rs.getString("department");
                        String email = rs.getString("email");
        %>
        <!-- Edit Faculty Form -->
        <form method="post" action="faculty-management.jsp">
            <h2>Edit Faculty</h2>
            <input type="hidden" name="id" value="<%= id %>">
            <input type="text" name="name" value="<%= name %>" required>
            <input type="text" name="department" value="<%= department %>" required>
            <input type="email" name="email" value="<%= email %>" required>
            <input type="submit" name="action" value="Update Faculty">
        </form>
        <% 
                    } else {
                        out.println("<p>Faculty not found!</p>");
                    }
                }

                // Handle Update Faculty
                if ("Update Faculty".equals(action)) {
                    String id = request.getParameter("id");
                    String name = request.getParameter("name");
                    String department = request.getParameter("department");
                    String email = request.getParameter("email");
                    String updateQuery = "UPDATE faculty SET name = ?, department = ?, email = ? WHERE id = ?";
                    stmt = conn.prepareStatement(updateQuery);
                    stmt.setString(1, name);
                    stmt.setString(2, department);
                    stmt.setString(3, email);
                    stmt.setInt(4, Integer.parseInt(id));
                    stmt.executeUpdate();
                    out.println("<p>Faculty updated successfully!</p>");
                }

                // Handle Delete Faculty
               if ("Delete Faculty".equals(action)) {
    String id = request.getParameter("id");

    // Delete related records in the availability table first
    String deleteAvailabilityQuery = "DELETE FROM availability WHERE faculty_id = ?";
    stmt = conn.prepareStatement(deleteAvailabilityQuery);
    stmt.setInt(1, Integer.parseInt(id));
    stmt.executeUpdate();

    // Now delete the faculty record
    String deleteQuery = "DELETE FROM faculty WHERE id = ?";
    stmt = conn.prepareStatement(deleteQuery);
    stmt.setInt(1, Integer.parseInt(id));
    stmt.executeUpdate();

    out.println("<p>Faculty deleted successfully!</p>");
}


                // Fetch all faculty records for display
                String selectAllQuery = "SELECT * FROM faculty";
                stmt = conn.prepareStatement(selectAllQuery);
                rs = stmt.executeQuery();
        %>

        <!-- Displaying Faculty List -->
        <h2>Faculty List</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Department</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
            <% while (rs.next()) { %>
            <tr>
                <td><%= rs.getString("id") %></td>
                <td><%= rs.getString("name") %></td>
                <td><%= rs.getString("department") %></td>
                <td><%= rs.getString("email") %></td>
                <td>
                    <div class="button-container">
                        <form method="post" action="faculty-management.jsp" style="display:inline;">
                            <input type="hidden" name="id" value="<%= rs.getString("id") %>">
                            <input type="submit" name="action" value="Delete Faculty" class="delete-button">
                        </form>
                        <form method="get" action="faculty-management.jsp" style="display:inline;">
                            <input type="hidden" name="searchId" value="<%= rs.getString("id") %>">
                            <input type="submit" value="Edit Faculty" class="edit-button">
                        </form>
                    </div>
                </td>
            </tr>
            <% } %>
        </table>
        <% 
            } catch (SQLException e) {
                out.println("<p>Error: " + e.getMessage() + "</p>");
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        %>
        <div class="button-container">
            <a href="landing.html" class="back-btn">Back to Home</a> <!-- Back to Home Button -->
        </div>
    </div>
</body>
</html>
