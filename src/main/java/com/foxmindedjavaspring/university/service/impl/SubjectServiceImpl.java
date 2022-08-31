package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Subject;
import com.foxmindedjavaspring.university.service.GenericService;

@Component
public class SubjectServiceImpl implements GenericService<Subject> {
    private final GenericDao genericDao;

    public SubjectServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void add(Subject subject) {
        genericDao.create(subject);
    }

    @Override
    public void remove(Subject subject) {
        genericDao.delete(subject);
    }

    @Override
    public List<Subject> getAll() {
        return genericDao.findAll();
    }

}
