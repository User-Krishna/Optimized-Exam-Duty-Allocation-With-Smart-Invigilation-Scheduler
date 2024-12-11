<%@ page import="java.sql.*, com.gnit.DatabaseFile.DbConnection" %>
<%@ page import="com.itextpdf.text.*, com.itextpdf.text.pdf.*, java.io.FileOutputStream, java.io.IOException" %>

<!DOCTYPE html>
<html>
<head>
    <title>Faculty Availability</title>
    <link rel="stylesheet" type="text/css" href="CSS/faculty-availability.css">
    <style>
  body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4;
    background-image: url('images/4.jpg');
    background-size: cover;
    background-position: center;
    background-attachment: fixed;
}

.center-button {
    display: flex;
    justify-content: center;
    margin-top: 20px;
}

.center-button button {
    padding: 10px 20px;
    font-size: 16px;
    color: #fff;
    background-color: #7E6363; /* Blue background */
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.center-button button:hover {
    background-color: #3E3232; /* Darker blue on hover */
}

/* Container styling */
.container {
    max-width: 1250px;
    margin: 30px auto;
    background: rgba(255, 255, 255, 0.8);
    padding: 30px;
    border-radius: 12px;
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
    overflow-x: auto;
}

/* Heading styling */
h1, h2 {
    color: #333;
    text-align: center;
}

/* Table styling */
table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
    font-size: 1.1em; /* Increased text size */
}

