<%@ page import="java.sql.Connection, java.sql.Statement, java.sql.ResultSet, java.sql.SQLException" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.gnit.DatabaseFile.DbConnection" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Notification Center</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
            background-image: url('https://www.transparenttextures.com/patterns/white-diamond.png');
        }

        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            border-radius: 8px;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            font-size: 22px;
            color: #333;
            margin-bottom: 15px;
        }
        .faculty-list {
            margin-bottom: 20px;
        }
        .faculty-list label {
            font-size: 16px;
            font-weight: bold;
            color: #555;
        }
        .faculty-names {
            display: flex;
            flex-wrap: nowrap;
            overflow-x: auto;
            white-space: nowrap;
            gap: 10px;
            margin-bottom: 10px;
            padding: 10px 0;
            border: 1px solid #ddd;
            background-color: #f9f9f9;
            border-radius: 5px;
            scrollbar-width: thin;
        }
        .faculty-names::-webkit-scrollbar {
            height: 8px;
        }
        .faculty-names::-webkit-scrollbar-thumb {
            background: #888;
            border-radius: 10px;
        }
        .faculty-names::-webkit-scrollbar-thumb:hover {
            background: #555;
        }
        .faculty-names span {
            background-color: #e7f0ff;
            padding: 8px 12px;
            border-radius: 20px;
            font-size: 14px;
            color: #333;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            display: inline-block;
            white-space: nowrap;
        }
        .text-area-container {
            margin-bottom: 20px;
        }
        input[type="date"], input[type="time"], textarea {
            width: calc(100% - 22px);
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
            margin-bottom: 10px;
        }
        textarea {
            height: 150px;
            resize: vertical;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            color: #666;
        }
        label {
            font-size: 14px;
            margin-bottom: 5px;
            display: block;
            color: #555;
        }
        .submit-button {
            background-color: #4CAF50;
            color: #ffffff;
            padding: 10px 18px;
            border: none;
            border-radius: 5px;
            font-size: 14px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .submit-button:hover {
            background-color: #45a049;
        }
        .form-group {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2> 📬 Notification Center 📣</h2>
    <form action="NotificationServlet" method="post">
        <div class="faculty-list">
            <label>To:</label>
            <div class="faculty-names">
                <%
                    Connection conn = null;
                    Statement stmt = null;
                    ResultSet rs = null;
                    int totalCount = 0;
                    String examDate = "";
                    String examTime = "";

                    try {
                        conn = DbConnection.getConnection();
                        stmt = conn.createStatement();
                        String query = "SELECT f.name, f.email, a.exam_date, a.timing " +
                                       "FROM faculty f JOIN faculty_allocation a ON f.id = a.faculty_id " +
                                       "WHERE a.archived = 0";
                        rs = stmt.executeQuery(query);

                        while (rs.next()) {
                            String facultyName = rs.getString("name");
                            String email = rs.getString("email");
                            examDate = rs.getDate("exam_date") != null ? new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("exam_date")) : "";
                            examTime = rs.getTime("timing") != null ? new SimpleDateFormat("HH:mm").format(rs.getTime("timing")) : "";
                            totalCount++;
                %>
                <span><%= facultyName %> (<%= email %>)</span>
                <%
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
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
            </div>
            <div>
                <strong>Total Count:</strong> <%= totalCount %>
            </div>
        </div>

        <div class="form-group">
            <label for="exam_date">Exam Date:</label>
            <input type="date" id="exam_date" name="exam_date" value="<%= examDate %>" required />
        </div>
        
        <div class="form-group">
            <label for="exam_time">Exam Timing:</label>
            <input type="time" id="exam_time" name="exam_time" value="<%= examTime %>" required />
        </div>

        <div class="form-group">
            <label for="message">Message:</label>
            <textarea id="message" name="message" readonly>
                Dear [Faculty Name],

                This is a notification regarding your Exam invigilator duty allocation:

                Exam Date: [exam_date]
                Room Number: [room_number]
                Block: [block]
                Floor: [floor]
                Timing: [exam_time]
                Duration: [duration]

                Best regards,
                Exam Branch Coordinator
            </textarea>
        </div>

        <button type="submit" class="submit-button">Send Mail</button>
    </form>
</div>

</body>
</html>
