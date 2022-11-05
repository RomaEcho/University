package com.foxmindedjavaspring.university.service;

import com.foxmindedjavaspring.university.model.Faculty;

import java.util.List;

public interface FacultyService {

    void addFaculty(Faculty faculty);

    void removeFaculty(Faculty faculty);

    void editFaculty(Faculty faculty);

    Faculty getFaculty(Long id);

    List<Faculty> getAllFaculties();
}