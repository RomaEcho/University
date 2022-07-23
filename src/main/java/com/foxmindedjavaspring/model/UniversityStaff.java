package com.foxmindedjavaspring.model;

public class UniversityStaff extends Person.Builder {
    private final String staffId;
    private final String title;

    public UniversityStaff(String staffId, String title) {
        this.staffId = staffId;
        this.title = title;
    }
}
