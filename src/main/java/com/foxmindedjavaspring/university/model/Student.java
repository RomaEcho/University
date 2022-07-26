package com.foxmindedjavaspring.university.model;

import java.time.LocalDate;

public class Student<B extends Student.Builder<B>>
        extends UniversityStaff<B> {
    private final LocalDate startDate;
    private final StudentState state;

    private Student(Builder<B> builder) {
        super(builder);
        this.startDate = builder.startDate;
        this.state = builder.state;
    }

    public static final class Builder<B extends Student.Builder<B>>
            extends UniversityStaff.Builder<B> {
        private LocalDate startDate;
        private StudentState state;

        public B withStartDate(LocalDate startDate) {
            this.startDate = startDate;
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

    public StudentState getState() {
        return state;
    }
}
