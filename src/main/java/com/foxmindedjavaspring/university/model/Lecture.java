package com.foxmindedjavaspring.university.model;

import java.time.LocalDateTime;

public class Lecture {
    private final Course course;
    private final LocalDateTime startTime;
    private final Integer lab;

    private Lecture(Builder builder) {
        this.course = builder.course;
        this.startTime = builder.startTime;
        this.lab = builder.lab;
    }

    public static final class Builder {
        private Course course;
        private LocalDateTime startTime;
        private Integer lab;

        public Builder withCourse(Course course) {
            this.course = course;
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

    public Course getCourse() {
        return course;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Integer getLab() {
        return lab;
    }
}
