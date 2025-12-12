package com.example.Main;

import com.example.dao.StudentCallableDAO;
import com.example.model.Student;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final StudentCallableDAO dao = new StudentCallableDAO();

    private static int readInt(String msg) {
        while (true) {
            System.out.println(msg);
            String s = sc.nextLine().trim();
            if (s.isEmpty()) { System.out.println("Input required."); continue; }
            try { return Integer.parseInt(s); }
            catch (NumberFormatException e) { System.out.println("Enter a valid integer."); }
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- CallableStatement DEMO ---");
            System.out.println("1. Insert student (stored proc)");
            System.out.println("2. Get student by ID (stored proc returns resultset)");
            System.out.println("3. Count students by course (stored proc OUT param)");
            System.out.println("4. Show all students (regular query)");
            System.out.println("5. Exit");
            System.out.println("Enter choice: ");

            int ch = readInt("");
            switch (ch) {
                case 1:
                    doInsert();
                    break;
                case 2:
                    doGetById();
                    break;
                case 3:
                    doCountByCourse();
                    break;
                case 4:
                    doShowAll();
                    break;
                case 5:
                    System.out.println("Exiting.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void doInsert() {
        int id = readInt("ID: ");
        System.out.println("Name: "); String name = sc.nextLine().trim();
        System.out.println("Course: "); String course = sc.nextLine().trim();
        System.out.println("Marks: "); String marks = sc.nextLine().trim();

        Student s = new Student(id, name, course, marks);
        boolean ok = dao.insertUsingProcedure(s);
        System.out.println(ok ? "Inserted via procedure." : "Insert failed (maybe duplicate id).");
    }

    private static void doGetById() {
        int id = readInt("Enter ID: ");
        Student s = dao.getByIdUsingProcedure(id);
        if (s != null) System.out.println("Found: " + s);
        else System.out.println("Not found.");
    }

    private static void doCountByCourse() {
        System.out.println("Enter Course: ");
        String course = sc.nextLine().trim();
        int cnt = dao.countByCourseUsingProcedure(course);
        if (cnt >= 0) System.out.println("Count for " + course + " = " + cnt);
        else System.out.println("Error while counting.");
    }

    private static void doShowAll() {
        List<Student> all = dao.findAll();
        if (all.isEmpty()) System.out.println("No records.");
        else all.forEach(System.out::println);
    }
}
