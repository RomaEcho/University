package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Lecturer;
import com.foxmindedjavaspring.university.service.LecturerService;

@Component
public class LecturerServiceImpl implements LecturerService {
    private final GenericDao<Lecturer> genericDao;

    public LecturerServiceImpl(GenericDao<Lecturer> genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addLecturer(Lecturer lecturer) {
        genericDao.create(lecturer);
    }

    @Override
    public void removeLecturer(long id) {
        genericDao.delete(id);
    }

    @Override
    public Lecturer getLecturer(long id) {
        return genericDao.findById(id);
    }

    @Override
    public List<Lecturer> getAllLecturers() {
        return genericDao.findAll();
    }

}
