package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Student;
import com.foxmindedjavaspring.university.service.StudentService;

@Component
public class StudentServiceImpl implements StudentService {
    private final GenericDao genericDao;

    public StudentServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addStudent(Student student) {
        genericDao.create(student);
    }

    @Override
    public void removeStudent(Student student) {
        genericDao.delete(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return genericDao.findAll();
    }
}
