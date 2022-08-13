package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Faculty;

public interface FacultyDao {
    void create(Faculty faculty);

    void delete(Faculty faculty);
}
