package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.University;

public interface UniversityDao {
    int create(University university);

    boolean delete(University university);

    University findById(long long1);
}
