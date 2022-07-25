package com.foxmindedjavaspring.university.model;

public class Course {
    private final String topic;
    private final Subject subject;
    private final String description;

    public Course(String topic, Subject subject, String description) {
        this.topic = topic;
        this.subject = subject;
        this.description = description;
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
