package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Lecturer;
import com.foxmindedjavaspring.university.service.LecturerService;

@Component
public class LecturerServiceImpl implements LecturerService {
    private final GenericDao genericDao;

    public LecturerServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addLecturer(Lecturer lecturer) {
        genericDao.create(lecturer);
    }

    @Override
    public void removeLecturer(Lecturer lecturer) {
        genericDao.delete(lecturer);
    }

    @Override
    public List<Lecturer> getAllLecturers() {
        return genericDao.findAll();
    }

}
