package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Student;
import com.foxmindedjavaspring.university.service.StudentService;

@Component
public class StudentServiceImpl implements StudentService {
    private final GenericDao<Student> genericDao;

    public StudentServiceImpl(GenericDao<Student> genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addStudent(Student student) {
        genericDao.create(student);
    }

    @Override
    public void removeStudent(Long id) {
        genericDao.delete(id);
    }

    @Override
    public Student getStudent(Long id) {
        return genericDao.findById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return genericDao.findAll();
    }
}
