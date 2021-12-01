package com.example.faculty.database.enums;

public enum CourseB {

    c1(1),
    c2(2),
    c3(3),
    c4(4);

    public int course;

    CourseB(int course) {
        this.course = course;
    }

    public int getCourse() {
        return this.course;
    }

}
