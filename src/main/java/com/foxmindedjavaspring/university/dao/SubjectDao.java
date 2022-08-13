package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Subject;

public interface SubjectDao {
    boolean create(Subject subject);

    boolean delete(Subject subject);

    boolean addDescription(Subject subject, String description);
}
