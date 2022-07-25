package com.foxmindedjavaspring.university.model;

import java.time.LocalDate;

public class CourseEvent {
    private final Course course;
    private final LocalDate startDate;
    private Lecturer lecturer;
    private Integer numberOfHours;
    private Integer rate;

    public CourseEvent(Course course, LocalDate startDate, Lecturer lecturer,
            Integer numberOfHours) {
        this.course = course;
        this.startDate = startDate;
        this.lecturer = lecturer;
        this.numberOfHours = numberOfHours;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public Integer getNumberOfHours() {
        return numberOfHours;
    }

    public Integer getRate() {
        return rate;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public void setNumberOfHours(Integer numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
