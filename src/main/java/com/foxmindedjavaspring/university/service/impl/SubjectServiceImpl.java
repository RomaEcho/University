package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.SubjectDao;
import com.foxmindedjavaspring.university.model.Subject;
import com.foxmindedjavaspring.university.service.SubjectService;

@Component
public class SubjectServiceImpl implements SubjectService {
    private final SubjectDao subjectDao;

    public SubjectServiceImpl(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Override
    public void addSubject(Subject subject) {
        subjectDao.create(subject);
    }

    @Override
    public void removeSubject(Long id) {
        subjectDao.delete(id);
    }

    @Override
    public Subject getSubject(Long id) {
        return subjectDao.findById(id);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectDao.findAll();
    }

    @Override
    public void editSubject(Long id, Subject subject) {
        subjectDao.update(id, subject);
    }

    @Override
    public List<Subject> getByName(String name) {
        return subjectDao.findByName(name);
    }
}
