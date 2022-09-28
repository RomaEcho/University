package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Subject;

public interface SubjectDao extends GenericDao<Subject> {
    int update(Long id, Subject subject);
}
