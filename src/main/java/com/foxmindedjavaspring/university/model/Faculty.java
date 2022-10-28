package com.foxmindedjavaspring.university.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "faculties")
public class Faculty extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @Column(name = "department")
    private String department;

    @Column(name = "address")
    private String address;

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
