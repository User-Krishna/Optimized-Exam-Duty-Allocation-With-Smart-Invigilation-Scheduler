<!DOCTYPE html>
<html>
<head>
    <title>Faculty Allocation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color:  #f4f4f4;
            background-image: url('images/3.jpg'); /* Replace with your background image URL */
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background: rgba(248, 249, 250, 0.8);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        form {
            margin-top: 20px;
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        input[type="text"], input[type="number"], input[type="date"], input[type="time"] {
            padding: 10px;
            width: calc(100% - 22px);
            margin-bottom: 10px;
            border-radius: 4px;
            border: 1px solid #ccc;
            background: rgba(248, 249, 250, 0.8);
        }
        input[type="submit"] {
            padding: 10px 15px;
            background-color: #49243E; /* Dark color for button */
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #331729; /* Darker shade on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Faculty Allocation</h1>
        <form method="post" action="FacultyAllocationServlet">
            <label for="examDate">Select Date of Exam:</label>
            <input type="date" id="examDate" name="examDate" required>

            <label for="numRooms">Number of Rooms:</label>
            <input type="number" id="numRooms" name="numRooms" required>

            <label for="facultyPerRoom">Number of Faculty per Room:</label>
            <input type="number" id="facultyPerRoom" name="facultyPerRoom" required>

            <label for="duration">Duration of Exam (in Minutes):</label>
            <input type="text" id="duration" name="duration" required>

            <label for="timing">Timing of Exam:</label>
            <input type="time" id="timing" name="timing" required>

            <input type="submit" value="Allocate Faculty">
        </form>
    </div>
</body>
</html>
