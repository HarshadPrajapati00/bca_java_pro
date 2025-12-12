package com.example.dao;

import com.example.db.DBConnection;
import com.example.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentCallableDAO {

    // 1) Call stored procedure that INSERTs a student (IN params)
    public boolean insertUsingProcedure(Student s) {
        String call = "{CALL insert_student(?, ?, ?, ?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(call)) {

            cs.setInt(1, s.getId());
            cs.setString(2, s.getName());
            cs.setString(3, s.getCourse());
            cs.setString(4, s.getMarks());

            int affected = cs.executeUpdate();
            return affected == 1; // expect 1 row inserted

        } catch (SQLException e) {
            System.out.println("Insert proc error: " + e.getMessage());
            return false;
        }
    }

    // 2) Call stored procedure that SELECTs by id (returns result set)
    public Student getByIdUsingProcedure(int id) {
        String call = "{CALL get_student_by_id(?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(call)) {

            cs.setInt(1, id);

            // For a SELECT inside stored proc, use executeQuery or execute() then getResultSet
            boolean hadResultSet = cs.execute();
            if (hadResultSet) {
                try (ResultSet rs = cs.getResultSet()) {
                    if (rs.next()) {
                        return new Student(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("course"),
                                rs.getString("marks")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("GetById proc error: " + e.getMessage());
        }
        return null;
    }

    // 3) Call stored procedure with OUT parameter (count)
    public int countByCourseUsingProcedure(String course) {
        String call = "{CALL count_students_by_course(?, ?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(call)) {

            cs.setString(1, course);
            cs.registerOutParameter(2, Types.INTEGER);

            cs.execute(); // execute the procedure

            return cs.getInt(2); // retrieve OUT parameter
        } catch (SQLException e) {
            System.out.println("Count proc error: " + e.getMessage());
            return -1;
        }
    }

    // Optional: get all students using simple SELECT (regular PreparedStatement)
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT id, name, course, marks FROM students";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getString("marks")
                ));
            }
        } catch (SQLException e) {
            System.out.println("FindAll error: " + e.getMessage());
        }
        return list;
    }
}
