package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Student;
import com.foxmindedjavaspring.university.service.GenericService;

@Component
public class StudentServiceImpl implements GenericService<Student> {
    private final GenericDao genericDao;

    public StudentServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void add(Student student) {
        genericDao.create(student);
    }

    @Override
    public void remove(Student student) {
        genericDao.delete(student);
    }

    @Override
    public List<Student> getAll() {
        return genericDao.findAll();
    }
}
