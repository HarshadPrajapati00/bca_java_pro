package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3307/college_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // set your DB user
    private static final String PASS = "harshad1"; // set your DB password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

