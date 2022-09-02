package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Subject;
import com.foxmindedjavaspring.university.service.SubjectService;

@Component
public class SubjectServiceImpl implements SubjectService {
    private final GenericDao genericDao;

    public SubjectServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addSubject(Subject subject) {
        genericDao.create(subject);
    }

    @Override
    public void removeSubject(Subject subject) {
        genericDao.delete(subject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return genericDao.findAll();
    }

}
