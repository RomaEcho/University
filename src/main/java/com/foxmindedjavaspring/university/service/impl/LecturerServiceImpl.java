package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Lecturer;
import com.foxmindedjavaspring.university.service.GenericService;

@Component
public class LecturerServiceImpl implements GenericService<Lecturer> {
    private final GenericDao genericDao;

    public LecturerServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void add(Lecturer lecturer) {
        genericDao.create(lecturer);
    }

    @Override
    public void remove(Lecturer lecturer) {
        genericDao.delete(lecturer);
    }

    @Override
    public List<Lecturer> getAll() {
        return genericDao.findAll();
    }

}
