package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Student;
import com.foxmindedjavaspring.university.model.StudentState;

public interface StudentDao {
    void addStudent(Student student);

    void removeStudent(Student student);

    void setState(Student student, StudentState state);
}
