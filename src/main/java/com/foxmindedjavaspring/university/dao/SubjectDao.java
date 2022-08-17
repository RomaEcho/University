package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Subject;

public interface SubjectDao {
    int create(Subject subject);

    boolean delete(Subject subject);

    boolean addDescription(Subject subject, String description);

    Subject findById(long long1);
}
