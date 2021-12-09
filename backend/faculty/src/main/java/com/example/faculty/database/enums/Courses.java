package com.example.faculty.database.enums;

public enum Courses {

    c1(1),
    c2(2),
    c3(3),
    c4(4),
    c5(5),
    c6(6);

    public int course;

    Courses(int course) {
        this.course = course;
    }

    public int getCourse() {
        return this.course;
    }

}
