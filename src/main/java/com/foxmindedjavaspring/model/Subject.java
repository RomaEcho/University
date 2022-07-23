package com.foxmindedjavaspring.model;

public class Subject {
    private final Integer number;
    private final String name;
    private String description;

    private Subject(Builder builder) {
        this.number = builder.number;
        this.name = builder.name;
        this.description = builder.description;
    }

    public static final class Builder {
        private Integer number;
        private String name;
        private String description;

        public Builder() {
        }

        public Builder withNumber(Integer number) {
            this.number = number;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Subject build() {
            return new Subject(this);
        }
    }

    public Integer getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
