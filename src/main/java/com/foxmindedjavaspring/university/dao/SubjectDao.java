package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Subject;

public interface SubjectDao {
    void addSubject(Subject subject);

    void removeSubject(Subject subject);

    void addDescription(Subject subject, String description);
}
