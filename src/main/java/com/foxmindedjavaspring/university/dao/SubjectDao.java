package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Subject;

public interface SubjectDao {
    void create(Subject subject);

    void delete(Subject subject);

    void addDescription(Subject subject, String description);
}
