package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Faculty;

public interface FacultyDao {
    int create(Faculty faculty);

    boolean delete(Faculty faculty);
}
