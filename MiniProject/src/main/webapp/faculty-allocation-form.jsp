<!DOCTYPE html>
<html>
<head>
    <title>Faculty Allocation</title>
  <style>
    /* General body styling */
    body {
        font-family: 'Poppins', sans-serif;
        margin: 0;
        padding: 0;
        background-image: url('images/3.jpg'); /* Using your image */
        background-size: cover;
        background-position: center;
        background-attachment: fixed;
        color: #333;
        min-height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    /* Form container styling */
    .container {
        width: 100%;
        max-width: 600px;
        background: rgba(255, 255, 255, 0.9); /* Semi-transparent white */
        backdrop-filter: blur(8px); /* Frosted glass effect */
        padding: 30px;
        border-radius: 16px;
        box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.3);
        transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
    }

    .container:hover {
        transform: translateY(-5px);
        box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.4);
    }

    /* Header styling */
    h1 {
        text-align: center;
        font-size: 28px;
        margin-bottom: 20px;
        color: #6A0572;
        font-weight: bold;
        text-transform: uppercase;
    }

    /* Form layout styling */
    form {
        display: flex;
        flex-direction: column;
    }

    label {
        font-size: 14px;
        margin-bottom: 8px;
        color: #555;
        font-weight: bold;
    }

    input[type="text"], 
    input[type="number"], 
    input[type="date"], 
    input[type="time"] {
        font-size: 16px;
        padding: 12px;
        margin-bottom: 20px;
        border-radius: 8px;
        border: 1px solid #ddd;
        background: rgba(255, 255, 255, 0.8);
        box-shadow: inset 0px 2px 5px rgba(0, 0, 0, 0.1);
        transition: all 0.3s ease;
    }

    input[type="text"]:focus, 
    input[type="number"]:focus, 
    input[type="date"]:focus, 
    input[type="time"]:focus {
        border-color: #6A0572;
        box-shadow: 0px 0px 8px rgba(106, 5, 114, 0.4);
        outline: none;
    }

    input[type="submit"] {
        font-size: 16px;
        font-weight: bold;
        padding: 12px;
        border: none;
        border-radius: 8px;
        background: linear-gradient(to right, #6A0572, #A61279);
        color: white;
        cursor: pointer;
        text-transform: uppercase;
        transition: background-color 0.3s ease, transform 0.2s ease;
    }

    input[type="submit"]:hover {
        background: linear-gradient(to right, #A61279, #6A0572);
        transform: translateY(-2px);
        box-shadow: 0px 8px 15px rgba(166, 18, 121, 0.2);
    }

    input[type="submit"]:active {
        background-color: #54034E;
        transform: translateY(0);
        box-shadow: none;
    }

    /* Responsive design for smaller screens */
    @media (max-width: 768px) {
        .container {
            padding: 20px;
        }

        h1 {
            font-size: 24px;
        }

        input[type="text"], 
        input[type="number"], 
        input[type="date"], 
        input[type="time"], 
        input[type="submit"] {
            font-size: 14px;
            padding: 10px;
        }
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
