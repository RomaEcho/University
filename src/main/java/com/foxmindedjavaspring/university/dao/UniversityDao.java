package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.University;

public interface UniversityDao {
    void create(University university);

    void delete(University university);
}
