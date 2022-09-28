package com.foxmindedjavaspring.university.model;

public class Subject {
    private final Long id;
    private final Integer number;
    private final String name;
    private String description;

    public Subject(Long id, Integer number, String name) {
        this.id = id;
        this.number = number;
        this.name = name;
    }

    public Long getId() {
        return id;
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
