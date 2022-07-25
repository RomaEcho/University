package com.foxmindedjavaspring.university.model;

public class Exam {
    private final String title;
    private String description;

    public Exam(String title) {
        this.title = title;
    }

    public Exam(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
