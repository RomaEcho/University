package com.foxmindedjavaspring.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student extends UniversityStaff {
    private final LocalDate startDate;
    private final List<CourseEvent> courses = new ArrayList<>();
    private final List<ExamEvent> exams = new ArrayList<>();
    private StudentState state;

    public Student(String staffId, String title, LocalDate startDate,
            StudentState state) {
        super(staffId, title);
        this.startDate = startDate;
        this.state = state;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public List<CourseEvent> getCourses() {
        return courses;
    }

    public List<ExamEvent> getExams() {
        return exams;
    }

    public StudentState getState() {
        return state;
    }

    public void setState(StudentState state) {
        this.state = state;
    }
}
