package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.University;
import com.foxmindedjavaspring.university.service.GenericService;

@Component
public class UniversityServiceImpl implements GenericService<University> {
    private final GenericDao genericDao;

    public UniversityServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void add(University university) {
        genericDao.create(university);
    }

    @Override
    public void remove(University university) {
        genericDao.delete(university);
    }

    @Override
    public List<University> getAll() {
        return genericDao.findAll();
    }
}
