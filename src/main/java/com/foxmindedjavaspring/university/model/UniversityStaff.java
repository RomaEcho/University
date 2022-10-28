package com.foxmindedjavaspring.university.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class UniversityStaff extends Person {

    @Column(name = "staff_id")
    private String staffId;

    @Column(name = "title")
    private String title;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
