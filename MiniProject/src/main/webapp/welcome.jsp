<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<script type="text/javascript">
    function redirectUser(username) {
        if (username === "ankur"|| username=="hod") {
            window.location.href = "landing.html";
        } else if (username === "binod" || username === "krishna"|| username=="coe") {
            window.location.href = "landing2.html";
        }
    }
</script>
</head>
<body>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        // If no username is found in the session, redirect to login page
        response.sendRedirect("login.jsp");
    } else {
        // Output welcome message
        out.println("<h1>Welcome " + username + "!</h1>");
%>
        <script type="text/javascript">
            // Call redirect function based on the username
            redirectUser("<%= username %>");
        </script>
<%
    }
%>
</body>
</html>
