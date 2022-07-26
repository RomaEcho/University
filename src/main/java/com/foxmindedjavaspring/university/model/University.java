package com.foxmindedjavaspring.university.model;

public class University {
    private final String name;
    private final String hqLocation;

    public University(String name, String hqLocation) {
        this.name = name;
        this.hqLocation = hqLocation;
    }

    public String getName() {
        return name;
    }

    public String getHqLocation() {
        return hqLocation;
    }

}
