package com.foxmindedjavaspring.university.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lecturers")
public class Lecturer extends UniversityStaff {

    @OneToOne(mappedBy = "lecturer")
    private Course course;

    @Column(name = "level")
    private String level;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
