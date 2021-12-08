package com.example.faculty.database.enums;

public enum Faculty {

    FACULTY_OF_COMPUTER_SCIENCES("FACULTY OF COMPUTER SCIENCES");
//    FACULTY_OF_HUMANITIES("FACULTY OF HUMANITIES"),
//    FACULTY_OF_ECONOMICS("FACULTY OF ECONOMICS"),
//    FACULTY_OF_LAW("FACULTY OF LAW");

    public String faculty;

    Faculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFaculty() {
        return this.faculty;
    }

}
