package com.foxmindedjavaspring.university.service;

import com.foxmindedjavaspring.university.model.Subject;

import java.util.List;

public interface SubjectService {

    void addSubject(Subject subject);

    void removeSubject(Subject subject);

    void editSubject(Subject subject);

    Subject getSubject(Long id);

    List<Subject> getAllSubjects();

    List<Subject> getByName(String name);
}