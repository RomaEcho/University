package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.University;

public interface UniversityDao {
    boolean create(University university);

    boolean delete(University university);
}
