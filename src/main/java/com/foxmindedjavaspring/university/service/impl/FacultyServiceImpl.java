package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.FacultyDao;
import com.foxmindedjavaspring.university.model.Faculty;
import com.foxmindedjavaspring.university.service.FacultyService;

@Component
public class FacultyServiceImpl implements FacultyService {
    private final FacultyDao facultyDao;

    public FacultyServiceImpl(FacultyDao facultyDao) {
        this.facultyDao = facultyDao;
    }

    @Override
    public void addFaculty(Faculty faculty) {
        facultyDao.create(faculty);
    }

    @Override
    public void removeFaculty(Faculty faculty) {
        facultyDao.delete(faculty);
    }

    @Override
    public Faculty getFaculty(Long id) {
        return facultyDao.findById(id);
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyDao.findAll();
    }

    @Override
    public void editFaculty(Faculty faculty) {
        facultyDao.update(faculty);
    }
}
