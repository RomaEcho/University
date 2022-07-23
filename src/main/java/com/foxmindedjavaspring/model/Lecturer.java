package com.foxmindedjavaspring.model;

public class Lecturer extends UniversityStaff {
    private String level;

    public Lecturer(String staffId, String title, String level) {
        super(staffId, title);
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
