package com.foxmindedjavaspring.university.model;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private final String department;
    private final List<Subject> subjects = new ArrayList<>();
    private String address;

    public Faculty(String department, String address) {
        this.department = department;
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
