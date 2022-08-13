package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Faculty;

public interface FacultyDao {
    boolean create(Faculty faculty);

    boolean delete(Faculty faculty);
}
