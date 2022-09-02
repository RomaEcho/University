package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Faculty;
import com.foxmindedjavaspring.university.service.FacultyService;

@Component
public class FacultyServiceImpl implements FacultyService {
    private final GenericDao genericDao;

    public FacultyServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addFaculty(Faculty faculty) {
        genericDao.create(faculty);
    }

    @Override
    public void removeFaculty(Faculty faculty) {
        genericDao.delete(faculty);
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return genericDao.findAll();
    }
}
