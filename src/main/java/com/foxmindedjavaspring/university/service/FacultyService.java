package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Faculty;

public interface FacultyService {

    void addFaculty(Faculty faculty);

    void removeFaculty(long id);

    Faculty getFaculty(long id);

    List<Faculty> getAllFaculties();

}