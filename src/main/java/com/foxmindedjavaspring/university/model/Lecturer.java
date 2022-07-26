package com.foxmindedjavaspring.university.model;

public class Lecturer<B extends Lecturer.Builder<B>>
        extends UniversityStaff<B> {
    private final String level;

    private Lecturer(Builder<B> builder) {
        super(builder);
        this.level = builder.level;
    }

    public static final class Builder<B extends Lecturer.Builder<B>>
            extends UniversityStaff.Builder<B> {
        private String level;

        public B withLevel(String level) {
            this.level = level;
            return (B) this;
        }

        @Override
        public Lecturer<B> build() {
            return new Lecturer<>(this);
        }
    }

    public String getLevel() {
        return level;
    }
}
