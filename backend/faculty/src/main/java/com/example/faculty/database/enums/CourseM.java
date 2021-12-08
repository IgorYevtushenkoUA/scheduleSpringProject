package com.example.faculty.database.enums;

public enum CourseM {

    c1(1),
    c2(2);

    public int course;

    CourseM(int course) {
        this.course = course;
    }

    public int getCourse() {
        return this.course;
    }

}
