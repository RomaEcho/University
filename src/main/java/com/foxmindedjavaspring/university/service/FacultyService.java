package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Faculty;

public interface FacultyService {

    void addFaculty(Faculty faculty);

    void removeFaculty(Long id);

    Faculty getFaculty(Long id);

    List<Faculty> getAllFaculties();

}