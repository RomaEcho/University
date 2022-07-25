package com.foxmindedjavaspring.university.model;

import java.time.LocalDateTime;

public class Lecture {
    private final CourseEvent courseEvent;
    private final LocalDateTime startTime;
    private final Integer lab;

    private Lecture(Builder builder) {
        this.courseEvent = builder.courseEvent;
        this.startTime = builder.startTime;
        this.lab = builder.lab;
    }

    public static final class Builder {
        private CourseEvent courseEvent;
        private LocalDateTime startTime;
        private Integer lab;

        public Builder() {
        }

        public Builder withCourseEvent(CourseEvent courseEvent) {
            this.courseEvent = courseEvent;
            return this;
        }

        public Builder withStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder withLab(Integer lab) {
            this.lab = lab;
            return this;
        }

        public Lecture build() {
            return new Lecture(this);
        }
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

}
