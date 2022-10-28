package com.foxmindedjavaspring.university.service.impl;

import com.foxmindedjavaspring.university.dao.SubjectDao;
import com.foxmindedjavaspring.university.model.Subject;
import com.foxmindedjavaspring.university.service.SubjectService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
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
    public void removeSubject(Subject subject) {
        subjectDao.delete(subject);
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
    public void editSubject(Subject subject) {
        subjectDao.update(subject);
    }

    @Override
    public List<Subject> getByName(String name) {
        return subjectDao.findByName(name);
    }
}
