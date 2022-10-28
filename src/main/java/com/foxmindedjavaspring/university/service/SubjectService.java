package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Subject;

public interface SubjectService {

    void addSubject(Subject subject);

    void removeSubject(Subject subject);

    Subject getSubject(Long id);

    List<Subject> getAllSubjects();

    void editSubject(Subject subject);

    List<Subject> getByName(String name);
}