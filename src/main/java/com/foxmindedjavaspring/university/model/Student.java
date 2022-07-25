package com.foxmindedjavaspring.university.model;

import java.time.LocalDate;
import java.util.List;

public class Student<B extends Student.Builder<B>>
        extends UniversityStaff<B> {
    private final LocalDate startDate;
    private final List<CourseEvent> courseEvents;
    private final List<ExamEvent> examEvents;
    private final StudentState state;

    private Student(Builder<B> builder) {
        super(builder);
        this.startDate = builder.startDate;
        this.courseEvents = builder.courseEvents;
        this.examEvents = builder.examEvents;
        this.state = builder.state;
    }

    public static final class Builder<B extends Student.Builder<B>>
            extends UniversityStaff.Builder<B> {
        private LocalDate startDate;
        private List<CourseEvent> courseEvents;
        private List<ExamEvent> examEvents;
        private StudentState state;

        public B withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return (B) this;
        }

        public B withCourseEvents(List<CourseEvent> courseEvents) {
            this.courseEvents = courseEvents;
            return (B) this;
        }

        public B withExamEvents(List<ExamEvent> examEvents) {
            this.examEvents = examEvents;
            return (B) this;
        }

        public B withState(StudentState state) {
            this.state = state;
            return (B) this;
        }

        @Override
        public Student<B> build() {
            return new Student<>(this);
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public List<CourseEvent> getCourseEvents() {
        return courseEvents;
    }

    public List<ExamEvent> getExamEvents() {
        return examEvents;
    }

    public StudentState getState() {
        return state;
    }
}
