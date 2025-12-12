package com.example.model;

public class Student {
    private int id;
    private String name;
    private String course;
    private String marks;

    public Student() {}

    public Student(int id, String name, String course, String marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    // getters/setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public String getMarks() { return marks; }
    public void setMarks(String marks) { this.marks = marks; }

    @Override
    public String toString() {
        return String.format("Student{id=%d, name='%s', course='%s', marks='%s'}", id, name, course, marks);
    }
}