th, td {
    padding: 12px;
    border: 1px solid #ddd;
    text-align: center;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

th {
    background-color: #49243E; /* Dark color for header */
    color: white;
}

td {
    transition: color 0.3s ease, font-size 0.3s ease; /* Smooth transition for hover effect */
}

/* Hover effect */
td:hover {
    color: #637E76; /* Dark color on hover */
    font-size: 1.2em; /* Increase text size on hover */
}

/* Form styling */
form {
    margin-bottom: 20px;
    text-align: center;
}

input[type="text"], input[type="date"], select {
    padding: 10px;
    width: 220px;
    margin-right: 10px;
    border-radius: 4px;
    border: 1px solid #ccc;
}

input[type="submit"] {
    padding: 10px 20px;
    background-color: #49243E;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

input[type="submit"]:hover {
    background-color: #331729; /* Darker shade on hover */
}

/* Conditional date styling */
.conditional-date {
    display: none;
    margin-top: 10px;
}

.conditional-date input[type="date"] {
    width: 240px;
}

/* Wrapper to hold both forms in a single line, aligned center */
.inline-forms-container {
    display: flex;  /* Use flexbox for horizontal alignment */
    justify-content: center;  /* Center-align both forms horizontally */
    align-items: center;  /* Vertically center the forms */
    gap: 20px;  /* Add some space between the forms */
    margin-top: 20px;
}

/* Button styling */
.styled-button {
    padding: 10px 20px;
    font-size: 16px;
    color: #fff;
    background-color: #7E6363; /* Button background */
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.styled-button:hover {
    background-color: #3E3232; /* Darker background on hover */
}



    </style>
</head>

<body>
    <div class="container">
        <h1>Faculty Availability</h1>
        <div class="inline-forms-container">
            <!-- Search Faculty Form -->
            <form method="post" action="faculty-availability.jsp">
                <input type="text" name="facultyId" placeholder="Enter Faculty ID" required>
                <input type="submit" value="Search">
            </form>
            <!-- Mark All Available Form -->
            <form action="DbConnectionTest" method="post">
                <button type="submit" class="styled-button">Mark All Available</button>
            </form>
        </div>

        <h2>Faculty Status Counts</h2>
        <%
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                conn = DbConnection.getConnection();
                String countQuery = "SELECT status, COUNT(*) AS count FROM availability GROUP BY status";
                stmt = conn.prepareStatement(countQuery);
                rs = stmt.executeQuery();
        %>
        <table>
            <tr>
                <th>Status</th>
                <th>Count</th>
                <th>Actions</th>
            </tr>
            <%
                while (rs.next()) {
                    String status = rs.getString("status");
                    int count = rs.getInt("count");
            %>
            <tr>
                <td><%= status %></td>
                <td><%= count %></td>
                <td>
                    <form method="post" action="faculty-availability.jsp">
                        <input type="hidden" name="status" value="<%= status %>">
                        <input type="submit" value="Generate PDF">
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            } catch (SQLException e) {
                out.println("<p>Error fetching status counts: " + e.getMessage() + "</p>");
            } finally {
                try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
                try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
                try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        %>

        <%
            String facultyId = request.getParameter("facultyId");
            if (facultyId != null && !facultyId.isEmpty()) {
                try {
                    conn = DbConnection.getConnection();
                    String query = "SELECT a.id, f.name, f.department, f.phone, a.available_date, a.status " +
                                   "FROM availability a JOIN faculty f ON a.faculty_id = f.id " +
                                   "WHERE a.faculty_id = ?";
                    stmt = conn.prepareStatement(query);
                    stmt.setString(1, facultyId);
                    rs = stmt.executeQuery();

                    if (rs.next()) {
                        String name = rs.getString("name");
                        String department = rs.getString("department");
                        String phone = rs.getString("phone");
                        String availableDate = rs.getString("available_date");
                        String status = rs.getString("status");
        %>

        <table>
            <tr>
                <th>Faculty ID</th>
                <th>Name</th>
                <th>Department</th>
                <th>Phone</th>
                <th>Available Date</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            <tr>
                <td><%= facultyId %></td>
                <td><%= name %></td>
                <td><%= department %></td>
                <td><%= phone %></td>
                <td><%= availableDate %></td>
                <td><%= status %></td>
                <td>
                    <!-- Update Status Form -->
                    <form method="post" action="faculty-availability.jsp" class="update-form">
                        <input type="hidden" name="facultyId" value="<%= facultyId %>">
                        <select name="newStatus">
                            <option value="Available" <%= "Available".equals(status) ? "selected" : "" %>>Available</option>
                            <option value="On Leave" <%= "On Leave".equals(status) ? "selected" : "" %>>On Leave</option>
                            <option value="Busy" <%= "Busy".equals(status) ? "selected" : "" %>>Busy</option>
                        </select>
                        <input type="date" name="newAvailableDate" value="<%= availableDate %>" required>
                        <input type="submit" name="updateStatus" value="Update Status">
                    </form>
                </td>
            </tr>
        </table>

        <%
                    } else {
                        out.println("<p>No availability records found for Faculty ID: " + facultyId + "</p>");
                    }
                } catch (Exception e) {
                    out.println("<p>Error: " + e.getMessage() + "</p>");
                } finally {
                    try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
                    try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
                    try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
                }
            }
        %>

        <%
            // Handle status update
            if ("POST".equalsIgnoreCase(request.getMethod()) && request.getParameter("updateStatus") != null) {
                String facultyIdToUpdate = request.getParameter("facultyId");
                String newStatus = request.getParameter("newStatus");
                String newAvailableDate = request.getParameter("newAvailableDate");

                try {
                    conn = DbConnection.getConnection();
                    String updateQuery = "UPDATE availability SET status = ?, available_date = ? WHERE faculty_id = ?";
                    stmt = conn.prepareStatement(updateQuery);
                    stmt.setString(1, newStatus);
                    stmt.setString(2, newAvailableDate);
                    stmt.setString(3, facultyIdToUpdate);

                    int rowsUpdated = stmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        out.println("<p>Status updated successfully for Faculty ID: " + facultyIdToUpdate + "</p>");
                    } else {
                        out.println("<p>Failed to update status for Faculty ID: " + facultyIdToUpdate + "</p>");
                    }
                } catch (SQLException e) {
                    out.println("<p>Error updating status: " + e.getMessage() + "</p>");
                } finally {
                    try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
                    try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
                }
            }
        %>
        <%
            if ("POST".equalsIgnoreCase(request.getMethod()) && request.getParameter("status") != null) {
                String statusToFetch = request.getParameter("status");
                try {
                    conn = DbConnection.getConnection();
                    String pdfQuery = "SELECT f.id, f.name, f.department, f.phone " +
                                      "FROM availability a JOIN faculty f ON a.faculty_id = f.id " +
                                      "WHERE a.status = ?";
                    stmt = conn.prepareStatement(pdfQuery);
                    stmt.setString(1, statusToFetch);
                    rs = stmt.executeQuery();

                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "attachment;filename=Faculty_Details_" + statusToFetch + ".pdf");
                    Document document = new Document();
                    PdfWriter.getInstance(document, response.getOutputStream());
                    document.open();

                    Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.RED);
                    Font contentFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);

                    Paragraph header = new Paragraph("Faculty Details - " + statusToFetch, headerFont);
                    header.setAlignment(Element.ALIGN_CENTER);
                    header.setSpacingAfter(20);
                    document.add(header);

                    PdfPTable table = new PdfPTable(4);
                    table.setWidthPercentage(100);
                    table.addCell(new Phrase("Faculty ID", headerFont));
                    table.addCell(new Phrase("Name", headerFont));
                    table.addCell(new Phrase("Department", headerFont));
                    table.addCell(new Phrase("Phone", headerFont));

                    while (rs.next()) {
                        table.addCell(new Phrase(rs.getString("id"), contentFont));
                        table.addCell(new Phrase(rs.getString("name"), contentFont));
                        table.addCell(new Phrase(rs.getString("department"), contentFont));
                        table.addCell(new Phrase(rs.getString("phone"), contentFont));
                    }

                    document.add(table);
                    document.close();
                } catch (Exception e) {
                    out.println("<p>Error generating PDF: " + e.getMessage() + "</p>");
                } finally {
                    try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
                    try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
                    try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
                }
            }
        %>

        <!-- Back to Dashboard -->
        <div class="center-button">
            <a href="landing.html"><button>Back to Dashboard</button></a>
        </div>
    </div>
</body>
</html>
