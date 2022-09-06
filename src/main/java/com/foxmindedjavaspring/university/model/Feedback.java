package com.foxmindedjavaspring.university.model;

import java.time.LocalDateTime;

public class Feedback {
    private final Student student;
    private final Course course;
    private final Integer rating;
    private final Comment comment;
    private final LocalDateTime creationDate;
    private final LocalDateTime updateDate;

    private Feedback(Builder builder) {
        this.student = builder.student;
        this.course = builder.course;
        this.rating = builder.rating;
        this.comment = builder.comment;
        this.creationDate = builder.creationDate;
        this.updateDate = builder.updateDate;
    }

    public static final class Builder {
        private Student student;
        private Course course;
        private Integer rating;
        private Comment comment;
        private LocalDateTime creationDate;
        private LocalDateTime updateDate;
        
        public Builder withStudent(Student student) {
            this.student = student;
            return this;
        }

        public Builder withCourse(Course course) {
            this.course = course;
            return this;
        }

        public Builder withRating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Builder withComment(Comment comment) {
            this.comment = comment;
            return this;
        }

        public Builder withCreationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder withUpdateDate(LocalDateTime updateDate) {
            this.updateDate = updateDate;
            return this;
        }

        public Feedback build() {
            return new Feedback(this);
        }
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Integer getRating() {
        return rating;
    }

    public Comment getComment() {
        return comment;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

}
