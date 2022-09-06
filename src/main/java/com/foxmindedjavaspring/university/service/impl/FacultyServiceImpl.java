package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Faculty;
import com.foxmindedjavaspring.university.service.FacultyService;

@Component
public class FacultyServiceImpl implements FacultyService {
    private final GenericDao<Faculty> genericDao;

    public FacultyServiceImpl(GenericDao<Faculty> genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addFaculty(Faculty faculty) {
        genericDao.create(faculty);
    }

    @Override
    public void removeFaculty(long id) {
        genericDao.delete(id);
    }

    @Override
    public Faculty getFaculty(long id) {
        return genericDao.findById(id);
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return genericDao.findAll();
    }
}
