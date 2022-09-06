package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Subject;
import com.foxmindedjavaspring.university.service.SubjectService;

@Component
public class SubjectServiceImpl implements SubjectService {
    private final GenericDao<Subject> genericDao;

    public SubjectServiceImpl(GenericDao<Subject> genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addSubject(Subject subject) {
        genericDao.create(subject);
    }

    @Override
    public void removeSubject(Long id) {
        genericDao.delete(id);
    }

    @Override
    public Subject getSubject(Long id) {
        return genericDao.findById(id);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return genericDao.findAll();
    }

}
