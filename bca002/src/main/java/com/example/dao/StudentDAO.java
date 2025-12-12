package com.example.dao;

import com.example.db.DBConnection;
import com.example.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public boolean insert(Student s) {
        String sql = "INSERT INTO students (id, name, course, marks) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, s.getId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getCourse());
            ps.setString(4, s.getMarks());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println("Insert Error: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Student s) {
        String sql = "UPDATE students SET name = ?, course = ?, marks = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getName());
            ps.setString(2, s.getCourse());
            ps.setString(3, s.getMarks());
            ps.setInt(4, s.getId());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println("Update Error: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println("Delete Error: " + e.getMessage());
            return false;
        }
    }

    public Student findById(int id) {
        String sql = "SELECT id, name, course, marks FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getString("marks")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("FindById Error: " + e.getMessage());
        }
        return null;
    }

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
            System.out.println("FindAll Error: " + e.getMessage());
        }

        return list;
    }
}
