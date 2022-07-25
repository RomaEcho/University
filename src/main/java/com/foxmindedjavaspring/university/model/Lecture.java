package com.foxmindedjavaspring.university.model;

import java.time.LocalDateTime;

public class Lecture {
    private final CourseEvent courseEvent;
    private LocalDateTime startTime;
    private Integer lab;

    public Lecture(CourseEvent courseEvent, LocalDateTime startTime,
            Integer lab) {
        this.courseEvent = courseEvent;
        this.startTime = startTime;
        this.lab = lab;
    }

    public CourseEvent getCourseEvent() {
        return courseEvent;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Integer getLab() {
        return lab;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setLab(Integer lab) {
        this.lab = lab;
    }
}
