<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="CSS/style.css">
    <title>Login</title>
    <style>
        body {
            background-image: url(images/clz2.jpg);
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            background-color: #AEFD6C;
            background-size: cover;
            background-position: center center;
            background-repeat: no-repeat;
        }

        #cameraModal {
            display: none;
            position: fixed;
            z-index: 1;
            padding-top: 60px;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0,0,0,0.4);
        }

        #cameraModalContent {
            background-color: #fefefe;
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            width: 60%;
            max-width: 300px;
            text-align: center;
        }

        video {
            width: 100%;
            max-width: 250px;
            border: 1px solid black;
        }

        #capture, #closeCamera {
            margin: 10px 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Login</h1>
        <form action="LoginServlet" method="post" id="loginForm">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>
            <button type="submit">Login</button>
        </form>
<p>Or<p>
        <!-- New 'Login with Face' button -->
        <form action="http://localhost:5173/user-select" method="get">
            <button type="submit" style="margin-top: 10px;">Login with Face</button>
        </form>

        <!-- Error message handling -->
        <% String error = request.getParameter("error");
        if (error != null && error.equals("1")) { %>
            <p style="color: red;">Invalid username or password. Please try again.</p>
        <% } %>

        <% String rs = request.getParameter("registration");
        if (rs != null && rs.equals("success")) { %>
            <p style="color: green;">Your Registration is Successful. Please Login.</p>
        <% } %>
    </div>
</body>
</html>
