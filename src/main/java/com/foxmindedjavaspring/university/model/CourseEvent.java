package com.foxmindedjavaspring.university.model;

import java.time.LocalDate;

public class CourseEvent {
    private final Course course;
    private final LocalDate startDate;
    private final Lecturer lecturer;
    private final Integer numberOfHours;
    private final Integer rate;

    private CourseEvent(Builder builder) {
        this.course = builder.course;
        this.startDate = builder.startDate;
        this.lecturer = builder.lecturer;
        this.numberOfHours = builder.numberOfHours;
        this.rate = builder.rate;
    }

    public static final class Builder {
        private Course course;
        private LocalDate startDate;
        private Lecturer lecturer;
        private Integer numberOfHours;
        private Integer rate;

        public Builder() {
        }

        public Builder withCourse(Course course) {
            this.course = course;
            return this;
        }

        public Builder withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withLecturer(Lecturer lecturer) {
            this.lecturer = lecturer;
            return this;
        }

        public Builder withNumberOfHours(Integer numberOfHours) {
            this.numberOfHours = numberOfHours;
            return this;
        }

        public Builder withRate(Integer rate) {
            this.rate = rate;
            return this;
        }

        public CourseEvent build() {
            return new CourseEvent(this);
        }
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

}
