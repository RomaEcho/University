package com.foxmindedjavaspring.university.dao;

import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.model.Student;

@Repository
public class StudentDao extends AbstractGenericDao<Student> {
    public StudentDao() {
        super(Student.class);
    }
}
