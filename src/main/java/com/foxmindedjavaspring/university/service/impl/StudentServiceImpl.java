package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.StudentDao;
import com.foxmindedjavaspring.university.model.Student;
import com.foxmindedjavaspring.university.service.StudentService;

@Component
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void addStudent(Student student) {
        studentDao.create(student);
    }

    @Override
    public void removeStudent(Student student) {
        studentDao.delete(student);
    }

    @Override
    public Student getStudent(Long id) {
        return studentDao.findById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public void editStudent(Student student) {
        studentDao.update(student);
    }
}
