package com.example;

import com.example.dao.StudentDAO;
import com.example.model.Student;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final StudentDAO dao = new StudentDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n======================================");
            System.out.println("         STUDENT CRUD SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Insert Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student by ID");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.println("Enter your choice: ");
            System.out.println("======================================");
         

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    insertStudent();
                    break;

                case 2:
                    updateStudent();
                    break;

                case 3:
                    deleteStudent();
                    break;

                case 4:
                    searchById();
                    break;

                case 5:
                    displayAll();
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid choice! Please choose between 1 and 6.");
            }
        }
    }

    // INSERT OPERATION
    private static void insertStudent() {
        System.out.println("Enter ID: ");
      
        int id = Integer.parseInt(sc.nextLine());

        System.out.println("Enter Name: ");
        String name = sc.nextLine();

        System.out.println("Enter Course: ");
        String course = sc.nextLine();

        System.out.println("Enter Marks: ");
        String marks = sc.nextLine();

        Student s = new Student(id, name, course, marks);

        if (dao.insert(s))
            System.out.println("Student inserted successfully.");
        else
            System.out.println("Insert failed. ID might already exist.");
    }

    // UPDATE OPERATION
    private static void updateStudent() {
        System.out.println("Enter ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        Student s = dao.findById(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Enter new Name (" + s.getName() + "): ");
        String name = sc.nextLine();
        if (!name.isBlank()) s.setName(name);

        System.out.print("Enter new Course (" + s.getCourse() + "): ");
        String course = sc.nextLine();
        if (!course.isBlank()) s.setCourse(course);

        System.out.println("Enter new Marks (" + s.getMarks() + "): ");
        String marks = sc.nextLine();
        if (!marks.isBlank()) s.setMarks(marks);

        if (dao.update(s))
            System.out.println("Student updated successfully.");
        else
            System.out.println("Update failed.");
    }

    // DELETE OPERATION
    private static void deleteStudent() {
        System.out.println("Enter ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        if (dao.delete(id))
            System.out.println("Student deleted successfully.");
        else
            System.out.println("Delete failed. ID not found.");
    }

    // SEARCH OPERATION
    private static void searchById() {
        System.out.print("Enter ID to search: ");
        int id = Integer.parseInt(sc.nextLine());

        Student s = dao.findById(id);

        if (s != null)
            System.out.println("Record Found: " + s);
        else
            System.out.println("No student found with this ID.");
    }

    // DISPLAY ALL
    private static void displayAll() {
        List<Student> list = dao.findAll();

        if (list.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n------ ALL STUDENTS ------");
        for (Student s : list) {
            System.out.println(s);
        }
    }
}
