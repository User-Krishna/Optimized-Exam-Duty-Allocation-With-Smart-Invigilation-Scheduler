<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Notification Success</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
            background-image: url('https://www.transparenttextures.com/patterns/white-diamond.png');
        }
        .container {
            max-width: 500px;
            margin: 100px auto;
            padding: 20px;
            border-radius: 8px;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h2 {
            font-size: 24px;
            color: #4CAF50;
            margin-bottom: 20px;
        }
        p {
            font-size: 16px;
            color: #333;
            margin-bottom: 20px;
        }
        .button {
            background-color: #4CAF50;
            color: #ffffff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            transition: background-color 0.3s ease;
        }
        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>✅ Email Sent Successfully!</h2>
        <p>Your email notifications have been sent successfully to all selected faculty members.</p>
        <a href="landing2.html" class="button">Goto Home</a>
    </div>
</body>
</html>
