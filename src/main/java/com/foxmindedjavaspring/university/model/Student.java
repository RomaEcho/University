package com.foxmindedjavaspring.university.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends UniversityStaff {

    @Column(name = "starting_date")
    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private StudentState state;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public StudentState getState() {
        return state;
    }

    public void setState(StudentState state) {
        this.state = state;
    }

}
