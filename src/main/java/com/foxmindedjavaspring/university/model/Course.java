package com.foxmindedjavaspring.university.model;

public class Course {
    private final String topic;
    private final Subject subject;
    private final String description;

    private Course(Builder builder) {
        this.topic = builder.topic;
        this.subject = builder.subject;
        this.description = builder.description;
    }

    public static final class Builder {
        private String topic;
        private Subject subject;
        private String description;

        public Builder() {
        }

        public Builder withTopic(String topic) {
            this.topic = topic;
            return this;
        }

        public Builder withSubject(Subject subject) {
            this.subject = subject;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Course build() {
            return new Course(this);
        }
    }

    public String getTopic() {
        return topic;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

}
