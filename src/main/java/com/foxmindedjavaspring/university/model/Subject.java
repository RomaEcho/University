package com.foxmindedjavaspring.university.model;

public class Subject {
    private final Integer number;
    private final String name;
    private String description;

    public Subject(Integer number, String name) {
        this.number = number;
        this.name = name;
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
