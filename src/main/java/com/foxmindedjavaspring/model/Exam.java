package com.foxmindedjavaspring.model;

public class Exam {
    private final String title;
    private final String description;

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
