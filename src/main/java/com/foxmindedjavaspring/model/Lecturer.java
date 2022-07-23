package com.foxmindedjavaspring.model;

import java.time.LocalDate;

public class Lecturer extends UniversityStaff {
    private String level;

    public Lecturer(String firstName, String lastName, LocalDate birthday,
            String gender, String phone, String email, String address,
            String staffId, String title, String level) {
        super(firstName, lastName, birthday, gender, phone, email, address,
                staffId, title);
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
