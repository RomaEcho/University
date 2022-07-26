package com.foxmindedjavaspring.university.model;

public class Faculty {
    private final String department;
    private final String address;

    public Faculty(String department, String address) {
        this.department = department;
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public String getAddress() {
        return address;
    }
}
