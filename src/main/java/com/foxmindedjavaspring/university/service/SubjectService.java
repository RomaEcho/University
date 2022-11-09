package com.foxmindedjavaspring.university.service;

import com.foxmindedjavaspring.university.dto.SubjectDto;
import com.foxmindedjavaspring.university.model.Subject;

import java.util.List;

public interface SubjectService {

    void addSubject(Subject subject);

    void addSubject(SubjectDto subjectDto);

    void removeSubject(Subject subject);

    void removeSubject(SubjectDto subjectDto);

    void editSubject(Subject subject);

    Subject getSubject(Long id);

    List<Subject> getAllSubjects();

    List<Subject> getByName(String name);
}