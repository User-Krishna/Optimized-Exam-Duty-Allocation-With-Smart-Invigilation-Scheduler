package com.gnit.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.gnit.DatabaseFile.DbConnection;
import com.gnit.utils.AllocationUtils;

public class FacultyAllocationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String examDate = request.getParameter("examDate");
        int numRooms = Integer.parseInt(request.getParameter("numRooms"));
        int facultyPerRoom = Integer.parseInt(request.getParameter("facultyPerRoom"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        String timing = request.getParameter("timing");

        try (Connection conn = DbConnection.getConnection()) {
            List<Faculty> availableFaculty = getAvailableFaculty(conn, examDate);
            List<String> availableRooms = getAvailableRooms(conn);
            Collections.shuffle(availableRooms);

            if (availableFaculty.size() < numRooms * facultyPerRoom) {
                request.setAttribute("errorMessage", "Not enough faculty available for allocation.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }

            List<Allocation> allocations = allocateFacultyToRooms(availableFaculty, availableRooms, numRooms, facultyPerRoom);
            storeAllocations(conn, allocations, examDate, duration, timing);

            request.setAttribute("examDate", examDate);
            request.setAttribute("allocations", allocations);
            request.getRequestDispatcher("/display-allocations.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        }
    }

    private List<Faculty> getAvailableFaculty(Connection conn, String examDate) throws SQLException {
        List<Faculty> facultyList = new ArrayList<>();
        String query = "SELECT f.id, f.name FROM faculty f " +
                       "JOIN availability a ON f.id = a.faculty_id " +
                       "WHERE a.available_date = ? AND a.status = 'Available'";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(examDate));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    facultyList.add(new Faculty(id, name));
                }
            }
        }
        return facultyList;
    }

    private List<String> getAvailableRooms(Connection conn) throws SQLException {
        List<String> roomList = new ArrayList<>();
        String query = "SELECT room_code FROM room_details";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                roomList.add(rs.getString("room_code"));
            }
        }
        return roomList;
    }

    private List<Allocation> allocateFacultyToRooms(List<Faculty> availableFaculty, List<String> availableRooms, int numRooms, int facultyPerRoom) {
        List<Allocation> allocations = new ArrayList<>();
        Collections.shuffle(availableFaculty);  // Shuffle faculty list for random assignment

        int facultyIndex = 0;
        for (int room = 0; room < numRooms; room++) {
            for (int i = 0; i < facultyPerRoom; i++) {
                if (facultyIndex >= availableFaculty.size()) {
                    facultyIndex = 0; // Loop through faculty if needed
                }
                Faculty faculty = availableFaculty.get(facultyIndex++);
                String roomCode = availableRooms.get(room);
                allocations.add(new Allocation(roomCode, faculty.getId()));
            }
        }
        return allocations;
    }

    private void storeAllocations(Connection conn, List<Allocation> allocations, String examDate, int duration, String timing) throws SQLException {
        String archiveQuery = "UPDATE faculty_allocation SET archived = 1 WHERE exam_date = ?";
        try (PreparedStatement archiveStmt = conn.prepareStatement(archiveQuery)) {
            archiveStmt.setDate(1, Date.valueOf(examDate));
            archiveStmt.executeUpdate();
        }

        String insertQuery = "INSERT INTO faculty_allocation (exam_date, room_number, faculty_id, duration, timing, archived) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
            for (Allocation allocation : allocations) {
                insertStmt.setDate(1, Date.valueOf(examDate));
                insertStmt.setString(2, allocation.getRoomCode());
                insertStmt.setInt(3, allocation.getFacultyId());
                insertStmt.setInt(4, duration);
                insertStmt.setString(5, timing);
                insertStmt.setBoolean(6, false); // Ensure this matches the SQL query
                insertStmt.addBatch();
            }
            insertStmt.executeBatch();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String facultyId = request.getParameter("facultyId");
        String oldRoomCode = request.getParameter("oldRoomNumber");
        String newRoomCode = request.getParameter("newRoomNumber");
        String examDate = request.getParameter("examDate");

        try (Connection conn = DbConnection.getConnection()) {
            String updateQuery = "UPDATE faculty_allocation SET room_number = ? WHERE faculty_id = ? AND room_number = ? AND exam_date = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                stmt.setString(1, newRoomCode);
                stmt.setInt(2, Integer.parseInt(facultyId));
                stmt.setString(3, oldRoomCode);
                stmt.setDate(4, Date.valueOf(examDate));
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    List<Allocation> allocations = AllocationUtils.getAllocations(conn, examDate);
                    request.setAttribute("allocations", allocations);
                    request.setAttribute("successMessage", "Room number updated successfully!");
                } else {
                    request.setAttribute("errorMessage", "No records updated. Please check the details.");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        }

        request.getRequestDispatcher("/display-allocations.jsp").forward(request, response);
    }

    public static class Faculty {
        private int id;
        private String name;

        public Faculty(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class Allocation {
        private String roomCode;
        private int facultyId;

        public Allocation(String roomCode, int facultyId) {
            this.roomCode = roomCode;
            this.facultyId = facultyId;
        }

        public String getRoomCode() {
            return roomCode;
        }

        public int getFacultyId() {
            return facultyId;
        }
    }
}