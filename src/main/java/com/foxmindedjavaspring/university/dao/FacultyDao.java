package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Faculty;

public interface FacultyDao {
    void addFaculty(Faculty faculty);

    void removeFaculty(Faculty faculty);
}
