package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Student;
import com.foxmindedjavaspring.university.model.StudentState;

public interface StudentDao {
    int create(Student student);

    boolean delete(Student student);

    boolean setState(Student student, StudentState state);
}
