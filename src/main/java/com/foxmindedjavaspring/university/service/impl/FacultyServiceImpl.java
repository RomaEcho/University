package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Faculty;
import com.foxmindedjavaspring.university.service.GenericService;

@Component
public class FacultyServiceImpl implements GenericService<Faculty> {
    private final GenericDao genericDao;

    public FacultyServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void add(Faculty faculty) {
        genericDao.create(faculty);
    }

    @Override
    public void remove(Faculty faculty) {
        genericDao.delete(faculty);
    }

    @Override
    public List<Faculty> getAll() {
        return genericDao.findAll();
    }
}
