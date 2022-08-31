package com.foxmindedjavaspring.university.model;

import java.time.LocalDateTime;

public class Comment {
    private final Student student;
    private final Course course;
    private final Integer rating;
    private final String comment;
    private final LocalDateTime commentUpdate;
    private final LocalDateTime ratingUpdate;

    private Comment(Builder builder) {
        this.student = builder.student;
        this.course = builder.course;
        this.rating = builder.rating;
        this.comment = builder.comment;
        this.commentUpdate = builder.commentUpdate;
        this.ratingUpdate = builder.ratingUpdate;
    }

    public static final class Builder {
        private Student student;
        private Course course;
        private Integer rating;
        private String comment;
        private LocalDateTime commentUpdate;
        private LocalDateTime ratingUpdate;

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

        public Builder withComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder withCommentUpdate(LocalDateTime commentUpdate) {
            this.commentUpdate = commentUpdate;
            return this;
        }

        public Builder withRatingUpdate(LocalDateTime ratingUpdate) {
            this.ratingUpdate = ratingUpdate;
            return this;
        }

        public Comment build() {
            return new Comment(this);
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

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCommentUpdate() {
        return commentUpdate;
    }

    public LocalDateTime getRatingUpdate() {
        return ratingUpdate;
    }

}
