package com.gnit.utils;

import com.gnit.servlet.FacultyAllocationServlet.Allocation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AllocationUtils {

    public static List<Allocation> getAllocations(Connection conn, String examDate) throws SQLException {
        List<Allocation> allocations = new ArrayList<>();
        String query = "SELECT room_number, faculty_id FROM faculty_allocation WHERE exam_date = ? AND archived = false";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(examDate));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String roomNumber = rs.getString("room_number"); 
                    int facultyId = rs.getInt("faculty_id");
                    allocations.add(new Allocation(roomNumber, facultyId));
                }
            }
        }
        return allocations;
    }
}
