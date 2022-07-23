package com.foxmindedjavaspring.model;

import java.util.ArrayList;
import java.util.List;

public class University {
    private final List<Faculty> faculties = new ArrayList<>();
    private String name;
    private String hqLocation;

    public University(String name, String hqLocation) {
        this.name = name;
        this.hqLocation = hqLocation;
    }

    public String getName() {
        return name;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public String getHqLocation() {
        return hqLocation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHqLocation(String hqLocation) {
        this.hqLocation = hqLocation;
    }
}
